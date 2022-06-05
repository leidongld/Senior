package com.openld.seniorui.testanimparallax

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.NonNull
import com.openld.seniorui.R

/**
 * author: lllddd
 * created on: 2022/5/20 15:09
 * description:自定义视差动画的布局膨胀器
 */
class ParallaxLayoutInflater(
    original: LayoutInflater?,
    newContext: Context?,
    fragment: ParallaxFragment
) :
    LayoutInflater(original, newContext) {
//    constructor(context: Context) : this(null, context, fragment)

    private lateinit var mContext: Context

    init {
        factory = ParallaxFactory(this)
        mFragment = fragment
        mContext = context
    }

    override fun cloneInContext(newContext: Context?): LayoutInflater {
        return ParallaxLayoutInflater(this, mContext, mFragment)
    }

    override fun onCreateView(name: String?, attrs: AttributeSet?): View {
        return super.onCreateView(name, attrs)
    }

    companion object {
        private lateinit var mFragment: ParallaxFragment

        /**
         * 自定义的工厂类，视图创建的工厂类
         */
        private class ParallaxFactory(inflater: LayoutInflater) : LayoutInflater.Factory {
            private val layoutInflater: LayoutInflater = inflater

            override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
                // 1.实例化里面的View
                val view = createView(name, context, attrs)
                if (view != null) {
                    // 获取自定义属性
                    getCustomAttrs(context, attrs, view)
                    mFragment.getViews().add(view)
                }

                return view
            }

            /**
             * 自定义动画属性
             */
            private val customAttrsDef = intArrayOf(
                R.attr.a_in,
                R.attr.a_out,
                R.attr.x_in,
                R.attr.x_out,
                R.attr.y_in,
                R.attr.y_out,
            )

            @SuppressLint("ResourceType")
            private fun getCustomAttrs(context: Context, attrs: AttributeSet, @NonNull view: View) {
                val a: TypedArray = context.obtainStyledAttributes(attrs, customAttrsDef)

                if (a.length() > 0) {
                    // alpha in
                    val alphaIn = a.getFloat(0, 1.0F)
                    // alpha out
                    val alphaOut = a.getFloat(1, 1.0F)
                    // scaleX in
                    val xIn = a.getFloat(2, 1.0F)
                    // scaleX out
                    val xOut = a.getFloat(3, 1.0F)
                    // scaleY in
                    val yIn = a.getFloat(4, 1.0F)
                    // scaleY out
                    val yOut = a.getFloat(5, 1.0F)

                    val parallaxViewTag = ParallaxViewTag().apply {
                        this.alphaIn = alphaIn
                        this.alphaOut = alphaOut
                        this.xIn = xIn
                        this.xOut = xOut
                        this.yIn = yIn
                        this.yOut = yOut
                    }

                    view.setTag(R.id.parallax_view_tag, parallaxViewTag)
                }

                a.recycle()
            }

            private val PREFIXS = mutableListOf<String>(
                "android.widget.",
                "android.view.",
                "<androidx.constraintlayout.widget.",
                "androidx.appcompat.widget."
            )

            /**
             * 创建视图
             */
            private fun createView(name: String, context: Context, attrs: AttributeSet): View? {
                if (name.contains(".")) {// 自定义控件已经是全类名了
                    return createView(name, "  ", context, attrs)
                } else {
                    for (prefix in PREFIXS) {
                        val view = createView(name, prefix, context, attrs)
                        if (view != null) {
                            return view;
                        }
                    }
                }
                return null
            }

            private fun createView(
                name: String,
                prefix: String,
                context: Context,
                attrs: AttributeSet
            ): View? {
                return try {
                    layoutInflater.createView(name, prefix, attrs)
                } catch (e: Exception) {
                    null
                }
            }
        }
    }
}