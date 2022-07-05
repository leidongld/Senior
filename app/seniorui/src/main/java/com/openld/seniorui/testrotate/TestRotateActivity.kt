package com.openld.seniorui.testrotate

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.openld.seniorui.R
import kotlin.math.cos
import kotlin.math.sin

class TestRotateActivity : AppCompatActivity() {
    companion object {
        private val RADIUS = 300F
        private val DURATION = 1800
        private val INTERPOLATOR = AccelerateDecelerateInterpolator()
    }


    private lateinit var mImage1: ImageView;
    private lateinit var mImage2: ImageView;
    private lateinit var mImage3: ImageView;
    private lateinit var mImage4: ImageView;
    private lateinit var mImage5: ImageView;
    private lateinit var mImage6: ImageView;
    private lateinit var mImage7: ImageView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_rotate)

        initWidgets()

        startRotate()
    }

    private fun startRotate() {
        val anim1 = ValueAnimator.ofFloat(0F, 360F).apply {
            repeatCount = ValueAnimator.INFINITE
            startDelay = 0
            addUpdateListener {
                val value: Double = (it.animatedValue as Float) * 1.0
                val radians = Math.toRadians(value)
                val cos = cos(radians)
                val sin = sin(radians)

                mImage1.translationX = (RADIUS * cos).toFloat()
                mImage1.translationY = (RADIUS * sin).toFloat()
            }
        }


        val anim2 = ValueAnimator.ofFloat(0F, 360F).apply {
            repeatCount = ValueAnimator.INFINITE
            startDelay = 100
            addUpdateListener {
                val value: Double = (it.animatedValue as Float) * 1.0
                val radians = Math.toRadians(value)
                val cos = cos(radians)
                val sin = sin(radians)

                mImage2.translationX = (RADIUS * cos).toFloat()
                mImage2.translationY = (RADIUS * sin).toFloat()
            }
        }

        val anim3 = ValueAnimator.ofFloat(0F, 360F).apply {
            repeatCount = ValueAnimator.INFINITE
            startDelay = 200
            addUpdateListener {
                val value: Double = (it.animatedValue as Float) * 1.0
                val radians = Math.toRadians(value)
                val cos = cos(radians)
                val sin = sin(radians)

                mImage3.translationX = (RADIUS * cos).toFloat()
                mImage3.translationY = (RADIUS * sin).toFloat()
            }
        }

        val anim4 = ValueAnimator.ofFloat(0F, 360F).apply {
            repeatCount = ValueAnimator.INFINITE
            startDelay = 300
            addUpdateListener {
                val value: Double = (it.animatedValue as Float) * 1.0
                val radians = Math.toRadians(value)
                val cos = cos(radians)
                val sin = sin(radians)

                mImage4.translationX = (RADIUS * cos).toFloat()
                mImage4.translationY = (RADIUS * sin).toFloat()
            }
        }

        val anim5 = ValueAnimator.ofFloat(0F, 360F).apply {
            repeatCount = ValueAnimator.INFINITE
            startDelay = 400
            addUpdateListener {
                val value: Double = (it.animatedValue as Float) * 1.0
                val radians = Math.toRadians(value)
                val cos = cos(radians)
                val sin = sin(radians)

                mImage5.translationX = (RADIUS * cos).toFloat()
                mImage5.translationY = (RADIUS * sin).toFloat()
            }
        }


        val anim6 = ValueAnimator.ofFloat(0F, 360F).apply {
            repeatCount = ValueAnimator.INFINITE
            startDelay = 500
            addUpdateListener {
                val value: Double = (it.animatedValue as Float) * 1.0
                val radians = Math.toRadians(value)
                val cos = cos(radians)
                val sin = sin(radians)

                mImage6.translationX = (RADIUS * cos).toFloat()
                mImage6.translationY = (RADIUS * sin).toFloat()
            }
        }

        val anim7 = ValueAnimator.ofFloat(0F, 360F).apply {
            repeatCount = ValueAnimator.INFINITE
            startDelay = 600
            addUpdateListener {
                val value: Double = (it.animatedValue as Float) * 1.0
                val radians = Math.toRadians(value)
                val cos = cos(radians)
                val sin = sin(radians)

                mImage7.translationX = (RADIUS * cos).toFloat()
                mImage7.translationY = (RADIUS * sin).toFloat()
            }
        }

        val animSet = AnimatorSet().apply {
            duration = DURATION.toLong()
            interpolator = INTERPOLATOR
            playTogether(anim1, anim2, anim3, anim4, anim5, anim6, anim7)
            start()
        }
    }

    private fun initWidgets() {
        mImage1 = findViewById(R.id.img1);
        mImage2 = findViewById(R.id.img2);
        mImage3 = findViewById(R.id.img3);
        mImage4 = findViewById(R.id.img4);
        mImage5 = findViewById(R.id.img5);
        mImage6 = findViewById(R.id.img6);
        mImage7 = findViewById(R.id.img7);

        mImage1.translationX = RADIUS
        mImage2.translationX = RADIUS
        mImage3.translationX = RADIUS
        mImage4.translationX = RADIUS
        mImage5.translationX = RADIUS
        mImage6.translationX = RADIUS
        mImage7.translationX = RADIUS
    }
}