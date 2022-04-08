package com.openld.senior.uisection.testdrawerlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.openld.senior.R

class TestDrawerLayoutActivity : AppCompatActivity() {
    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_drawer_layout)

        initWidgets()
    }

    private fun initWidgets() {
        TODO("Not yet implemented")
    }
}