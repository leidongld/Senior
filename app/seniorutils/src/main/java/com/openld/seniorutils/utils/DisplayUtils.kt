package com.openld.seniorutils.utils

import android.content.Context
import androidx.annotation.NonNull

/**
 * author: lllddd
 * created on: 2022/6/15 20:46
 * description:展示的工具类
 */
class DisplayUtils {
    companion object {
        /**
         * dp转为px
         *
         * @param context 上下文
         * @param dp dp
         */
        fun dp2px(@NonNull context: Context?, dp: Int): Int {
            val density = context!!.resources.displayMetrics.density
            return (density * dp).toInt()
        }

        /**
         * px转dp
         *
         * @param context 上下文
         * @param px px
         */
        fun px2dp(@NonNull context: Context?, px: Int): Int {
            val density = context!!.resources.displayMetrics.density
            return (px / density).toInt()
        }

        /**
         * sp转px
         *
         * @param context 上下文
         * @param sp sp
         */
        fun sp2px(context: Context?, sp: Int): Int {
            val scaledDensity = context!!.resources.displayMetrics.scaledDensity
            return (sp * scaledDensity).toInt()
        }

        /**
         * sp转px
         *
         * @param context 上下文
         * @param px px
         */
        fun px2sp(context: Context?, px: Int): Int {
            val scaledDensity = context!!.resources.displayMetrics.scaledDensity
            return (px / scaledDensity).toInt()
        }
    }
}