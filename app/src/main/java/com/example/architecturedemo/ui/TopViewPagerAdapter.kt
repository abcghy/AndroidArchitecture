package com.example.architecturedemo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturedemo.databinding.PagerTopBinding
import com.example.architecturedemo.data.TopStory

//class TopViewPagerAdapter: PagerAdapter {
//    constructor() : super()
//
//    private val list = ArrayList<TopStory>()
//
//    fun setData(data: List<TopStory>) {
//        list.clear()
//        list.addAll(data)
//    }
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view == `object`
//    }
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        return LayoutInflater.from(container.context).inflate(R.layout.pager_top, container, true)
//    }
//
//    override fun getCount(): Int {
//        return list.size
//    }
//
//}

class TopPagerViewHolder(val binding: PagerTopBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(topStory: TopStory) {
        binding.topStory = topStory
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): TopPagerViewHolder {
            val binding =
                PagerTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return TopPagerViewHolder(binding)
        }
    }
}

class TopViewPagerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder> {
    constructor() : super()

    private val list = ArrayList<TopStory>()

    fun setData(data: List<TopStory>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TopPagerViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TopPagerViewHolder).bind(list[position])
    }
}