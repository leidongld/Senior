package com.openld.seniorui.testpaint.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.openld.seniorui.R

/**
 * author: lllddd
 * created on: 2022/7/17 10:34
 * description:
 */
class MaskFilterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mPaint1: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mPaint2: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mPaint3: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mPaint4: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mScreenWidth = 0

    private var mBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.fire)

    init {
        mScreenWidth = context.resources.displayMetrics.widthPixels
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        canvas!!.drawBitmap(mBitmap, null, Rect().apply {
            left = 0
            top = 0
            right = mScreenWidth
            bottom = mScreenWidth
        }, mPaint1)

        // 边缘模糊
        mPaint2.maskFilter = BlurMaskFilter(9000F, BlurMaskFilter.Blur.SOLID)
        canvas.drawBitmap(mBitmap, null, Rect().apply {
            left = 200
            top = mScreenWidth + 200
            right = (mScreenWidth * 0.7F + 200).toInt()
            bottom = (mScreenWidth * 1.7F + 200).toInt()
        }, mPaint2)

        // 浮雕遮罩
        mPaint3.maskFilter =
            EmbossMaskFilter(floatArrayOf(100F, 30F, 90F), 0.2F, 50F, 100F)
        canvas.drawBitmap(mBitmap, null, Rect().apply {
            left = 200
            top = (mScreenWidth * 1.7F + 400).toInt()
            right = (mScreenWidth * 0.7F + 200).toInt()
            bottom = (mScreenWidth * 2.4F + 400).toInt()
        }, mPaint3)

        // 颜色过滤器，各种滤镜就是从这里调配出来的
        // 绿色通道
//        val colorMatrix = ColorMatrix(
//            floatArrayOf(
//                0F, 0F, 0F, 0F, 0F,
//                0F, 1F, 0F, 0F, 0F,
//                0F, 0F, 0F, 0F, 0F,
//                0F, 0F, 0F, 1F, 0F
//            )
//        )
        // 增加亮度
//        val colorMatrix = ColorMatrix(
//            floatArrayOf(
//                1.1F, 0F, 0F, 0F, 0F,
//                0F, 1.1F, 0F, 0F, 0F,
//                0F, 0F, 1.1F, 0F, 0F,
//                0F, 0F, 0F, 1F, 0F
//            )
//        )
//        // 黑白图片
//        val colorMatrix = ColorMatrix(
//            floatArrayOf(
//                0.213F, 0.715F, 0.072F, 0F, 0F,
//                0.213F, 0.715F, 0.072F, 0F, 0F,
//                0.213F, 0.715F, 0.072F, 0F, 0F,
//                0F, 0F, 0F, 1F, 0F
//            )
//        )
        // 反相
        val colorMatrix = ColorMatrix(
//            floatArrayOf(
//                -1F, 0F, 0F, 0F, 255F,
//                0F, -1F, 0F, 0F, 255F,
//                0F, 0F, -1F, 0F, 255F,
//                0F, 0F, 0F, 1F, 0F
//            )
        )
//        colorMatrix.setSaturation(0)
        colorMatrix.setScale(1.4F, 1F, 1F, 1F)
        mPaint4.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(mBitmap, null, Rect().apply {
            left = 200
            top = (mScreenWidth * 2.4F + 600).toInt()
            right = (mScreenWidth * 0.7F + 200).toInt()
            bottom = (mScreenWidth * 3.1F + 600).toInt()
        }, mPaint4)
    }

}