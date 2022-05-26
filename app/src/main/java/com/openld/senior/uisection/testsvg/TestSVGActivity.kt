package com.openld.senior.uisection.testsvg

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.openld.senior.R

class TestSVGActivity : AppCompatActivity() {
    private lateinit var mImgSvg1: AppCompatImageView

    private lateinit var mImgSvg2: AppCompatImageView

    private lateinit var mImgSvg3: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_svgactivity)

        initWidgets()

        addListeners()
    }

    private fun initWidgets() {
        mImgSvg1 = findViewById(R.id.img_svg_1)
        mImgSvg2 = findViewById(R.id.img_svg_2)
        mImgSvg3 = findViewById(R.id.img_svg_3)
    }

    private fun addListeners() {
        mImgSvg1.setOnClickListener {
            val animX = ObjectAnimator.ofFloat(mImgSvg1, "scaleX", 1.0F, 0.8F, 1.2F, 1.0F)
            val animY = ObjectAnimator.ofFloat(mImgSvg1, "scaleY", 1.0F, 0.8F, 1.2F, 1.0F)

            val animSet = AnimatorSet().apply {
                duration = 600
                interpolator = AccelerateDecelerateInterpolator()
                playTogether(animX, animY)
                start()
            }
        }

        mImgSvg2.setOnClickListener {
            val animX = ObjectAnimator.ofFloat(mImgSvg2, "scaleX", 1.0F, 0.8F, 1.2F, 1.0F)
            val animY = ObjectAnimator.ofFloat(mImgSvg2, "scaleY", 1.0F, 0.8F, 1.2F, 1.0F)

            val animSet = AnimatorSet().apply {
                duration = 600
                interpolator = AccelerateDecelerateInterpolator()
                playTogether(animX, animY)
                start()
            }
        }

        mImgSvg3.setOnClickListener {
            val animX = ObjectAnimator.ofFloat(mImgSvg3, "scaleX", 1.0F, 0.8F, 1.2F, 1.0F)
            val animY = ObjectAnimator.ofFloat(mImgSvg3, "scaleY", 1.0F, 0.8F, 1.2F, 1.0F)

            val animSet = AnimatorSet().apply {
                duration = 600
                interpolator = AccelerateDecelerateInterpolator()
                playTogether(animX, animY)
                start()
            }
        }
    }
}