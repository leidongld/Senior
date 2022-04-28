package com.openld.senior.uisection.testcheckbox

import android.os.Build
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import com.openld.senior.R

class TestCheckBoxActivity : AppCompatActivity(), Animation.AnimationListener {
    private lateinit var mCbx: AppCompatCheckBox

    private lateinit var mBtnSubmit: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_check_box)

        initWidgets()

        addListeners()
    }

    private fun addListeners() {
        mBtnSubmit.setOnClickListener {
            if (!mCbx.isChecked) {
                Toast.makeText(this, "请勾选，提交失败！", Toast.LENGTH_SHORT).show()
                val animation = AnimationUtils.loadAnimation(this, R.anim.shake)
                animation.setAnimationListener(this)
                mCbx.startAnimation(animation)
            } else {
                Toast.makeText(this, "提交成功！", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initWidgets() {
        mCbx = findViewById(R.id.cbx)
        mBtnSubmit = findViewById(R.id.btn_submit)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onAnimationStart(animation: Animation?) {
        mCbx.setTextColor(resources.getColor(R.color.red, null))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onAnimationEnd(animation: Animation?) {
        mCbx.setTextColor(resources.getColor(R.color.black, null))
    }

    override fun onAnimationRepeat(animation: Animation?) {
    }
}