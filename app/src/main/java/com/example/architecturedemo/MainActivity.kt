package com.example.architecturedemo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.example.architecturedemo.databinding.ActivityMainBinding
import com.example.architecturedemo.ui.MainAdapter
import com.example.data.RetrofitInstance
import com.example.data.LatestRes
import com.example.data.Story
import kotlinx.coroutines.launch

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        with(modelClass) {
            return when {
                isAssignableFrom(MainViewModel::class.java) -> MainViewModel()
                else -> throw Exception()
            } as T
        }
    }
}

class MainViewModel : ViewModel() {
    private val _todayRes = MutableLiveData<LatestRes>()

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    val items: LiveData<List<Story>> = _todayRes.map {
        it.stories ?: emptyList()
    }

    fun refresh() {
        _dataLoading.value = true
        viewModelScope.launch {
            _todayRes.value = RetrofitInstance.zhihuService.today()
            _dataLoading.value = false
        }
    }
}

class MainActivity : AppCompatActivity() {

    private val mViewModel by viewModels<MainViewModel> { MainViewModelFactory() }

    private lateinit var viewDataBinding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewDataBinding.apply {
            viewmodel = mViewModel
        }
        viewDataBinding.lifecycleOwner = this

        initAdapter()
        initData()
    }

    private fun initAdapter() {
        mainAdapter = MainAdapter(mViewModel)
        viewDataBinding.rv.adapter = mainAdapter
    }

    private fun initData() {
        mViewModel.refresh()
    }
}
