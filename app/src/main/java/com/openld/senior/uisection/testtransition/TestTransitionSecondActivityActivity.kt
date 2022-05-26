package com.openld.senior.uisection.testtransition

import android.os.Bundle
import android.text.TextUtils
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import androidx.appcompat.app.AppCompatActivity
import com.openld.senior.R

class TestTransitionSecondActivityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_transition_second_activity)

        obtainIntentData()
    }

    private fun obtainIntentData() {
        val transitionType = intent.getStringExtra("transitionType")
        if (!TextUtils.isEmpty(transitionType)) {
            when {
                transitionType.equals("slide") -> {
                    val slide: Slide = Slide().apply {
                        duration = 600
                    }
                    window.enterTransition = slide
                    window.exitTransition = slide
                }
                transitionType.equals("explode") -> {
                    val explode: Explode = Explode().apply {
                        duration = 800
                    }
                    window.enterTransition = explode
                    window.exitTransition = explode
                }
                transitionType.equals("fade") -> {
                    val fade: Fade = Fade().apply {
                        duration = 1000
                    }
                    window.enterTransition = fade
                    window.exitTransition = fade
                }
                else -> {
                    val slide: Slide = Slide().apply {
                        duration = 600
                    }
                    window.enterTransition = slide
                    window.exitTransition = slide
                }
            }
        }
    }
}