package com.openld.senior.uisection.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.openld.senior.R
import com.openld.senior.uisection.testdrawerlayout.TestDrawerLayoutActivity
import com.openld.senior.uisection.testnavigationview.TestNavigationViewActivity
import com.openld.senior.uisection.testrecyclerview.TestRecyclerViewActivity
import com.openld.senior.uisection.testsnackbar.TestSnackBarActivity
import com.openld.senior.utils.PageUtils

class UIMainActivity : AppCompatActivity() {
    private lateinit var mBtnRecyclerView: AppCompatButton

    private lateinit var mBtnDrawerLayout: AppCompatButton

    private lateinit var mBtnNavigationView: AppCompatButton

    private lateinit var mBtnSnackBar: AppCompatButton

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

        mBtnDrawerLayout.setOnClickListener {
            PageUtils.jumpToPage(this, TestDrawerLayoutActivity::class.java)
        }

        mBtnNavigationView.setOnClickListener {
            PageUtils.jumpToPage(this, TestNavigationViewActivity::class.java)
        }

        mBtnSnackBar.setOnClickListener {
            PageUtils.jumpToPage(this, TestSnackBarActivity::class.java)
        }
    }

    private fun initWidgets() {
        mBtnRecyclerView = findViewById(R.id.btn_recycler_view)

        mBtnDrawerLayout = findViewById(R.id.btn_drawer_layout)

        mBtnNavigationView = findViewById(R.id.btn_navigation_view)

        mBtnSnackBar = findViewById(R.id.btn_snack_bar)
    }
}