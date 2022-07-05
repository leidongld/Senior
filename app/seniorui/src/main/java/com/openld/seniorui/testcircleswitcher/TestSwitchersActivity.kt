package com.openld.seniorui.testcircleswitcher

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.openld.seniorui.R

/**
 * 测试自定义开关
 */
class TestSwitchersActivity : AppCompatActivity() {
    // 圆形开关
    private lateinit var mCircleSwitcher: CircleSwitcher

    // 椭圆形开关
    private lateinit var mOvalSwitcher: OvalSwitcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_switchers)

        initWidgets()

        addListeners()
    }

    private fun addListeners() {
    }

    private fun initWidgets() {
        mCircleSwitcher = findViewById(R.id.circle_switcher)
        mCircleSwitcher.setOnCheckChangedListener(object : CircleSwitcher.OnCheckChangedListener {
            override fun onCheckChanged(isCheck: Boolean) {
                Toast.makeText(
                    this@TestSwitchersActivity,
                    "圆形开关 isCheck = $isCheck",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        mOvalSwitcher = findViewById(R.id.oval_switcher)
        mOvalSwitcher.setOnCheckChangedListener(object : OvalSwitcher.OnCheckChangedListener {
            override fun onCheckChanged(isCheck: Boolean) {
                Toast.makeText(
                    this@TestSwitchersActivity,
                    "椭开关 isCheck = $isCheck",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}