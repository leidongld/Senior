package com.openld.senior.uisection.testrecyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.NonNull
import androidx.core.view.marginBottom
import androidx.core.view.marginRight
import androidx.recyclerview.widget.RecyclerView
import com.openld.senior.R

/**
 * author: lllddd
 * created on: 2022/3/31 18:40
 * description:自定義分割綫
 */
class TestRecyclerViewItemDecoration(@NonNull context: Context) : RecyclerView.ItemDecoration() {
    companion object {
        private val VERTICAL = 0
        private val HORIZONTAL = 1
    }

    private var mOrientation = VERTICAL

    private var mDivider: Drawable? = context.getDrawable(R.drawable.divider)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == VERTICAL) {
            onDrawVertical(c, parent, state)
        } else {
            onDrawHorizontal(c, parent, state)
        }
    }

    private fun onDrawVertical(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom

        val childCount = parent.childCount

        for (index in 0 until childCount) {
            val child = parent.getChildAt(index)

            val left = child.right + child.marginRight + child.getTranslationX()
            val right = left + (mDivider?.intrinsicWidth ?: 0)

            mDivider?.setBounds(left.toInt(), top, right.toInt(), bottom)
            mDivider?.draw(c)
        }
    }

    private fun onDrawHorizontal(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft + 20
        val right = parent.width - parent.paddingRight - 20

        val childCount = parent.childCount

        for (index in 0 until childCount) {
            val child = parent.getChildAt(index)

            val top = (child.bottom + child.marginBottom + child.getTranslationY())
            val bottom = top + (mDivider?.intrinsicHeight ?: 0)

            mDivider?.setBounds(left, top.toInt(), right, bottom.toInt())
            mDivider?.draw(c)

            if (index == 0) {// 销量第一
                // TODO:  
            }

            if (index == 1) {// 销量第二
                // TODO:  
            }

            if (index == 2) {// 销量第三
                // TODO:  
            }
        }
    }

    fun setOrientation(orientation: Int) {
        if (mOrientation != VERTICAL && mOrientation != HORIZONTAL) {
            throw RuntimeException("方向错误！！！")
        }
        this.mOrientation = orientation
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (mOrientation == VERTICAL) {
            mDivider?.let { outRect.set(0, 0, 10, it.intrinsicHeight) }
        } else {
            mDivider?.let { outRect.set(0, 0, it.intrinsicWidth, 0) }
        }
    }
}