package com.openld.senior

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.openld.seniorutils.utils.PageUtils

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
            PageUtils.jumpToPage(this, "com.openld.seniorui.mainpage", null)

            // 也可以使用反射进行跳转
//            val clazz = Class.forName("com.openld.seniorui.main.UIMainActivity")
//            val intent = Intent(this, clazz)
//            startActivity(intent)
        }

        mBtnPerformance.setOnClickListener {
            PageUtils.jumpToPage(this, "com.openld.seniorperform.mainpage", null)

//            val clazz = Class.forName("com.openld.seniorperform.main.PerformanceMainActivity")
//            val intent = Intent(this, clazz)
//            startActivity(intent)
        }

        mBtnStructure.setOnClickListener {
            PageUtils.jumpToPage(this, "com.openld.seniorstructure.mainpage", null)
        }

        mBtnNDK.setOnClickListener {
            PageUtils.jumpToPage(this, "com.openld.seniorndk.mainpage", null)
        }
    }
}