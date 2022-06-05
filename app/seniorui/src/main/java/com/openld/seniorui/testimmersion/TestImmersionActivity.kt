package com.openld.seniorui.testimmersion

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.openld.seniorui.R

/**
 * 测试沉浸式效果
 */
class TestImmersionActivity : AppCompatActivity() {
    private lateinit var mClyContainer: ConstraintLayout

    private lateinit var mToolbar: Toolbar

    private lateinit var mBtnYellow: AppCompatButton

    private lateinit var mBtnGreen: AppCompatButton

    private lateinit var mBtnRed: AppCompatButton

    private lateinit var mBtnBlue: AppCompatButton

    private lateinit var mBtnPink: AppCompatButton

    private lateinit var mBtnPurple: AppCompatButton

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 放开注释则状态栏、导航栏透明，可以实现沉浸式效果
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        setContentView(R.layout.activity_test_immersion)

        configImmersion()

        initWidgets()

        addListeners()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initWidgets() {
        mClyContainer = findViewById(R.id.cly_container)

        // 避免设置上方状态栏透明的时候，状态栏遮挡主体内容，如果希望
        // 状态栏透明的时候Toolbar北京图片侵入状态栏则设置该属性为false
        mClyContainer.fitsSystemWindows = false

        mToolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(mToolbar)
//        mToolbar.setBackgroundResource(R.drawable.bg_toolbar)

        mBtnYellow = findViewById(R.id.btn_yellow)
        mBtnGreen = findViewById(R.id.btn_green)
        mBtnRed = findViewById(R.id.btn_red)
        mBtnBlue = findViewById(R.id.btn_blue)
        mBtnPink = findViewById(R.id.btn_pink)
        mBtnPurple = findViewById(R.id.btn_purple)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun addListeners() {
        mBtnYellow.setOnClickListener {
            changeImmersionColor(R.color.yellow)
            changeToolbarBackground(R.color.yellow)
        }

        mBtnGreen.setOnClickListener {
            changeImmersionColor(R.color.green)
            changeToolbarBackground(R.color.green)
        }

        mBtnRed.setOnClickListener {
            changeImmersionColor(R.color.red)
            changeToolbarBackground(R.color.red)
        }

        mBtnBlue.setOnClickListener {
            changeImmersionColor(R.color.blue)
            changeToolbarBackground(R.color.blue)
        }

        mBtnPink.setOnClickListener {
            changeImmersionColor(R.color.pink)
            changeToolbarBackground(R.color.pink)
        }

        mBtnPurple.setOnClickListener {
            changeImmersionColor(R.color.purple)
            changeToolbarBackground(R.color.purple)
        }
    }

    /**
     * 修改Toolbar的背景色
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun changeToolbarBackground(colorRes: Int) {
        mToolbar.setBackgroundColor(resources.getColor(colorRes, null))
    }

    /**
     * 改变沉浸式颜色
     *
     * @param colorRes 颜色
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun changeImmersionColor(colorRes: Int) {
        window.navigationBarColor = resources.getColor(colorRes, null)
        window.statusBarColor = resources.getColor(colorRes, null)
    }

    /**
     * 动态配置沉浸式效果
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun configImmersion() {
        changeImmersionColor(R.color.iron)
    }
}