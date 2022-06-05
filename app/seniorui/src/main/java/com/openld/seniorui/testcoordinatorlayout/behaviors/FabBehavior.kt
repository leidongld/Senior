package com.openld.seniorui.testcoordinatorlayout.behaviors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.marginBottom
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * author: lllddd
 * created on: 2022/5/4 12:27
 * description:悬浮按钮的自定义Behavior
 */
class FabBehavior(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<FloatingActionButton>(context, attrs) {
    private var mVisible = true

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        // 当观察的View（RecyclerView）发生滑动的开始的时候回调
        // nestedScrollAxes：滑动关联轴，我们现在只关心垂直的滑动
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(
            coordinatorLayout,
            child,
            directTargetChild,
            target,
            axes,
            type
        )
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )
        // 往上滑，隐藏
        if (dyConsumed > 0 && mVisible) {
            val marginBottom = child.marginBottom

            child.animate().translationY((child.height + marginBottom).toFloat())
                .setInterpolator(AccelerateDecelerateInterpolator())
                .setDuration(500)
                .start()
            mVisible = false
        }

        // 往下滑，显示
        else if (dyConsumed < 0 && !mVisible) {
            val marginBottom = child.marginBottom

            child.animate().translationY(0F)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .setDuration(500)
                .start()
            mVisible = true
        }
    }
}