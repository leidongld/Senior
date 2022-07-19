package com.openld.seniorui.testpaint.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.openld.seniorui.R

/**
 * author: lllddd
 * created on: 2022/7/16 20:10
 * description:
 */
class ZoomImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mPaint: Paint = Paint()

    private lateinit var mBitmap: Bitmap

    private lateinit var mDrawable: ShapeDrawable

    private lateinit var mShader: BitmapShader

    private val FACTOR = 3

    private val RADIUS = 200

    init {

        mBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.fruit_image17)

        var bmp = mBitmap
        bmp = Bitmap.createScaledBitmap(mBitmap, bmp.width * FACTOR, bmp.height * FACTOR, false)

        mShader = BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        mDrawable = ShapeDrawable(OvalShape())
        mDrawable.paint.shader = mShader
        // 切出矩形区域，用于绘制圆形
        mDrawable.setBounds(0, 0, RADIUS * 2, RADIUS * 2)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.drawBitmap(mBitmap, null, Rect().apply {
            left = 0
            top = 0
            right = mBitmap.width
            bottom = mBitmap.height
        }, mPaint)

        mDrawable.draw(canvas)

        postInvalidate()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
}