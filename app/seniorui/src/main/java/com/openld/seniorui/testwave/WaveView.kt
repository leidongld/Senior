package com.openld.seniorui.testwave

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import com.openld.seniorui.R

/**
 * author: lllddd
 * created on: 2022/8/1 21:34
 * description:波浪View
 */
class WaveView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    // View宽高
    private var mWidth = 0
    private var mHeight = 0

    // 第一个波浪的起点x y坐标
    private var mWaveBaseY1 = 0
    private var mWaveBaseX1 = 0

    // 第二个波浪的起点x y坐标
    private var mWaveBaseY2 = 0
    private var mWaveBaseX2 = 0

    // 第三个波浪的起点x y坐标
    private var mWaveBaseY3 = 0
    private var mWaveBaseX3 = 0

    // 波浪画笔
    private val mWavePaint1 = Paint()
    private val mWavePaint2 = Paint()
    private val mWavePaint3 = Paint()
    private val mPaintBoat = Paint()

    private var mBmpBoat: Bitmap? = null

    // 波浪Path
    private val mWavePath1 = Path()
    private val mWavePath2 = Path()
    private val mWavePath3 = Path()

    // 用于动画移动波浪以达到动效的作用，x轴偏移
    private var xOffset1 = -WAVE_LENGTH_1.toFloat()
    private var xOffset2 = -WAVE_LENGTH_2.toFloat()
    private var xOffset3 = -WAVE_LENGTH_2.toFloat()
    private var boatOffset = 0

    /**
     * 设置第一个波浪的x偏移
     *
     * @param boatOffset x偏移
     */
    fun setBoatOffset(boatOffset: Int) {
        this.boatOffset = boatOffset
        invalidate()
    }


    /**
     * 设置第一个波浪的x偏移
     *
     * @param xOffset x偏移
     */
    fun setXOffset1(xOffset: Float) {
        this.xOffset1 = xOffset
        invalidate()
    }

    /**
     * 设置第二个波浪的x偏移
     *
     * @param xOffset x偏移
     */
    fun setXOffset2(xOffset: Float) {
        this.xOffset2 = xOffset
        invalidate()
    }

    /**
     * 设置第三个波浪的x偏移
     *
     * @param xOffset x偏移
     */
    fun setXOffset3(xOffset: Float) {
        this.xOffset3 = xOffset
        invalidate()
    }

    @SuppressLint("Recycle")
    private fun configAnimators() {
        val animator1 = ObjectAnimator.ofFloat(this, "xOffset1", 0f, 1f * WAVE_LENGTH_1)
        animator1.duration = 2600
        animator1.repeatCount = ValueAnimator.INFINITE
        animator1.interpolator = LinearInterpolator()

        val animator2 = ObjectAnimator.ofFloat(this, "xOffset2", 0f, 1f * WAVE_LENGTH_2)
        animator2.duration = 3200
        animator2.repeatCount = ValueAnimator.INFINITE
        animator2.interpolator = LinearInterpolator()

        val animator3 = ObjectAnimator.ofFloat(this, "xOffset3", 0f, 1f * WAVE_LENGTH_3)
        animator3.duration = 4000
        animator3.repeatCount = ValueAnimator.INFINITE
        animator3.interpolator = LinearInterpolator()

        val animatorBoat = ObjectAnimator.ofInt(this, "boatOffset", 0, 100, 0)
        animatorBoat.duration = 3000
        animatorBoat.repeatCount = ValueAnimator.INFINITE
        animatorBoat.interpolator = AccelerateDecelerateInterpolator()

        animator1.start()
        animator2.start()
        animator3.start()
        animatorBoat.start()
    }

    /**
     * 初始化画笔
     */
    private fun initPaints() {
        mWavePaint1.color = Color.parseColor("#6495ed")
        mWavePaint1.strokeWidth = 3f
        mWavePaint1.isAntiAlias = true
        mWavePaint1.xfermode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)
        mWavePaint1.style = Paint.Style.FILL_AND_STROKE

        mWavePaint2.color = Color.parseColor("#00bfff")
        mWavePaint2.strokeWidth = 3f
        mWavePaint2.isAntiAlias = true
        mWavePaint2.xfermode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)
        mWavePaint2.style = Paint.Style.FILL_AND_STROKE

        mWavePaint3.color = Color.parseColor("#87ceeb")
        mWavePaint3.strokeWidth = 3f
        mWavePaint3.isAntiAlias = true
        mWavePaint3.xfermode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)
        mWavePaint3.style = Paint.Style.FILL_AND_STROKE

        mPaintBoat.isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight

        // 初始化曲线起始坐标
        mWaveBaseX1 = -WAVE_LENGTH_1
        mWaveBaseY1 = mHeight shr 1

        mWaveBaseX2 = -WAVE_LENGTH_2
        mWaveBaseY2 = mHeight shr 1

        mWaveBaseX3 = -WAVE_LENGTH_3
        mWaveBaseY3 = mHeight shr 1
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawWave1(canvas)
        drawWave2(canvas)
        drawWave3(canvas)
        drawBoat(canvas)
    }

    /**
     * 画帆船
     *
     * @param canvas 画布
     */
    private fun drawBoat(canvas: Canvas) {
        if (mBmpBoat == null) {
            return
        }

        // 绘制帆船
        val rect = Rect(
            (mWidth / 3 - 0.8F * mBmpBoat!!.width / 2).toInt(),
            (mHeight / 2 - 0.8F * mBmpBoat!!.height + boatOffset).toInt(),
            (mWidth / 3 + 0.8F * mBmpBoat!!.width / 2).toInt(),
            (mHeight / 2 + 0.8F * boatOffset).toInt()
        )

        canvas.drawBitmap(mBmpBoat!!, null, rect, mPaintBoat)
    }

    /**
     * 画第三个波浪
     *
     * @param canvas 画布
     */
    private fun drawWave3(canvas: Canvas) {
        mWavePath3.reset()
        mWavePath3.moveTo(mWaveBaseX3 + xOffset3, mWaveBaseY3.toFloat())

        // 画上方曲线
        var i: Int = 0
        while (i <= mWidth + WAVE_LENGTH_3) {
            mWavePath3.rQuadTo(
                QUARTER_WAVE_LENGTH_3.toFloat(),
                -170f,
                HALF_WAVE_LENGTH_3.toFloat(),
                0f
            )
            mWavePath3.rQuadTo(
                QUARTER_WAVE_LENGTH_3.toFloat(),
                170f,
                HALF_WAVE_LENGTH_3.toFloat(),
                0f
            )
            i += WAVE_LENGTH_3
        }

        // 连接下方成封闭形状
        mWavePath3.lineTo((mWaveBaseX3 + i * WAVE_LENGTH_3).toFloat(), mHeight.toFloat())
        mWavePath3.lineTo(mWaveBaseX3.toFloat(), mHeight.toFloat())
        mWavePath3.close()

        canvas.drawPath(mWavePath3, mWavePaint3)
    }

    /**
     * 画第二个波浪
     *
     * @param canvas 画布
     */
    private fun drawWave2(canvas: Canvas) {
        mWavePath2.reset()
        mWavePath2.moveTo(mWaveBaseX2 + xOffset2, mWaveBaseY2.toFloat())

        // 画上方曲线
        var i: Int = 0
        while (i <= mWidth + WAVE_LENGTH_2) {
            mWavePath2.rQuadTo(
                QUARTER_WAVE_LENGTH_2.toFloat(),
                -120f,
                HALF_WAVE_LENGTH_2.toFloat(),
                0f
            )
            mWavePath2.rQuadTo(
                QUARTER_WAVE_LENGTH_2.toFloat(),
                120f,
                HALF_WAVE_LENGTH_2.toFloat(),
                0f
            )
            i += WAVE_LENGTH_2
        }

        // 连接下方成封闭形状
        mWavePath2.lineTo((mWaveBaseX2 + i * WAVE_LENGTH_2).toFloat(), mHeight.toFloat())
        mWavePath2.lineTo(mWaveBaseX2.toFloat(), mHeight.toFloat())
        mWavePath2.close()

        canvas.drawPath(mWavePath2, mWavePaint2)
    }

    /**
     * 画第一个波浪
     *
     * @param canvas 画布
     */
    private fun drawWave1(canvas: Canvas) {
        mWavePath1.reset()
        mWavePath1.moveTo(mWaveBaseX1 + xOffset1, mWaveBaseY1.toFloat())

        // 画上方曲线
        var i: Int = 0
        while (i <= mWidth + WAVE_LENGTH_1) {
            mWavePath1.rQuadTo(
                QUARTER_WAVE_LENGTH_1.toFloat(),
                -200f,
                HALF_WAVE_LENGTH_1.toFloat(),
                0f
            )
            mWavePath1.rQuadTo(
                QUARTER_WAVE_LENGTH_1.toFloat(),
                200f,
                HALF_WAVE_LENGTH_1.toFloat(),
                0f
            )
            i += WAVE_LENGTH_1
        }

        // 连接下方成封闭形状
        mWavePath1.lineTo((mWaveBaseX1 + i * WAVE_LENGTH_1).toFloat(), mHeight.toFloat())
        mWavePath1.lineTo(mWaveBaseX1.toFloat(), mHeight.toFloat())
        mWavePath1.close()

        canvas.drawPath(mWavePath1, mWavePaint1)
    }

    companion object {
        // 波浪一：波长、半波长、四分之一波长
        private const val WAVE_LENGTH_1 = 1500
        private const val HALF_WAVE_LENGTH_1 = WAVE_LENGTH_1 shr 1
        private const val QUARTER_WAVE_LENGTH_1 = HALF_WAVE_LENGTH_1 shr 1

        // 波浪二：波长、半波长、四分之一波长
        private const val WAVE_LENGTH_2 = 2000
        private const val HALF_WAVE_LENGTH_2 = WAVE_LENGTH_2 shr 1
        private const val QUARTER_WAVE_LENGTH_2 = HALF_WAVE_LENGTH_2 shr 1

        // 波浪三：波长、半波长、四分之一波长
        private const val WAVE_LENGTH_3 = 3200
        private const val HALF_WAVE_LENGTH_3 = WAVE_LENGTH_3 shr 1
        private const val QUARTER_WAVE_LENGTH_3 = HALF_WAVE_LENGTH_3 shr 1
    }

    init {
        initPaints()
        if (context != null) {
            mBmpBoat = BitmapFactory.decodeResource(context.resources, R.drawable.boat)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        configAnimators()
    }
}