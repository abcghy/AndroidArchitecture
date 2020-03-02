package com.example.architecturedemo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturedemo.MainViewModel
import com.example.architecturedemo.databinding.ItemMainViewBinding
import com.example.data.Story

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
    ListAdapter<Story, RecyclerView.ViewHolder>(StoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MainViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MainViewHolder).bind(getItem(position))
    }
}

class StoryDiffCallback : DiffUtil.ItemCallback<Story>() {
    override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem == newItem
    }
}