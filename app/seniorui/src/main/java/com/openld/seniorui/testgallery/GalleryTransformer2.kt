package com.openld.seniorui.testgallery

import android.view.View
import androidx.viewpager.widget.ViewPager

/**
 * author: lllddd
 * created on: 2022/6/10 11:14
 * description:
 */
class GalleryTransformer2 : ViewPager.PageTransformer {
    private val MIN_SCALE = 0.5F

    override fun transformPage(page: View, position: Float) {
        if (position < 0F) {// 左
            page.scaleX = MIN_SCALE * position + 1.5F
            page.scaleY = MIN_SCALE * position + 1.5F
            page.alpha = MIN_SCALE * position + 1
            page.translationX = page.width * position * MIN_SCALE
        } else {// 右
            page.scaleX = -MIN_SCALE * position + 1.5F
            page.scaleY = -MIN_SCALE * position + 1.5F
            page.alpha = -MIN_SCALE * position + 1
            page.translationX = page.width * position * MIN_SCALE
        }

        page.elevation = page.scaleX
    }
}