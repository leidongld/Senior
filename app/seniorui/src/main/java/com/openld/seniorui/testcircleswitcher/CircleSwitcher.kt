package com.openld.seniorui.testcircleswitcher

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.annotation.NonNull
import com.openld.seniorui.R

/**
 * author: lllddd
 * created on: 2022/7/2 15:59
 * description:圆形开关
 */
class CircleSwitcher @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    // 默认打开颜色
    private val OPEN_COLOR = Color.parseColor("#ff48ea8b")

    // 默认关闭颜色
    private val CLOSE_COLOR = Color.parseColor("#ffff4651")

    // 关闭颜色
    private var mCloseColor: Int

    // 打开颜色
    private var mOpenColor: Int

    // 屏幕density
    private var mDensity: Float = 0F

    // 外环画笔
    private var mPaint: Paint = Paint().apply {
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
    }

    // 内点画笔
    private var mPaintDot: Paint = Paint().apply {
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.FILL
        strokeJoin = Paint.Join.ROUND
    }

    // 是否选中的监听器
    private var mCheckChangedListener: OnCheckChangedListener? = null

    // 组件宽度
    private var mWidth = 0

    // 组件高度
    private var mHeight = 0

    // 是否打开的状态
    open var mChecked = false
        @SuppressLint("Recycle")
        set(isChecked) {
            field = isChecked

            post {
                if (field) {
                    val anim1 =
                        ObjectAnimator.ofArgb(
                            this,
                            "mCurrentColor",
                            mCloseColor,
                            mOpenColor
                        )

                    val anim2 = ObjectAnimator.ofFloat(this, "mDotRadius", 0F, mWidth / 5F).apply {
                        interpolator = AccelerateDecelerateInterpolator()
                    }

                    AnimatorSet().apply {
                        duration = 160
                        playTogether(anim1, anim2)
                        start()
                    }
                } else {
                    val anim1 =
                        ObjectAnimator.ofArgb(
                            this,
                            "mCurrentColor",
                            mOpenColor,
                            mCloseColor
                        )

                    val anim2 = ObjectAnimator.ofFloat(this, "mDotRadius", mWidth / 5F, 0F).apply {
                        interpolator = AccelerateDecelerateInterpolator()
                    }

                    AnimatorSet().apply {
                        duration = 160
                        playTogether(anim1, anim2)
                        start()
                    }
                }
            }
        }

    // 当亲颜色
    private var mCurrentColor: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    // 内点半径
    private var mDotRadius: Float = 80F
        set(value) {
            field = value
            invalidate()
        }

    /**
     * 初始化
     */
    init {
        // 获取屏幕density
        mDensity = context.resources.displayMetrics.density

        val styleAttrs = context.obtainStyledAttributes(attrs, R.styleable.CircleSwitcher)
        mCloseColor =
            styleAttrs.getColor(
                R.styleable.CircleSwitcher_close_color,
                CLOSE_COLOR
            )

        mOpenColor =
            styleAttrs.getColor(
                R.styleable.CircleSwitcher_open_color,
                OPEN_COLOR
            )

        mChecked = styleAttrs.getBoolean(R.styleable.CircleSwitcher_checked, false)

        styleAttrs.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(widthMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(widthMeasureSpec)

        when (widthMode) {
            MeasureSpec.EXACTLY -> {
                mWidth = width
                mHeight = mWidth
            }
            MeasureSpec.AT_MOST -> {
                mWidth = (35 * mDensity).toInt()
                mHeight = mWidth
            }
            else -> {
                mWidth = (35 * mDensity).toInt()
                mHeight = mWidth
            }
        }

        setMeasuredDimension(mWidth, mHeight)

        mPaint.strokeWidth = (mWidth / 9).toFloat()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = mCurrentColor
        mPaintDot.color = mCurrentColor

        canvas!!.drawCircle(
            (mWidth / 2).toFloat(), (mHeight / 2).toFloat(),
            (mWidth / 3).toFloat(), mPaint
        )
        canvas.drawCircle((mWidth / 2).toFloat(), (mHeight / 2).toFloat(), mDotRadius, mPaintDot)
    }

    // 用户是否曾经按下了该组件
    private var mIsUserDown = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                mIsUserDown = true
                return true
            }
            MotionEvent.ACTION_UP -> {
                if (mIsUserDown) {
                    mChecked = !mChecked
                    mCheckChangedListener?.onCheckChanged(mChecked)

                    mIsUserDown = false
                }
                return true
            }
        }

        return super.onTouchEvent(event)
    }

    /**
     * 设置选中状态改变时的监听器
     */
    fun setOnCheckChangedListener(@NonNull listener: OnCheckChangedListener) {
        this.mCheckChangedListener = listener
    }

    /**
     * 选中状态监听器
     */
    interface OnCheckChangedListener {
        /**
         * 选中状态变化回调
         */
        fun onCheckChanged(isCheck: Boolean)
    }
}