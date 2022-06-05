package com.openld.seniorui.testnavigationview

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import com.openld.seniorui.R

/**
 * 测试NavigationView
 */
class TestNavigationViewActivity : AppCompatActivity() {
    private lateinit var mNavigation: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_navigation_view)

        initWidgets()

        addListeners()
    }

    /**
     * 添加监听器
     */
    private fun addListeners() {
        mNavigation.setNavigationItemSelectedListener {
            Toast.makeText(this, it.title, Toast.LENGTH_SHORT).apply {
                setGravity(Gravity.CENTER, 0, 0)
                show()
            }
            true
        }
    }

    /**
     * 初始化控件
     */
    private fun initWidgets() {
        mNavigation = findViewById<NavigationView?>(R.id.navigation).apply {
            // 将icon原本的彩色显示出来
            itemIconTintList = null
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)
    }
}