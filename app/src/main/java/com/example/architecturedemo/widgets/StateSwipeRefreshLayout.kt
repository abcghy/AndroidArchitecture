package com.example.architecturedemo.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * 这个类的主要作用是添加一个自定义 refreshing 状态
 */
class StateSwipeRefreshLayout : SwipeRefreshLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
}