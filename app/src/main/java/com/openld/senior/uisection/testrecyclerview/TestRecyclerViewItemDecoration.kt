package com.openld.senior.uisection.testrecyclerview

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.NonNull
import androidx.core.view.marginBottom
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.openld.senior.R

/**
 * author: lllddd
 * created on: 2022/3/31 18:40
 * description:自定義分割綫
 */
class TestRecyclerViewItemDecoration(@NonNull context: Context) : RecyclerView.ItemDecoration() {
    companion object {
        private val VERTICAL = RecyclerView.VERTICAL
        private val HORIZONTAL = RecyclerView.HORIZONTAL
    }


    private val mContext = context

    private var mOrientation = VERTICAL

    private var mDivider: Drawable? = context.getDrawable(R.drawable.divider)

    private var mPaint: Paint = Paint().apply {
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
    }

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
        val left = parent.paddingLeft + 30
        val right = parent.width - parent.paddingRight - 30

        val childCount = parent.childCount

        for (index in 0 until childCount) {
            val child = parent.getChildAt(index)

            val top = (child.bottom + child.marginBottom + child.getTranslationY())
            val bottom = top + 5

            mDivider?.setBounds(left, top.toInt(), right, bottom.toInt())
            mDivider?.draw(c)
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
        if (mOrientation == HORIZONTAL) {
            mDivider?.let { outRect.set(10, 10, 10, it.intrinsicHeight) }
        } else {
            mDivider?.let { outRect.set(10, 10, it.intrinsicWidth, 10) }
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        super.onDrawOver(c, parent, state)
        val childCount = parent.childCount

        for (index in 0 until childCount) {
            val child = parent.getChildAt(index)

            if (index == 0) {
                val bitmap = BitmapFactory.decodeResource(mContext.resources, R.drawable.launcher)
                c.drawBitmap(bitmap, Rect().apply {
                    val left = 0
                    val top = 0
                    val right = 60
                    val bottom = 60
                    set(left, top, right, bottom)
                }, Rect().apply {
                    val left = 0
                    val top = 0
                    val right = 60
                    val bottom = 60
                    set(left, top, right, bottom)
                }, mPaint)
            } else if (index == 1) {


            } else if (index == 2) {

            }
        }
    }
}