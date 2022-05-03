package com.openld.senior

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.openld.senior.ndksection.main.NDKMainActivity
import com.openld.senior.performancesection.main.PerformanceMainActivity
import com.openld.senior.structuresection.main.StructureMainActivity
import com.openld.senior.uisection.main.UIMainActivity
import com.openld.senior.utils.PageUtils

/**
 * 主页面
 */
class MainActivity : AppCompatActivity() {
    private lateinit var mLySwipeRefresh: SwipeRefreshLayout

    private lateinit var mBtnUI: AppCompatButton

    private lateinit var mBtnPerformance: AppCompatButton

    private lateinit var mBtnStructure: AppCompatButton

    private lateinit var mBtnNDK: AppCompatButton

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

        mBtnPerformance = findViewById(R.id.btn_performance)

        mBtnStructure = findViewById(R.id.btn_structure)

        mBtnNDK = findViewById(R.id.btn_ndk)
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

        mBtnPerformance.setOnClickListener {
            PageUtils.jumpToPage(this, PerformanceMainActivity::class.java)
        }

        mBtnStructure.setOnClickListener {
            PageUtils.jumpToPage(this, StructureMainActivity::class.java)
        }

        mBtnNDK.setOnClickListener {
            PageUtils.jumpToPage(this, NDKMainActivity::class.java)
        }
    }
}