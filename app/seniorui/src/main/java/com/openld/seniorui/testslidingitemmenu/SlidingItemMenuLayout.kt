package com.openld.seniorui.testslidingitemmenu

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.LinearLayout
import android.widget.Scroller

/**
 * author: lllddd
 * created on: 2022/7/1 20:40
 * description:可以侧滑的菜单条目
 */
class SlidingItemMenuLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private lateinit var mScroller: Scroller

    private var mScreenWidth: Int

    private lateinit var mViewConfiguration: ViewConfiguration

//    private var mContentWidth: Int = 0
//    private var mDelWidth: Int = 0

    init {
        orientation = LinearLayout.HORIZONTAL
        mScroller = Scroller(context, AccelerateDecelerateInterpolator(), true)
        mScreenWidth = context.resources.displayMetrics.widthPixels
        mViewConfiguration = ViewConfiguration()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (childCount == 2) {
            val txtContent = getChildAt(0)
            val txtDel = getChildAt(1)

            txtContent.layoutParams.width = mScreenWidth
            txtDel.layoutParams.width = mScreenWidth / 4
            super.onMeasure((mScreenWidth * 1.25F).toInt(), heightMeasureSpec)
        } else {
            super.onMeasure(0, 0)
        }

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
    }

//    override fun onFinishInflate() {
//        super.onFinishInflate()
//        mContentWidth = getChildAt(0).width
//        mDelWidth = getChildAt(1).width
//    }

    override fun onDragEvent(event: DragEvent?): Boolean {
        return super.onDragEvent(event)
    }

    private var mDownX = 0F
    private var mDownY = 0F
    private var mMoveRight = false
    private var mMoveLeft = false

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownX = ev.x
                mDownY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                val offsetX = ev.x - mDownX
                if (offsetX <= -mScreenWidth / 5) {// 左滑超过屏幕宽度的三分之一
                    mMoveLeft = true
                } else if (offsetX >= mScreenWidth / 5) {// 右滑超过屏幕宽度的三分之一
                    mMoveRight = true
                } else {
                    mMoveLeft = false
                    mMoveRight = false
                }
//                return true
            }
            MotionEvent.ACTION_UP -> {
                if (mMoveLeft && x >= 0) {
                    mMoveLeft = false
//                    this.scrollTo(mScreenWidth / 4, 0)
                    val animator =
                        ObjectAnimator.ofFloat(
                            this,
                            "translationX",
                            0F,
                            -1.0F * mScreenWidth / 4
                        )
                    animator.interpolator = OvershootInterpolator()
                    animator.duration = 300
                    animator.start()
                    return true
                } else if (mMoveRight && x < 0) {
                    mMoveRight = false
                    val animator =
                        ObjectAnimator.ofFloat(
                            this,
                            "translationX",
                            0F,
                            1.0F * mScreenWidth / 20,
                            0F
                        )
                    animator.interpolator = OvershootInterpolator()
                    animator.duration = 300
                    animator.start()
//                    this.scrollTo(0, 0)
                    return true
                }
            }
        }

        return super.dispatchTouchEvent(ev)
    }
}