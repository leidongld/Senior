package com.openld.seniorui.testanimparallax

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.openld.seniorui.R

/**
 * 视差动画第一页
 */
class TestAnimParallaxActivity : AppCompatActivity() {
    private lateinit var mImgCow: AppCompatImageView

    private lateinit var mParallaxContainer: ParallaxContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_anim_parallax)

        initWidgets()
    }

    private fun initWidgets() {
        mImgCow = findViewById(R.id.img_cow)
        mParallaxContainer = findViewById(R.id.parallax_container)


        val resArray: Array<Int> = arrayOf(
            R.layout.parallax_frag1,
            R.layout.parallax_frag2,
            R.layout.parallax_frag3,
            R.layout.parallax_frag4,
            R.layout.parallax_frag_login
        )
        mParallaxContainer.setUp(resArray)
    }
}