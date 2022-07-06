package com.openld.seniorui.testdrawable

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi

/**
 * author: lllddd
 * created on: 2022/7/6 9:28
 * description:四叶草自定义Drawable
 */
@RequiresApi(Build.VERSION_CODES.M)
class CloverDrawable(builder: Builder) : Drawable() {
    private lateinit var mContext: Context

    private var mImg1: Int = -1
    private var mImg2: Int = -1
    private var mImg3: Int = -1
    private var mImg4: Int = -1

    private lateinit var mBitmap1: Bitmap
    private lateinit var mBitmap2: Bitmap
    private lateinit var mBitmap3: Bitmap
    private lateinit var mBitmap4: Bitmap

    private var mMargin = 0

    private var mStrokeWidth = 0
    private var mStrokeColor = 0

    private var mRadius = 0

    private val mBitmapPaint = Paint().apply {
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        style = Paint.Style.FILL
    }

    private val mStrokePaint = Paint().apply {
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        style = Paint.Style.STROKE
    }

    init {
        this.mContext = builder.context!!

        this.mImg1 = builder.leftTopImage
        this.mImg2 = builder.rightTopImage
        this.mImg3 = builder.leftBottomImage
        this.mImg4 = builder.rightBottomImage

        this.mStrokeWidth = builder.strokeWidth
        this.mStrokeColor = builder.strokeColor
        mStrokePaint.color = mContext.getColor(mStrokeColor)
        mStrokePaint.strokeWidth = mStrokeWidth.toFloat()

        this.mMargin = builder.margin
        this.mRadius = builder.radius

        obtainBitmaps(mContext.resources)
    }

    private fun obtainBitmaps(resources: Resources) {
        if (mImg1 > 0) {
            mBitmap1 = BitmapFactory.decodeResource(resources, mImg1)
        }
        if (mImg2 > 0) {
            mBitmap2 = BitmapFactory.decodeResource(resources, mImg2)
        }
        if (mImg3 > 0) {
            mBitmap3 = BitmapFactory.decodeResource(resources, mImg3)
        }
        if (mImg4 > 0) {
            mBitmap4 = BitmapFactory.decodeResource(resources, mImg4)
        }
    }

