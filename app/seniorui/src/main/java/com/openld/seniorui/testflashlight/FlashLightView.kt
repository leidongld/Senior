package com.openld.seniorui.testflashlight

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.openld.seniorui.R

/**
 * author: lllddd
 * created on: 2022/7/7 16:26
 * description:手电筒
 */
class FlashLightView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val RADIUS = 500F

    private var mCx = 0F
        set(value) {
            field = value
            invalidate()
        }
    private var mCy = 0F
        set(value) {
            field = value
            invalidate()
        }

    private var mOldX = 0F
    private var mOldY = 0F

    private val mColorArray = intArrayOf(
        Color.parseColor("#ccFFFFFF"),
        Color.parseColor("#88FFFFFF"),
        Color.parseColor("#66FFFFFF"),
        Color.parseColor("#44FFFFFF"),
        Color.parseColor("#22FFFFFF")
    )

//    private val mStopArray = floatArrayOf(
//        0.3F, 0.5F, 0.6F, 0.7F, 0.8F, 0.9F
//    )

    private var mLightPaint = Paint().apply {
        isAntiAlias = true
    }

    private var mBitmapPaint = Paint().apply {
        isAntiAlias = true
    }

    private lateinit var mBitmap: Bitmap

    init {
        mBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.fruit_image10)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        mBitmapPaint.colorFilter = PorterDuffColorFilter(
            Color.RED,
            PorterDuff.Mode.DST
        )
        canvas!!.drawBitmap(mBitmap, null, canvas.clipBounds, mBitmapPaint)

        canvas.drawColor(Color.parseColor("#aa000000"))

        val radialGradient =
            RadialGradient(mCx, mCy, RADIUS, mColorArray, null, Shader.TileMode.CLAMP)
        mLightPaint.shader = radialGradient

        canvas.drawCircle(mCx, mCy, RADIUS.toFloat(), mLightPaint)
    }

    private var mUserDown = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                mUserDown = true
                return true
            }
            MotionEvent.ACTION_UP -> {
                if (mUserDown) {
                    val upX = event.x
                    val upY = event.y
                    moveLight(mOldX, upX, mOldY, upY)
                    mUserDown = false
                    return true
                }
            }
        }

        return super.onTouchEvent(event)

    }

    private fun moveLight(startX: Float, endX: Float, startY: Float, endY: Float) {
        val animX = ObjectAnimator.ofFloat(this, "mCx", startX, endX)

        val animY = ObjectAnimator.ofFloat(this, "mCy", startY, endY)

        val set = AnimatorSet().apply {
            startDelay = 600
            duration = 1000
            playTogether(animX, animY)
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }

        mOldX = endX
        mOldY = endY
    }
}