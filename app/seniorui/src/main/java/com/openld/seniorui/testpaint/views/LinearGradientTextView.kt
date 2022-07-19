package com.openld.seniorui.testpaint.views

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.TextView

/**
 * author: lllddd
 * created on: 2022/7/16 18:47
 * description:
 */
@SuppressLint("AppCompatCustomView")
class LinearGradientTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : TextView(context, attrs) {

    override fun onFinishInflate() {
        super.onFinishInflate()
        val anim = ObjectAnimator.ofFloat(this, "offset", -1000F, 0F, 2000F).apply {
            duration = 5000
            interpolator = LinearInterpolator()
            repeatCount = Animation.INFINITE
        }
        anim.start()
    }

    private var offset = 0F
        set(value) {
            field = value
            invalidate()
        }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val shader = LinearGradient(
            0f + offset,
            0f,
            800f + offset,
            400f,
            intArrayOf(
                Color.parseColor("#11ffffff"),
                Color.parseColor("#66ffffff"),
                Color.parseColor("#ffffffff"),
                Color.parseColor("#66ffffff"),
                Color.parseColor("#11ffffff")
            ),
            floatArrayOf(0f, 0.2f, 0.5f, 0.8f, 1f),
            Shader.TileMode.CLAMP
        )
        paint.shader = shader
    }
}