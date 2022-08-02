package com.openld.seniorui.testwave

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * author: lllddd
 * created on: 2022/8/2 14:12
 * description:圆形的线性布局
 */
class CircleLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private var mWidth = 0;
    private var mHeight = 0;

    private var mPaint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 5F
        color = Color.parseColor("#6495ed")
        style = Paint.Style.STROKE
    }

    private var mCirclePath = Path()

    override fun dispatchDraw(canvas: Canvas?) {
        mWidth = measuredWidth
        mHeight = measuredHeight

        canvas!!.save()

        mCirclePath.addCircle(
            (mWidth shr 1).toFloat(),
            (mHeight shr 1).toFloat(),
            (mWidth shr 1).coerceAtMost(mHeight shr 1).toFloat(),
            Path.Direction.CCW
        )
        canvas.clipPath(mCirclePath)

        canvas.drawCircle(
            (mWidth shr 1).toFloat(),
            (mHeight shr 1).toFloat(),
            (mWidth shr 1).coerceAtMost(mHeight shr 1).toFloat() - 5F,
            mPaint
        )

        super.dispatchDraw(canvas)
        canvas.restore()
    }
}