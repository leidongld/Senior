package com.openld.senior.uisection.testtransition

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import com.openld.senior.R
import com.openld.senior.utils.PageUtils

/**
 * 转场动画效果
 */
class TestTransitionActivity : AppCompatActivity() {
    private lateinit var mBtnTransition1: AppCompatButton

    private lateinit var mImgFruit: AppCompatImageView

    private lateinit var mBtnSlide: AppCompatButton

    private lateinit var mBtnExplode: AppCompatButton

    private lateinit var mBtnFade: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        // 设置允许使用转场动画，也可以在style中声明，控制的粒度会更粗
//        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS or Window.FEATURE_ACTIVITY_TRANSITIONS)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_transmition)

        initWidgets()

        addListeners()
    }

    private fun initWidgets() {
        mBtnTransition1 = findViewById(R.id.btn_transition1)
        mImgFruit = findViewById(R.id.img_fruit)

        mBtnSlide = findViewById(R.id.btn_slide)
        mBtnExplode = findViewById(R.id.btn_explode)
        mBtnFade = findViewById(R.id.btn_fade)
    }

    private fun addListeners() {
        mBtnTransition1.setOnClickListener {
            PageUtils.jumpToPage(this, TestTransitionSecondActivityActivity::class.java)
            // 这种写法2.x就有了，属于很旧的写法了
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }

        mImgFruit.setOnClickListener {
            val aoc: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                mImgFruit,
                "transitionFruitImage"
            )
            val intent = Intent(this, TestTransitionSecondActivityActivity::class.java)
            startActivity(intent, aoc.toBundle())
        }

        mBtnSlide.setOnClickListener {
            val slide: Slide = Slide().apply {
                duration = 600
            }

            window.exitTransition = slide
            window.enterTransition = slide

            val intent = Intent(this, TestTransitionSecondActivityActivity::class.java)
            intent.putExtra("transitionType", "slide")
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

        mBtnExplode.setOnClickListener {
            val explode: Explode = Explode().apply {
                duration = 800
            }
            window.exitTransition = explode
            window.enterTransition = explode

            val intent = Intent(this, TestTransitionSecondActivityActivity::class.java)
            intent.putExtra("transitionType", "explode")
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

        mBtnFade.setOnClickListener {
            val fade: Fade = Fade().apply {
                duration = 1000
            }

            window.enterTransition = fade
            window.exitTransition = fade

            val intent = Intent(this, TestTransitionSecondActivityActivity::class.java)
            intent.putExtra("transitionType", "fade")
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }
}