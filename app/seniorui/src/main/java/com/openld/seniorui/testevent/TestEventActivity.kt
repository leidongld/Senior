package com.openld.seniorui.testevent

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.openld.seniorui.R

class TestEventActivity : AppCompatActivity() {
    private lateinit var mMclyContainer: MyConstraintLayout

    private lateinit var mBtnClickMe: MyButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_event)

        initWidgets()

        addListeners()
    }

    private fun initWidgets() {
        mMclyContainer = findViewById(R.id.mcly_contaiuner)
        mBtnClickMe = findViewById(R.id.btn_click_me)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun addListeners() {
        mMclyContainer.setOnTouchListener { v, event ->
            Toast.makeText(this, "触摸了 ViewGroup", Toast.LENGTH_SHORT).show()
            // 这里返回true则其自身的onTouchEvent无法走到，自身的onClick方法也没法走到
            return@setOnTouchListener true
        }

        mMclyContainer.setOnClickListener {
            Toast.makeText(this, "点击了 ViewGroup", Toast.LENGTH_SHORT).show()
        }

        mBtnClickMe.setOnTouchListener { v, event ->
            Toast.makeText(this, "触摸了 子View", Toast.LENGTH_SHORT).show()
            return@setOnTouchListener false
        }

        mBtnClickMe.setOnClickListener {
            Toast.makeText(this, "点击了 子View", Toast.LENGTH_SHORT).show()
        }
    }
}