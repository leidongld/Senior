package com.openld.seniorui.testviewexposure

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.openld.seniorui.R

class TestViewExposureActivity : AppCompatActivity() {
    private lateinit var mScrollView: NestedScrollView

    private lateinit var mImg: ImageView;

    private var mImgWidth = 0
    private var mImgHeight = 0
    private var mImgArea = 0

    private var mRect = Rect()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_view_visible)

        initWidgets()

        addListeners()

        mImg.post {
            mImgWidth = mImg.width
            mImgHeight = mImg.height
            mImgArea = mImgHeight * mImgWidth
            Log.d(
                "Exposure >>>>>>",
                "图片宽度为 ${mImgWidth} 高度为${mImgHeight} 面积为${mImgArea}"
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun addListeners() {
        mScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val isGlobalVisible = mImg.getGlobalVisibleRect(mRect)
            if (isGlobalVisible) {
                // 有些场景进来是要可见面积大于自身面积的五分之一
                // 出去是要可见面积小于自己面积的五分之一
                // 这个时候可使用mRect和本身组件的原始面积进行一些比较，这里略
                Log.d(
                    "Exposure >>>>>>",
                    "全局可见 可见宽度为 ${mRect.width()} 可见高度为${mRect.height()} 可见面积为${mRect.width() * mRect.height()}"
                )
            } else {
                Log.d(
                    "Exposure >>>>>>",
                    "全局不可见}"
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initWidgets() {
        mScrollView = findViewById(R.id.scroll_view)
        mImg = findViewById(R.id.img)
    }
}