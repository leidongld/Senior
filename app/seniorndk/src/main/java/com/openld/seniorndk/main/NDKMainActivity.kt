package com.openld.seniorndk.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import com.openld.seniorndk.R

class NDKMainActivity : AppCompatActivity() {
    private lateinit var mScrollContainer: NestedScrollView

    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ndkmain)

        initWidgets()
        setSupportActionBar(mToolbar)
    }

    private fun initWidgets() {
        mScrollContainer = findViewById(R.id.scroll_container)
        mScrollContainer.post {
            mScrollContainer.smoothScrollTo(0, Integer.MAX_VALUE)
        }

        mToolbar = findViewById(R.id.toolbar)
    }
}