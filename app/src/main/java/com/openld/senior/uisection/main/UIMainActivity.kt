package com.openld.senior.uisection.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.openld.senior.R
import com.openld.senior.uisection.testrecyclerview.TestRecyclerViewActivity
import com.openld.senior.utils.PageUtils

class UIMainActivity : AppCompatActivity() {
    private lateinit var mBtnRecyclerView: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uimain)

        initWidgets()

        addListeners()
    }

    private fun addListeners() {
        mBtnRecyclerView.setOnClickListener {
            PageUtils.jumpToPage(this, TestRecyclerViewActivity::class.java)
        }
    }

    private fun initWidgets() {
        mBtnRecyclerView = findViewById(R.id.btn_recycler_view)
    }
}