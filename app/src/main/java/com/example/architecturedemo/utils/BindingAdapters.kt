package com.example.architecturedemo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.architecturedemo.ui.MainAdapter
import com.example.architecturedemo.ui.TopViewPagerAdapter
import com.example.architecturedemo.data.TopStory

// todo ghy 先这样吧
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Any>?) {
    items?.let {
        (listView.adapter as MainAdapter).setData(items)
    }
}

@BindingAdapter("app:items")
fun setItems(viewPager: ViewPager2, items: List<TopStory>) {
    (viewPager.adapter as TopViewPagerAdapter).setData(items)
}

@BindingAdapter(value = ["app:resource", "app:radius"], requireAll = false)
fun setBitmap(imageView: ImageView, url: String, size: Int?) {
    if (size != null) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(size)))
            .into(imageView)
    } else {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}