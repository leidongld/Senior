package com.openld.seniorui.testanimparallax

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * author: lllddd
 * created on: 2022/5/19 17:35
 * description:视差动画的Fragment
 */
class ParallaxFragment : Fragment() {
    private val mViewList = mutableListOf<View>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val arguments = arguments as Bundle
        val layoutId = arguments.getInt("layoutId")
        // 使用自定义的布局膨胀器来做逻辑
        val parallaxInflater: ParallaxLayoutInflater =
            ParallaxLayoutInflater(inflater, activity, this)
        return parallaxInflater.inflate(resources.getLayout(layoutId), container, false)
    }

    fun getViews(): MutableList<View> {
        return mViewList
    }
}