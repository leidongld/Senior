package com.openld.senior.uisection.testdrawerlayout

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import com.openld.senior.R

class TestDrawerLayoutActivity : AppCompatActivity(), DrawerListener {
    private lateinit var mToolBar: Toolbar

    private lateinit var mDrawerLayout: DrawerLayout

    private lateinit var mBtnShowDrawer: AppCompatButton

    private lateinit var mBtnHideDrawer: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_drawer_layout)

        initWidgets()

        addListeners()
    }

    private fun addListeners() {
        mBtnShowDrawer.setOnClickListener {
            mDrawerLayout.openDrawer(Gravity.LEFT, true)
        }

        mBtnHideDrawer.setOnClickListener {
            mDrawerLayout.closeDrawer(Gravity.LEFT, true)
        }

        mDrawerLayout.addDrawerListener(this)
    }

    private fun initWidgets() {
        mDrawerLayout = findViewById(R.id.dly)
        mBtnShowDrawer = findViewById(R.id.btn_show_drawer)
        mBtnHideDrawer = findViewById(R.id.btn_hide_drawer)
        mToolBar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolBar)

        val toggle = ActionBarDrawerToggle(
            this,
            mDrawerLayout,
            mToolBar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        toggle.syncState()
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        val content = mDrawerLayout.getChildAt(0)

        var scale = 1 - slideOffset
        var leftScale = 1 - scale * 0.3
        var rightScale = 0.7 + scale * 0.3

        drawerView.scaleX = leftScale.toFloat()
        drawerView.scaleY = leftScale.toFloat()

        content.scaleX = rightScale.toFloat()
        content.scaleY = rightScale.toFloat()
    }

    override fun onDrawerOpened(drawerView: View) {
        Toast.makeText(this, "菜单已打开", Toast.LENGTH_SHORT).show()
    }

    override fun onDrawerClosed(drawerView: View) {
        Toast.makeText(this, "菜单已关闭", Toast.LENGTH_SHORT).show()
    }

    override fun onDrawerStateChanged(newState: Int) {

    }
}