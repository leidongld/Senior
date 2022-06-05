package com.openld.seniorui.testtablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.openld.seniorui.R

/**
 * author: lllddd
 * created on: 2022/4/29 15:22
 * description:我的Pager的适配器
 */
class MyPagerAdapter(fragmentManager: FragmentManager, private val list: List<String>) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return TabFragment(R.layout.fragment_tab).apply {
            val bundle = Bundle().apply {
                putString("title", list[position])
            }
            arguments = bundle
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position]
    }
}