package com.openld.seniorui.testpalette

import androidx.annotation.FloatRange

/**
 * author: lllddd
 * created on: 2022/4/27 22:26
 * description:透明度变化监听器
 */
interface TranslucentListener {
    /**
     * 透明度变化回调
     *
     * @param alpha 透明度
     */
    fun onTranslucent(@FloatRange(from = 0.0, to = 1.0) alpha: Float)
}