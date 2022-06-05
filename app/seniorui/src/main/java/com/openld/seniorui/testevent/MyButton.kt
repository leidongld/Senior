package com.openld.seniorui.testevent

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.Button

/**
 * author: lllddd
 * created on: 2022/5/29 12:45
 * description:我的自定义按钮
 */
@SuppressLint("AppCompatCustomView")
class MyButton(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
    Button(context, attrs, defStyleAttr, defStyleRes) {
    private val TAG = "事件处理中的子View"

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?) : this(context, null)

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.d(
            TAG,
            ">>> 子View dispatchTouchEvent ${super.dispatchTouchEvent(event)} ${event.toString()}}"
        )
        return super.dispatchTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, ">>> 子View onTouchEvent ${super.onTouchEvent(event)} ${event.toString()}")
        return super.onTouchEvent(event)
    }
}