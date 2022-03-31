package com.openld.senior.utils

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity

/**
 * author: lllddd
 * created on: 2022/3/30 17:46
 * description:页面工具类
 */
class PageUtils {
    companion object {
        /**
         * 页面跳转
         *
         * @param context 上下文
         * @param clazz 类
         */
        fun <T : AppCompatActivity> jumpToPage(@NonNull context: Context, clazz: Class<T>) {
            val intent = Intent(context, clazz)
            context.startActivity(intent)
        }
    }
}