    override fun draw(canvas: Canvas) {
        val rectF = RectF(copyBounds())

        // 第一个图片
        canvas.save()
        val rectF1 = RectF().apply {
            left = mMargin.toFloat()
            top = mMargin.toFloat()
            right = mMargin + (rectF.right - 3F * mMargin) / 2F
            bottom = mMargin + (rectF.bottom - 3F * mMargin) / 2F
        }
        val path1 = Path()
        path1.addRoundRect(rectF1, mRadius.toFloat(), mRadius.toFloat(), Path.Direction.CCW)
        canvas.clipPath(path1)
        canvas.drawBitmap(mBitmap1, null, rectF1, mBitmapPaint)
        canvas.drawRoundRect(
            rectF1.left + mStrokeWidth / 2,
            rectF1.top + mStrokeWidth / 2,
            rectF1.right - mStrokeWidth / 2,
            rectF1.bottom - mStrokeWidth / 2,
            mRadius.toFloat(),
            mRadius.toFloat(),
            mStrokePaint
        )
        canvas.restore()

        // 第二个图片
        canvas.save()
        val rectF2 = RectF().apply {
            left = 2 * mMargin + (rectF.right - 3F * mMargin) / 2F
            top = mMargin.toFloat()
            right = rectF.right - mMargin
            bottom = mMargin + (rectF.bottom - 3F * mMargin) / 2F
        }
        val path2 = Path()
        path2.addRoundRect(rectF2, mRadius.toFloat(), mRadius.toFloat(), Path.Direction.CCW)
        canvas.clipPath(path2)
        canvas.drawBitmap(mBitmap2, null, rectF2, mBitmapPaint)
        canvas.drawRoundRect(
            rectF2.left + mStrokeWidth / 2,
            rectF2.top + mStrokeWidth / 2,
            rectF2.right - mStrokeWidth / 2,
            rectF2.bottom - mStrokeWidth / 2,
            mRadius.toFloat(),
            mRadius.toFloat(),
            mStrokePaint
        )
        canvas.restore()

        // 第三个图片
        canvas.save()
        val rectF3 = RectF().apply {
            left = mMargin.toFloat()
            top = 2 * mMargin + (rectF.bottom - 3F * mMargin) / 2F
            right = mMargin + (rectF.right - 3F * mMargin) / 2F
            bottom = rectF.bottom - mMargin
        }
        val path3 = Path()
        path3.addRoundRect(rectF3, mRadius.toFloat(), mRadius.toFloat(), Path.Direction.CCW)
        canvas.clipPath(path3)
        canvas.drawBitmap(mBitmap3, null, rectF3, mBitmapPaint)
        canvas.drawRoundRect(
            rectF3.left + mStrokeWidth / 2,
            rectF3.top + mStrokeWidth / 2,
            rectF3.right - mStrokeWidth / 2,
            rectF3.bottom - mStrokeWidth / 2,
            mRadius.toFloat(),
            mRadius.toFloat(),
            mStrokePaint
        )
        canvas.restore()

        // 第四个图片
        canvas.save()
        val rectF4 = RectF().apply {
            left = 2 * mMargin + (rectF.right - 3F * mMargin) / 2F
            top = 2 * mMargin + (rectF.bottom - 3F * mMargin) / 2F
            right = rectF.right - mMargin
            bottom = rectF.bottom - mMargin
        }
        val path4 = Path()
        path4.addRoundRect(rectF4, mRadius.toFloat(), mRadius.toFloat(), Path.Direction.CCW)
        canvas.clipPath(path4)
        canvas.drawBitmap(mBitmap4, null, rectF4, mBitmapPaint)
        canvas.drawRoundRect(
            rectF4.left + mStrokeWidth / 2,
            rectF4.top + mStrokeWidth / 2,
            rectF4.right - mStrokeWidth / 2,
            rectF4.bottom - mStrokeWidth / 2,
            mRadius.toFloat(),
            mRadius.toFloat(),
            mStrokePaint
        )
        canvas.restore()
    }

    override fun setAlpha(alpha: Int) {
        mBitmapPaint.alpha = alpha
        invalidateSelf()
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mBitmapPaint.colorFilter = colorFilter
        invalidateSelf()
    }

    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("PixelFormat.TRANSPARENT", "android.graphics.PixelFormat")
    )
    override fun getOpacity(): Int {
        return PixelFormat.TRANSPARENT
    }

    class Builder constructor(@NonNull context: Context) {
        internal var context: Context? = null

        internal var leftTopImage = -1
        internal var rightTopImage = -1
        internal var leftBottomImage = -1
        internal var rightBottomImage = -1

        internal var strokeColor = 0
        internal var strokeWidth = 0
        internal var margin = 0

        internal var radius = 0

        init {
            this.context = context
        }

        internal fun leftTopImage(image: Int): Builder {
            if (image > -1) {
                this.leftTopImage = image
            }
            return this
        }

        internal fun rightTopImage(image: Int): Builder {
            if (image > -1) {
                this.rightTopImage = image
            }
            return this
        }

        internal fun leftBottomImage(image: Int): Builder {
            if (image > -1) {
                this.leftBottomImage = image
            }
            return this
        }

        internal fun rightBottomImage(image: Int): Builder {
            if (image > -1) {
                this.rightBottomImage = image
            }
            return this
        }

        internal fun stroke(color: Int, width: Int): Builder {
            if (color > 0) {
                this.strokeColor = color
            }
            if (width > 0) {
                this.strokeWidth = width
            }
            return this
        }

        internal fun margin(margin: Int): Builder {
            if (margin > 0) {
                this.margin = margin
            }
            return this
        }

        internal fun radius(radius: Int): Builder {
            if (radius > 0) {
                this.radius = radius
            }
            return this
        }

        internal fun build(): CloverDrawable {
            return CloverDrawable(this)
        }
    }
}