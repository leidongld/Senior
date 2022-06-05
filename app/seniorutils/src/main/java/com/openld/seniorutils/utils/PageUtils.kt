package com.openld.seniorutils.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity

/**
 * author: lllddd
 * created on: 2022/3/30 17:46
 * description:页面工具类
 */
class PageUtils {
    companion object {
        private val TAG = "PageUtils"

        /**
         * 页面显式跳转
         *
         * @param context 上下文
         * @param clazz 类
         */
        fun <T : AppCompatActivity> jumpToPage(@NonNull context: Context, clazz: Class<T>) {
            val intent = Intent(context, clazz)
            context.startActivity(intent)
        }

        /**
         * 页面隐式跳转
         *
         * @param context 上下文
         * @param action 跳转action
         * @param uri 跳转uri
         *
         */
        @SuppressLint("QueryPermissionsNeeded")
        fun jumpToPage(@NonNull context: Context, @NonNull action: String, uri: Uri?) {
            val intent = Intent(action, uri)
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else {
                Log.e(TAG, ">>> 找不到对应的页面！！！")
            }
        }
    }
}