package com.openld.seniorui.testbehavior

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

/**
 * author: lllddd
 * created on: 2022/5/6 16:06
 * description:这是测试用的自定义Behavior
 */
class TestBehavior(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<AppCompatImageView>(context, attrs) {
    /**
     * 用来决定需要监听哪些控件或者容器的状态
     * 1.知道监听谁
     * 2.什么状态改变
     * 此处可以根据id或者tag实现View的交叉联动
     *
     * @param parent 父容器CoL
     * @param child 观察者View
     * @param dependency 被观察者View
     */
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: AppCompatImageView,
        dependency: View
    ): Boolean {
        return dependency is AppCompatTextView
    }

    /**
     * 被观察者发生变化时的响应
     *
     * @param parent 父容器CoL
     * @param child 观察者View
     * @param dependency 被观察者View
     */
    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: AppCompatImageView,
        dependency: View
    ): Boolean {
        // 获取被监听View的状态 -- 位置
        val verticalOffset = dependency.top - child.top
        ViewCompat.offsetTopAndBottom(child, verticalOffset)

        val animatorX = ObjectAnimator.ofFloat(child, "scaleX", 1.0F, 1.5F, 1.0F).apply {
            repeatCount = 2
        }
        val animatorY = ObjectAnimator.ofFloat(child, "scaleY", 1.0F, 1.5F, 1.0F).apply {
            repeatCount = 2
        }
        val animatorSet = AnimatorSet().apply {
            duration = 500
            interpolator = AccelerateDecelerateInterpolator()
            playTogether(animatorX, animatorY)
        }.start()

        return true
    }

}