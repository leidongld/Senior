package com.openld.senior.uisection.testcardview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.openld.senior.R

class TestCardViewActivity : AppCompatActivity() {
    private lateinit var mBtnFloating: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_card_view)

        initWidgets()

        addListeners()
    }

    private fun initWidgets() {
        mBtnFloating = findViewById(R.id.btn_floating)
    }

    private fun addListeners() {
        mBtnFloating.setOnClickListener{
            Toast.makeText(this, "哈哈，你点击了按钮", Toast.LENGTH_SHORT).show()
        }
    }
}