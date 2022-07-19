package com.openld.seniorui.testpaint

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.widget.NestedScrollView
import com.openld.seniorui.R

class TestPaintActivity : AppCompatActivity() {
    private lateinit var mContainer: NestedScrollView
    private lateinit var mClyContainer: ConstraintLayout

    private lateinit var mImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_paint)

        mContainer = findViewById(R.id.container)
        mClyContainer = findViewById(R.id.cly_container)
        mImg = findViewById(R.id.img)


        // 把图片变成黑白
        val matrix = ColorMatrix()
        matrix.setSaturation(0F)
        mImg.colorFilter = ColorMatrixColorFilter(matrix)

//        for (ele in mClyContainer.children) {
//            if (ele is ImageView) {
//                ele.colorFilter = ColorMatrixColorFilter(matrix)
//            }
//        }

        // 即使新设置图片，黑白效果也生效
//        mImg.setImageResource(R.drawable.ic_cow)
    }
}