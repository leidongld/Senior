package com.openld.senior.uisection.testanimparallax

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * author: lllddd
 * created on: 2022/5/19 17:38
 * description:视差效果的适配器
 */
class ParallaxAdapter(fm: FragmentManager, @NonNull fragmentList: List<ParallaxFragment>) :
    FragmentPagerAdapter(fm) {
    private var mFragmentList = fragmentList

    /**
     * 获取数量
     */
    override fun getCount(): Int {
        return mFragmentList.size
    }

    /**
     * 获取条目
     */
    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }
}