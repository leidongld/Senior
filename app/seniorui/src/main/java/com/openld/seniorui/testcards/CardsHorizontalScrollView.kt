package com.openld.seniorui.testcards

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.HorizontalScrollView

/**
 * author: lllddd
 * created on: 2022/7/30 21:45
 * description:卡片横向可滚动布局
 */
class CardsHorizontalScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : HorizontalScrollView(context, attrs) {
    private var mScreenWidth = 0

    private var mWidth = 0

    var mIsFold = false

    var mOnCardScrollListener: OnCardScrollListener? = null

    private var mCardCounts = 0

    private lateinit var mCardList: List<CardBean>

    init {
        mScreenWidth = context.resources.displayMetrics.widthPixels
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
    }


    private var downX: Float = 0F
    private var downScrollX = 0

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev!!.action == MotionEvent.ACTION_DOWN) {
            downX = ev.x
            downScrollX = scrollX
        } else if (ev.action == MotionEvent.ACTION_UP) {
            if (mIsFold) {
                return super.dispatchTouchEvent(ev)
            }

            val offsetX = ev.x - downX

            if (offsetX > 0F) {// 右滑
                val index = downScrollX / mScreenWidth

                if (offsetX > mScreenWidth / 5) {
                    smoothScrollTo((index - 1) * mScreenWidth, 0)
                    changeBackground(index - 1)
                    if (index - 1 in 0 until mCardCounts) {
                        mOnCardScrollListener?.onCardScrolled(index - 1)
                    }

                } else {
                    smoothScrollTo(index * mScreenWidth, 0)
                    changeBackground(index)
                    mOnCardScrollListener?.onCardScrolled(index)
                }
                return true
            } else if (offsetX < 0F) {// 左滑
                val index = downScrollX / mScreenWidth

                if (offsetX < -mScreenWidth / 5) {
                    smoothScrollTo((index + 1) * mScreenWidth, 0)
                    changeBackground(index + 1)
                    if (index + 1 in 0 until mCardCounts) {
                        mOnCardScrollListener?.onCardScrolled(index + 1)
                    }
                } else {
                    smoothScrollTo(index * mScreenWidth, 0)
                    changeBackground(index)
                    mOnCardScrollListener?.onCardScrolled(index)
                }
                return true
            } else {// 滑动距离过小

            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun changeBackground(index: Int) {
        if (index in 0 until mCardCounts) {
            setBackgroundResource(mCardList[index].image)
            background.mutate().colorFilter =
                ColorMatrixColorFilter(ColorMatrix().apply {
                    setScale(0.3F, 0.3F, 0.3F, 1F)
                })
        }
    }

    fun setCards(cardList: List<CardBean>) {
        if (cardList.isEmpty()) {
            return
        }

        this.mCardList = cardList
        this.mCardCounts = cardList.size

        if (childCount == 1 && getChildAt(0) is CardsContainerLayout) {
            (getChildAt(0) as CardsContainerLayout).setCards(mCardList)
        }

        changeBackground(0)
    }
}