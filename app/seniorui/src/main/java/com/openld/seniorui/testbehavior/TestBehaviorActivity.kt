package com.openld.seniorui.testbehavior

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import com.openld.seniorui.R

class TestBehaviorActivity : AppCompatActivity() {
    private lateinit var mTxtObservable: AppCompatTextView

    private lateinit var mImgObserver: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_behavior)

        initWidgets()

        addListeners()
    }

    private fun initWidgets() {
        mTxtObservable = findViewById(R.id.txt_observable)
        mImgObserver = findViewById(R.id.img_observer)
    }

    private fun addListeners() {
        mTxtObservable.setOnClickListener {
            Toast.makeText(this, "你点击了被观察者", Toast.LENGTH_SHORT).show()
            ViewCompat.offsetTopAndBottom(it, 100)
        }

        mImgObserver.setOnClickListener {
            Toast.makeText(this, "你点击了观察者", Toast.LENGTH_SHORT).show()
        }
    }
}