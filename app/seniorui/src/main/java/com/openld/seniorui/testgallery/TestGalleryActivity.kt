package com.openld.seniorui.testgallery

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.openld.seniorui.R
import java.util.*

/**
 * 测试一下画廊效果
 */
class TestGalleryActivity : AppCompatActivity() {
    private val TAG = "Gallery"

    private var mCurrentIndex = 0

    private lateinit var mContainer: ConstraintLayout

    private lateinit var mGallery: ViewPager

    private lateinit var mBtnJumpFirst: Button

    private lateinit var mBtnLoop: Button

    private val mFruitList = mutableListOf<Int>()

    private var mIsFold = false

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
//        mFruitList.add(R.drawable.fruit_image6)
//        mFruitList.add(R.drawable.fruit_image7)
//        mFruitList.add(R.drawable.fruit_image8)
//        mFruitList.add(R.drawable.fruit_image9)
//        mFruitList.add(R.drawable.fruit_image10)
//        mFruitList.add(R.drawable.fruit_image11)
//        mFruitList.add(R.drawable.fruit_image12)
//        mFruitList.add(R.drawable.fruit_image13)
//        mFruitList.add(R.drawable.fruit_image14)
//        mFruitList.add(R.drawable.fruit_image15)
//        mFruitList.add(R.drawable.fruit_image16)
//        mFruitList.add(R.drawable.fruit_image17)
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
                if (mCurrentIndex == mFruitList.size - 1) {
                    Toast.makeText(this@TestGalleryActivity, "到头了别滑了", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onPageSelected(position: Int) {
                // TODO 这里可以加一个高斯模糊效果
//                mContainer.setBackgroundResource(mFruitList[position])
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

        mBtnJumpFirst.setOnClickListener {
            scrollToFirst()
        }

        mBtnLoop.setOnClickListener {
            loopScroll()
        }
    }

    private fun initWidgets() {
        mContainer = findViewById(R.id.container)

        mGallery = findViewById(R.id.gallery)
        val adapter = GalleryAdapter(this, mFruitList)
        adapter.mOnGalleryItemClickListener = object : OnGalleyItemClickListener {
            override fun onGalleryItemClicked(position: Int) {
                val count = adapter.count
                val left = 0
                val right = count - 1

                val child = mGallery.getChildAt(0)
                val width = child.width
                val transformer = GalleryTransformer()

                if (mIsFold) {// 当前折叠
                    mGallery.setPageTransformer(false, transformer)
                    transformer.mFlag = false
                    mIsFold = !mIsFold
                } else {// 当前展开
                    mGallery.setPageTransformer(false, transformer)
                    transformer.mFlag = true
                    mIsFold = !mIsFold
                }

                mGallery.setCurrentItem(0, true)

                mGallery.postOnAnimationDelayed({
                    mGallery.setCurrentItem(position, true)
                }, 200)
            }
        }
//        mGallery.offscreenPageLimit = mFruitList.size
        mGallery.offscreenPageLimit = mFruitList.size - 1
        mGallery.adapter = adapter
        mGallery.currentItem = mFruitList.size - 1
        mGallery.setPageTransformer(false, GalleryTransformer())
        // 一些重叠效果可以通过设置该属性为负值达到
//        mGallery.pageMargin = -100
        mGallery.pageMargin = 100

        // 该属性设置两个Page之间的magin部分的图片
//        mGallery.setPageMarginDrawable(R.drawable.fruit_image1)

//        mGallery.drawa

        // 这个属性暂时划不动
//        mGallery.arrowScroll(View.FOCUS_RIGHT)
//        mGallery.arrowScroll(View.FOCUS_LEFT)

        mBtnJumpFirst = findViewById(R.id.btn_jump_first)
        mBtnLoop = findViewById(R.id.btn_loop)
    }

    class MyHandler(private val gallery: ViewPager) : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 10086) {
                gallery.currentItem = msg.obj as Int
            }
        }
    }

    private lateinit var mHandler: MyHandler

    /**
     * 循环滑动轮播
     */
    private fun loopScroll() {
        mHandler = MyHandler(mGallery)
        // 轮播
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val msg = Message.obtain()
                msg.what = 10086

                if (mCurrentIndex >= mFruitList.size) {
                    mCurrentIndex = 0
                } else {
                    mCurrentIndex += 1
                }

                msg.obj = mCurrentIndex
                mHandler.sendMessage(msg)
            }
        }, 0, 3000)
    }

    /**
     * 滑动到第一个
     */
    private fun scrollToFirst() {
        // 这鬼滑动速度不知道怎么控制
        mGallery.setCurrentItem(0, true)
    }
}