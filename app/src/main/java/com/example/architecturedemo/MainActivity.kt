package com.example.architecturedemo

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturedemo.databinding.ActivityMainBinding
import com.example.architecturedemo.repo.DailyRemoteDataSource
import com.example.architecturedemo.repo.DailyRepo
import com.example.architecturedemo.ui.MainAdapter
import com.example.architecturedemo.utils.exhaustive
import com.example.architecturedemo.utils.guard
import com.example.architecturedemo.data.Result
import com.example.architecturedemo.data.database.DailyLocalDataSource
import com.example.architecturedemo.data.database.StoryDatabase
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class MainViewModelFactory(val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        with(modelClass) {
            return when {
                isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                    DailyRepo(DailyRemoteDataSource(), DailyLocalDataSource(StoryDatabase.getInstance(context).storyDao()))
                )
                else -> throw Exception()
            } as T
        }
    }
}

class MainViewModel(private val dailyRepo: DailyRepo) : ViewModel() {
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _loadingMore = MutableLiveData<Boolean>()
    val loadingMore: LiveData<Boolean> = _loadingMore

    private val _items = MutableLiveData<MutableList<Any>>()
    val items: LiveData<MutableList<Any>> = _items

    private var lastDate: Date? = null

    fun refresh() {
        _dataLoading.value = true
        viewModelScope.launch {
//            val todayNews = RetrofitInstance.zhihuService.today()
            val todayNews = dailyRepo.refresh()
            when (todayNews) {
                is Result.Success -> {
                    lastDate = todayNews.data.date

                    val retList = ArrayList<Any>()
                    todayNews.data.topStories?.let { topStories ->
                        retList.add(topStories)
                    }
                    todayNews.data.stories?.let { stories ->
                        retList.addAll(stories)
                    }
                    _items.value = retList

                    _dataLoading.value = false
                }
                is Result.Error -> {

                }
            }.exhaustive
        }
    }

    fun loadMore() {
        val guardedLastDate = guard(lastDate) { return }
        _loadingMore.value = true
        viewModelScope.launch {
            val historyResult = dailyRepo.loadMore(guardedLastDate)
            when (historyResult) {
                is Result.Success -> {
                    lastDate = historyResult.data.date
                    val newItems = historyResult.data.stories
                    val oldItems = _items.value
                    if (oldItems != null && newItems != null) {
                        oldItems.addAll(newItems)
                    }
                    _items.value = oldItems
                    _loadingMore.value = false
                }
                is Result.Error -> {

                }
            }.exhaustive
        }
    }
}

class MainActivity : AppCompatActivity() {

    private val mViewModel by viewModels<MainViewModel> { MainViewModelFactory(applicationContext) }

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

        viewDataBinding.rv.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (mainAdapter.itemCount - 1 ==
                    (viewDataBinding.rv.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()) {
                    if (mViewModel.loadingMore.value != true) {
                        mViewModel.loadMore()
                    }
                }
            }
        })
    }

    private fun initData() {
        mViewModel.refresh()
    }
}
