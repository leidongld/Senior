package com.openld.seniorui.testdrawable

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.openld.seniorui.R

class TestDrawableActivity : AppCompatActivity() {
    private lateinit var mLlyContainer: CardView

    private var mDensity: Float = 0F

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_drawable)

        mDensity = resources.displayMetrics.density

        initWidgets()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initWidgets() {
        mLlyContainer = findViewById(R.id.lly_container)

        val cloverDrawable = CloverDrawable.Builder(this)
            .leftTopImage(R.drawable.fruit_image10)
            .rightTopImage(R.drawable.fruit_image11)
            .leftBottomImage(R.drawable.fruit_image4)
            .rightBottomImage(R.drawable.fruit_image13)
            .margin(((mDensity * 10).toInt()))
            .stroke(R.color.yellow, (mDensity * 1).toInt())
            .radius((mDensity * 10).toInt())
            .build()
        cloverDrawable.alpha = 100
        mLlyContainer.background = cloverDrawable
    }
}