package com.openld.seniorui.testgallery

import android.view.View
import androidx.viewpager.widget.ViewPager

/**
 * author: lllddd
 * created on: 2022/6/10 11:14
 * description:
 */
class GalleryTransformer : ViewPager.PageTransformer {
    private val MIN_SCALE = 0.5F

    override fun transformPage(page: View, position: Float) {
        if (position < 0F) {
            page.scaleX = MIN_SCALE * position + 1.5F
            page.scaleY = MIN_SCALE * position + 1.5F
            page.alpha = MIN_SCALE * position + 1
//            page.rotationY = 30F * position
            page.rotation = 30 * position
        } else {
            page.scaleX = -MIN_SCALE * position + 1.5F
            page.scaleY = -MIN_SCALE * position + 1.5F
            page.alpha = -MIN_SCALE * position + 1
//            page.rotationY = 30F * position
            page.rotation = 30 * position
        }

        page.elevation = page.scaleX
    }
}