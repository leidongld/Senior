package com.openld.seniorstructure.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import com.openld.seniorstructure.R
import com.openld.seniorstructure.testintentbigdata.TestIntentTransBIgDataActivity
import com.openld.seniorstructure.testobtainviewwidth.TestObtainViewWidthActivity
import com.openld.seniorutils.utils.PageUtils

class StructureMainActivity : AppCompatActivity() {
    private lateinit var mScrollContainer: NestedScrollView

    private lateinit var mToolbar: Toolbar

    private lateinit var mBtnIntentBigData: AppCompatButton

    private lateinit var mBtnObtainViewWidth: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_structure_main)

        initWidgets()
        setSupportActionBar(mToolbar)

        addListeners()
    }

    private fun initWidgets() {
        mScrollContainer = findViewById(R.id.scroll_container)
        mScrollContainer.post {
            mScrollContainer.smoothScrollTo(0, Integer.MAX_VALUE)
        }

        mToolbar = findViewById(R.id.toolbar)

        mBtnIntentBigData = findViewById(R.id.btn_intent_big_data)

        mBtnObtainViewWidth = findViewById(R.id.btn_obtain_view_width)
    }

    private fun addListeners() {
        mBtnIntentBigData.setOnClickListener {
            PageUtils.jumpToPage(this, TestIntentTransBIgDataActivity::class.java)
        }

        mBtnObtainViewWidth.setOnClickListener {
            PageUtils.jumpToPage(this, TestObtainViewWidthActivity::class.java)
        }
    }
}