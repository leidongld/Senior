package com.openld.seniorui.testpalette

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.NonNull
import androidx.core.widget.NestedScrollView

/**
 * author: lllddd
 * created on: 2022/4/27 22:24
 * description:自定义的ScrollView，用于在滑动的时候改变透明度
 */
class PaletteScrollView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    NestedScrollView(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        // 该控件的绘制范围是否不在padding里面。false：绘制的时候范围会考虑padding，即会往里面绘制
        clipToPadding = false
        // 子控件是否能够不超出padding的区域。false：比如ScrollView上滑的时候，child可以滑出该padding范围
        clipChildren = false
    }

    private lateinit var mTranslucentListener: TranslucentListener

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)

        val scrollY = scrollY
        val screenHeight = context.resources.displayMetrics.heightPixels
        val guideOffset = screenHeight / 3
        if (scrollY <= guideOffset) {
            mTranslucentListener.onTranslucent(1.0F - 1.0F * scrollY / guideOffset)
        } else {
            mTranslucentListener.onTranslucent(0.0F)
        }
    }

    fun setTranslucentListener(@NonNull listener: TranslucentListener) {
        this.mTranslucentListener = listener
    }

}