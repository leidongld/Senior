package com.openld.seniorui.testdust.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.openld.seniorui.testdust.particle.Particle
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

/**
 * author: lllddd
 * created on: 2022/11/8 20:14
 * description:尘埃View
 */
class DustView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mPaint: Paint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL_AND_STROKE
        isAntiAlias = true
    }

    private var mParticleList = mutableListOf<Particle>()

    private var mCenterX: Int = 0
    private var mCenterY: Int = 0

    private var mDustRadius: Int = 0

    private var mAnimator: ValueAnimator = ValueAnimator.ofInt(0, 100).apply {
        duration = 100000
        interpolator = LinearInterpolator()
        repeatCount = ValueAnimator.INFINITE
        addUpdateListener {
            updateParticles()
            invalidate()
        }
    }

    private fun updateParticles() {
        for (index in 0 until mParticleList.size) {
            val particle = mParticleList[index]

            particle.x += particle.speed * particle.cosTheta
            particle.y += particle.speed * particle.sinTheta

            val isOutBound =
                (particle.x - mCenterX) * (particle.x - mCenterX) + (particle.y - mCenterY) * (particle.y - mCenterY) > mDustRadius * mDustRadius
            if (isOutBound) {
                particle.x = mCenterX + 300 * particle.cosTheta
                particle.y = mCenterY + 300 * particle.sinTheta
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        mCenterX = measuredWidth / 2
        mCenterY = measuredHeight / 2

        mDustRadius = sqrt((mCenterX * mCenterX + mCenterY * mCenterY).toDouble()).toInt()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (index in 0 until mParticleList.size) {
            val particle = mParticleList[index]
            canvas!!.drawCircle(particle.x, particle.y, particle.radius, mPaint)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        for (index in 0 until 2000) {
            val random = Random.Default

            val radius = 3.0F
            val speed = 1.0F + 2.0F * random.nextFloat()

            val randomAngle = random.nextInt(360)
            val sinTheta = sin(randomAngle * Math.PI / 180)
            val cosTheta = cos(randomAngle * Math.PI / 180)

            val x = mCenterX + 300F * cosTheta
            val y = mCenterY + 300F * sinTheta

            val particle = Particle(
                x.toFloat(), y.toFloat(), radius, speed, sinTheta.toFloat(), cosTheta.toFloat()
            )
            mParticleList.add(particle)
        }

        mAnimator.start()
    }
}