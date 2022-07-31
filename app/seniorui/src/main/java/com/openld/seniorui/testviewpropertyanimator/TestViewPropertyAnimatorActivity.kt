package com.openld.seniorui.testviewpropertyanimator

import android.animation.Animator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.openld.seniorui.R

class TestViewPropertyAnimatorActivity : AppCompatActivity() {
    private lateinit var mImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_view_property_animator)

        initWidgets()

        addListeners()
    }

    private fun initWidgets() {
        mImg = findViewById(R.id.img)
    }

    private fun addListeners() {
        mImg.setOnClickListener {
            val animator = mImg.animate()
            animator.duration = 5000
            animator.rotationXBy(360F)
            animator.translationYBy(200F)
            animator.interpolator = AccelerateDecelerateInterpolator()
            animator.setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                    Toast.makeText(
                        this@TestViewPropertyAnimatorActivity,
                        "动画开始",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAnimationEnd(animation: Animator?) {
                    Toast.makeText(
                        this@TestViewPropertyAnimatorActivity,
                        "动画结束",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAnimationCancel(animation: Animator?) {
                    Toast.makeText(
                        this@TestViewPropertyAnimatorActivity,
                        "动画取消",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAnimationRepeat(animation: Animator?) {
                    Toast.makeText(
                        this@TestViewPropertyAnimatorActivity,
                        "动画重复",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
            animator.start()
        }
    }
}