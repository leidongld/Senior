package com.openld.seniorui.testanimparallax

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.openld.seniorui.R

/**
 * author: lllddd
 * created on: 2022/5/19 16:53
 * description:视差动画容器
 */
class ParallaxContainer(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val mContext: Context = context

    private var mFragmentList = mutableListOf<ParallaxFragment>()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : this(context, attrs, defStyleAttr, 0)

    /**
     * 设置视图
     */
    fun setUp(viewArray: Array<Int>) {
        for (layoutRes in viewArray) {
            val fragment = ParallaxFragment()
            fragment.arguments = Bundle().apply {
                putInt("layoutId", layoutRes)
            }
            mFragmentList.add(fragment)
        }

        val viewPager = ViewPager(context)
        viewPager.layoutParams =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        viewPager.adapter =
            ParallaxAdapter(
                (mContext as FragmentActivity).supportFragmentManager, mFragmentList
            )

        // 这里不设置会报错
        viewPager.id = R.id.parallax_pager
        addView(viewPager, 0)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                var outFragment: ParallaxFragment? = null
                var inFragment: ParallaxFragment? = null


                try {
                    // 进入的页面
                    inFragment = mFragmentList[position - 1]
                    // 退出的页面
                    outFragment = mFragmentList[position]
                } catch (e: Exception) {
                    // 触发了数组越界的保护
                }


                if (inFragment != null) {
                    val viewList: List<View> = inFragment.getViews()
                    for (v in viewList) {
                        val parallaxViewTag: ParallaxViewTag =
                            v.getTag(R.id.parallax_view_tag) as ParallaxViewTag

                        // 进来动画
                        v.translationX = (width - positionOffset) * parallaxViewTag.xIn
                        v.translationY = (width - positionOffset) * parallaxViewTag.yIn


                    }
                }

                if (outFragment != null) {
                    val viewList: List<View> = outFragment.getViews()
                    for (v in viewList) {
                        val parallaxViewTag: ParallaxViewTag =
                            v.getTag(R.id.parallax_view_tag) as ParallaxViewTag

                        // 出去动画
                        v.translationX = -(width - positionOffset) * parallaxViewTag.xOut
                        v.translationY = -(width - positionOffset) * parallaxViewTag.yOut


                    }
                }
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }
}