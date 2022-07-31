package com.openld.seniorui.testplanet.planet

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import com.openld.seniorui.R

/**
 * author: lllddd
 * created on: 2022/7/21 15:03
 * description:Tag星球布局
 */
class TagPlanetView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr), Runnable {
    private var mScreenWidth = 0

    private lateinit var mTagPlanet: TagPlanet

    private lateinit var mLayoutParams: MarginLayoutParams

    @ColorInt
    private var mLightColor = 0

    @ColorInt
    private var mDarkColor = 0

    init {
        mScreenWidth = context.resources.displayMetrics.widthPixels

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TagPlanetView)

        mLightColor =
            typedArray.getColor(R.styleable.TagPlanetView_lightColor, Color.parseColor("#aaff0000"))
        mDarkColor =
            typedArray.getColor(R.styleable.TagPlanetView_darkColor, Color.parseColor("#550000ff"))

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        mLayoutParams = layoutParams as MarginLayoutParams

        var mWidth = 0
        var mHeight = 0

        var maxSide = width.coerceAtLeast(height)

        if (widthMode == MeasureSpec.EXACTLY || heightMode == MeasureSpec.EXACTLY) {

        } else {
            maxSide = mScreenWidth - mLayoutParams.leftMargin - mLayoutParams.rightMargin
        }

        setMeasuredDimension(maxSide, maxSide)

        measureChildren(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0..childCount) {
            val view = getChildAt(i)

            if (view != null && view.visibility != View.GONE && mTagPlanet.mTagList.isNotEmpty()) {
                val tag = mTagPlanet.mTagList[i]

                val scale = tag.scale

                view.scaleX = scale
                view.scaleY = scale

                val left = tag.mPointF.x - view.measuredWidth / 2
                val top = tag.mPointF.y - view.measuredHeight / 2

                view.layout(
                    left.toInt(),
                    top.toInt(),
                    (left + view.measuredWidth).toInt(),
                    (top + view.measuredHeight).toInt()
                )
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun run() {
        // TODO:
    }
}