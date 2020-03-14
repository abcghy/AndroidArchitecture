package com.example.architecturedemo.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturedemo.MainViewModel
import com.example.architecturedemo.databinding.ItemMainViewBinding
import com.example.architecturedemo.databinding.ItemTopViewBinding
import com.example.architecturedemo.data.Story
import com.example.architecturedemo.data.TopStory

class TopViewHolder(val binding: ItemTopViewBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(list: List<TopStory>) {
        binding.topStories = list
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): TopViewHolder {
            val binding =
                ItemTopViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val topPagerAdapter = TopViewPagerAdapter()
            binding.viewPager.adapter = topPagerAdapter
            return TopViewHolder(binding)
        }
    }
}

class MainViewHolder(val binding: ItemMainViewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(story: Story) {
        binding.story = story
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MainViewHolder {
            val binding =
                ItemMainViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MainViewHolder(binding)
        }
    }
}

class MainAdapter(val viewModel: MainViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TOP = 0
    val NORMAL = 1

    private val list = ArrayList<Any>()

    fun setData(data: List<Any>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is List<*>) {
            TOP
        } else {
            NORMAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TOP -> {
                TopViewHolder.from(parent)
            }
            NORMAL -> {
                MainViewHolder.from(parent)
            }
            else -> {
                MainViewHolder.from(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.e("HHHHH", "pos: $position")
        if (holder is MainViewHolder) {
            holder.bind(list[position] as Story)
        } else if (holder is TopViewHolder) {
            holder.bind(list[position] as List<TopStory>)
        }
    }
}
