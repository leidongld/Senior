package com.openld.seniorui.testmaterialdesignanimation

import android.animation.AnimatorSet
import android.os.Bundle
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.openld.seniorui.R

/**
 * MaterialDesign动画测试
 *
 * 1.触摸反馈  水波纹
 * 2.Reveal Effect 揭露效果
 * 3.Activity Transition 页面转场效果
 * 4.Curved Motion 曲线运动
 * 5.View State Change 视图的状态改变
 */
class TestMDAnimActivity : AppCompatActivity() {
    private lateinit var mClyContainer: ConstraintLayout

    private lateinit var mBtnReveal1: AppCompatButton

    private lateinit var mImgBg: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_mdanim)

        initWidgets()

        addListeners()
    }

    private fun initWidgets() {
        mClyContainer = findViewById(R.id.cly_container)
        mBtnReveal1 = findViewById(R.id.btn_reveal1)
        mImgBg = findViewById(R.id.img_bg)

        mClyContainer.post {
            // 这两个动画只有前面的可以执行，所以采用该方式想实现两个水波纹碰撞效果是不可行的
            val anim2 = ViewAnimationUtils.createCircularReveal(
                mClyContainer,
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                10F,
                2000F
            )

            val anim1 = ViewAnimationUtils.createCircularReveal(
                mClyContainer,
                100,
                100,
                10F,
                2000F
            )

            val animSet = AnimatorSet().apply {
                duration = 1000
                interpolator = AccelerateDecelerateInterpolator()
                playTogether(anim1, anim2)
                start()
            }
        }
    }

    private fun addListeners() {
        mBtnReveal1.setOnClickListener {
            ViewAnimationUtils.createCircularReveal(it, it.width / 2, it.height / 2, 10F, 2000F)
                .apply {
                    duration = 1000
                    interpolator = AccelerateDecelerateInterpolator()
                    start()
                }
        }

        mImgBg.setOnClickListener {
            ViewAnimationUtils.createCircularReveal(
                mImgBg,
                it.width / 2,
                it.height / 2,
                10F,
                2000F
            ).apply {
                duration = 1000
                interpolator = AccelerateDecelerateInterpolator()
                start()
            }
        }
    }
}