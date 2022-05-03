package com.openld.senior.uisection.testtablayout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.openld.senior.R

/**
 * TabLayout + Fragment + ViewPager
 */
class TestTabLayoutActivity : AppCompatActivity() {
    private lateinit var mTlyTabs: TabLayout

    private lateinit var mPager: ViewPager

    private lateinit var mPagerAdapter: MyPagerAdapter

    private val mTabsList =
        listOf("桃子", "柠檬", "树莓", "香蕉", "苹果", "车厘子", "西瓜", "哈密瓜", "芒果", "榴莲", "葡萄")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_tab_layout)

        // 初始化控件
        initWidgets()

        // 添加监听器
        addListeners()
    }

    /**
     * 添加监听器
     */
    private fun addListeners() {

    }

    /**
     * 初始化控件
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initWidgets() {
        mTlyTabs = findViewById(R.id.tly_tabs)
        mPager = findViewById(R.id.pager)

        mPagerAdapter = MyPagerAdapter(supportFragmentManager, mTabsList)
        mPager.adapter = mPagerAdapter
//        mPager.currentItem = 1
        mTlyTabs.setupWithViewPager(mPager)

        for (i in 0 until mTlyTabs.tabCount) {
            val tab = mTlyTabs.getTabAt(i)

            when (i) {
                0 -> {
                    tab?.setIcon(R.drawable.icon_peach)
                }
                1 -> {
                    tab?.setIcon(R.drawable.icon_lemon)
                }
                2 -> {
                    tab?.setIcon(R.drawable.icon_berry)
                }
                3 -> {
                    tab?.setIcon(R.drawable.icon_banana)
                }
                4 -> {
                    tab?.setIcon(R.drawable.icon_apple)
                }
                5 -> {
                    tab?.setIcon(R.drawable.icon_cherry)
                }
                6 -> {
                    tab?.setIcon(R.drawable.icon_watermelon)
                }
                7 -> {
                    tab?.setIcon(R.drawable.icon_hami_melon)
                }
                8 -> {
                    tab?.setIcon(R.drawable.icon_mongo)
                }
                9 -> {
                    tab?.setIcon(R.drawable.icon_durian)
                }
                10 -> {
                    tab?.setIcon(R.drawable.icon_grape)
                }
            }
        }
    }
}