package com.example.architecturedemo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.architecturedemo.ui.MainAdapter
import com.example.data.Story

// todo ghy 先这样吧
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Story>?) {
    items?.let {
        (listView.adapter as MainAdapter).submitList(items)
    }
}

@BindingAdapter(value = ["app:resource", "app:radius"], requireAll = false)
fun setBitmap(imageView: ImageView, url: String, size: Int = 4) {
    Glide.with(imageView.context)
        .load(url)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(size)))
        .into(imageView)
}