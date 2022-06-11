package com.openld.seniorui.testgallery

import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.openld.seniorui.R
import java.util.*

/**
 * 测试一下画廊效果
 */
class TestGalleryActivity : AppCompatActivity() {
    private val TAG = "Gallery"

    private var mCurrentIndex = 0

    private lateinit var mGallery: ViewPager

    private val mFruitList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_gallery)

        initData()

        initWidgets()

        addListeners()
    }

    private fun initData() {
        mFruitList.add(R.drawable.fruit_image1)
        mFruitList.add(R.drawable.fruit_image2)
        mFruitList.add(R.drawable.fruit_image3)
        mFruitList.add(R.drawable.fruit_image4)
        mFruitList.add(R.drawable.fruit_image5)
        mFruitList.add(R.drawable.fruit_image6)
        mFruitList.add(R.drawable.fruit_image7)
        mFruitList.add(R.drawable.fruit_image8)
        mFruitList.add(R.drawable.fruit_image9)
        mFruitList.add(R.drawable.fruit_image10)
        mFruitList.add(R.drawable.fruit_image11)
        mFruitList.add(R.drawable.fruit_image12)
        mFruitList.add(R.drawable.fruit_image13)
        mFruitList.add(R.drawable.fruit_image14)
        mFruitList.add(R.drawable.fruit_image15)
        mFruitList.add(R.drawable.fruit_image16)
        mFruitList.add(R.drawable.fruit_image17)
    }

    private fun addListeners() {
        mGallery.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.d(TAG, ">>> $position")
                mCurrentIndex = position
            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

    private fun initWidgets() {
        Message.obtain()
        mGallery = findViewById(R.id.gallery)
        val adapter = GalleryAdapter(this, mFruitList)
        mGallery.offscreenPageLimit = mFruitList.size
        mGallery.adapter = adapter
        mGallery.currentItem = mFruitList.size - 1
        mGallery.setPageTransformer(false, GalleryTransformer())

        // 滑到第一个
        scrollToFirst()

        // 训话滑动轮播
        loopScroll()
    }

    /**
     * 循环滑动轮播
     */
    private fun loopScroll() {
        // 轮播
        mGallery.postDelayed({
            val timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    if (mCurrentIndex == mFruitList.size) {
                        mCurrentIndex = 0
                    }
                    mGallery.currentItem = mCurrentIndex++
                }
            }, 0, 2000)
        }, 2200)
    }

    /**
     * 滑动到第一个
     */
    private fun scrollToFirst() {
        // 这鬼滑动速度不知道怎么控制
        mGallery.postDelayed({
            mGallery.setCurrentItem(0, true)
        }, 200)
    }
}