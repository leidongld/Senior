package com.openld.senior

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.openld.senior.uisection.main.UIMainActivity
import com.openld.senior.utils.PageUtils

/**
 * 主页面
 */
class MainActivity : AppCompatActivity() {
    private lateinit var mLySwipeRefresh: SwipeRefreshLayout

    private lateinit var mBtnUI: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initWidgets()

        addListeners()
    }

    private fun initWidgets() {
        mLySwipeRefresh = findViewById<SwipeRefreshLayout?>(R.id.ly_swipe_refresh).apply {
            setSize(SwipeRefreshLayout.LARGE)
        }

        mBtnUI = findViewById(R.id.btn_ui)
    }

    private fun addListeners() {
        mLySwipeRefresh.setOnRefreshListener {
            Toast.makeText(this, "下拉刷新了啊！！！", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed(
                Runnable {
                    mLySwipeRefresh.isRefreshing = false
                }, 2000
            )
        }


        mBtnUI.setOnClickListener {
            PageUtils.jumpToPage(this, UIMainActivity::class.java)
        }
    }
}