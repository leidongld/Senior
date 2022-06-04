package com.openld.senior.uisection.testevent

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * author: lllddd
 * created on: 2022/5/29 12:43
 * description:我的自定义约束布局
 */
class MyConstraintLayout(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val TAG = "事件处理中的ViewGroup"

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : this(context, attrs, defStyleAttr, 0)

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    /**
     * 分发事件
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(
            TAG,
            ">>> ViewGroup dispatchTouchEvent ${super.dispatchTouchEvent(ev)} ${ev.toString()}"
        )
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 拦截事件
     */
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // 如果想要子View收不到事件就要在该方法中返回true拦截掉事件
        Log.d(
            TAG,
            ">>> ViewGroup onInterceptTouchEvent ${super.onInterceptTouchEvent(ev)} ${ev.toString()}"
        )
        return super.onInterceptTouchEvent(ev)
//        Log.d(
//            TAG,
//            ">>> ViewGroup onInterceptTouchEvent ${(true)} ${ev.toString()}"
//        )
//        return true
    }

    /**
     * 响应处理事件
     */
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, ">>> ViewGroup onTouchEvent ${super.onTouchEvent(event)} ${event.toString()}")
        return super.onTouchEvent(event)
//        Log.d(TAG, ">>> ViewGroup onTouchEvent ${true} ${event.toString()}")
//        return true
    }
}