package com.openld.seniorui.testcoordinatorlayout.behaviors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

/**
 * author: lllddd
 * created on: 2022/5/4 18:30
 * description:
 */
class ToolbarBehavior(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<Toolbar>(context, attrs) {

    private var mVisible = true

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: Toolbar,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
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
        child: Toolbar,
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

        // 上滑隐藏
        if (dyConsumed > 0 && mVisible) {
            child.animate().translationY((-1 * child.height).toFloat())
                .setInterpolator(AccelerateDecelerateInterpolator()).setDuration(500).start()

            mVisible = false
        }
        // 下滑展示
        else if (dyConsumed < 0 && !mVisible) {
            child.animate().translationY(0F)
                .setInterpolator(AccelerateDecelerateInterpolator()).setDuration(500).start()

            mVisible = true
        }
    }
}