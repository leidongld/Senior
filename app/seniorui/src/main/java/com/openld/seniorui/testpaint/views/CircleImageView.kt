package com.openld.seniorui.testpaint.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.openld.seniorui.R

/**
 * author: lllddd
 * created on: 2022/7/16 16:34
 * description:圆形图片
 */
class CircleImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private lateinit var mInitBitmap: Bitmap
    private lateinit var mRealBitmap: Bitmap

    private var mBitmapWidth: Int = 0
    private var mBitmapHeight: Int = 0

    private var mRatio = 1.0F

    private var mScreenWidth = 0;

    private var mSide = 0

    private lateinit var mShader: Shader

    private val mPaint = Paint().apply {
        isAntiAlias = true
    }

    init {
        mInitBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.hunter)
        mBitmapWidth = mInitBitmap.width
        mBitmapHeight = mInitBitmap.height

        mRatio = 1.0F * mBitmapWidth / mBitmapHeight

        mScreenWidth = context.resources.displayMetrics.widthPixels
    }

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val wMode = MeasureSpec.getMode(widthMeasureSpec)
        val hMode = MeasureSpec.getMode(heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        mSide = width.coerceAtLeast(height)

        if (mSide > mScreenWidth * context.resources.displayMetrics.density) {
            mSide = (mScreenWidth * context.resources.displayMetrics.density).toInt()
        }

        setMeasuredDimension(mSide, mSide)

        mRealBitmap = Bitmap.createBitmap(
            mInitBitmap,
            mInitBitmap.width / 2 - mSide / 2,
            mBitmapHeight / 2 - mSide / 2,
            mSide,
            mSide
        )
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val matrix: Matrix = Matrix().apply {
            scaleX = mRatio.coerceAtLeast(1.0F / mRatio)
            scaleY = mRatio.coerceAtLeast(1.0F / mRatio)
        }

        mShader = BitmapShader(mRealBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP).apply {
//            setLocalMatrix(matrix)
        }
        mPaint.shader = mShader

        canvas!!.drawCircle(
            (mSide / 2).toFloat(),
            (mSide / 2).toFloat(),
            (mSide / 2).toFloat(),
            mPaint
        )
    }
}