package com.openld.seniorui.testslidingmenu

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout

/**
 * author: lllddd
 * created on: 2022/6/28 22:01
 * description:自定义菜单布局
 */
class SlidingMenuContainer @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HorizontalScrollView(context, attrs, defStyleAttr) {
    private var mScreenWidth: Int

    private var mIsOnce = false

    private lateinit var mMain: ViewGroup

    private lateinit var mMenu: ViewGroup

    private var mMenuWidth: Int = 0

    private val mMenuPaddingRight = 0

    init {
        mScreenWidth = context!!.resources.displayMetrics.widthPixels
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!mIsOnce) {
            val wrapper = getChildAt(0) as LinearLayout

            mMenu = wrapper.getChildAt(0) as LinearLayout
            mMain = wrapper.getChildAt(1) as LinearLayout

            mMenuWidth = mScreenWidth - mMenuPaddingRight

            mMenu.layoutParams.width = mMenuWidth
            mMain.layoutParams.width = mScreenWidth
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        if (changed) {
            this.smoothScrollTo(mMenuWidth, 0)
            mIsOnce = true
        }
    }

    private var downX: Float = 0F

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {// 按下
                downX = ev.x
            }
            MotionEvent.ACTION_UP -> {// 抬起
                val offsetX = ev.x - downX
                if (offsetX >= mScreenWidth / 3 && downX <= mScreenWidth / 6) {
                    this.smoothScrollTo(0, 0)
                } else {// 右滑
                    this.smoothScrollTo(mMenuWidth, 0)
                }
                return true
            }
        }
        return super.onTouchEvent(ev)
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)

        // 滚动因子，利用这个因子去做各种变幻的动画效果
        val factor = (1.0F * l / mMenuWidth).toFloat()

//        mMain.scaleX = 0.6F + 0.4F * factor
//        mMain.scaleY = 0.6F + 0.4F * factor
        mMain.alpha = factor

        mMenu.scaleX = 1.0F - 0.4F * factor
        mMenu.scaleY = 1.0F - 0.4F * factor
        mMenu.rotation = -30F * factor
    }
}