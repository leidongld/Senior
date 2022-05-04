package com.openld.senior.uisection.testcoordinatorlayout

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.openld.senior.R
import com.openld.senior.uisection.testpalette.FruitImageBean
import com.openld.senior.uisection.testpalette.FruitImagesAdapter

class TestCoordinatorLayoutActivity : AppCompatActivity() {
    private lateinit var mColyContainer: CoordinatorLayout

    private lateinit var mToolbar: Toolbar

    private lateinit var mRcvFruits: RecyclerView

    private lateinit var mAdapter: FruitImagesAdapter
    private val mFruitImageList = mutableListOf<FruitImageBean>()

    private lateinit var mFloatingBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_coordinator_layout)

        initData()

        initWidgets()

        addListeners()
    }

    private fun initData() {
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image1, "闻完了香味，就该让嘴巴开心了！"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image2, "秋天是黄澄澄的，秋天是红通通，秋天是紫盈盈的，秋天是橘黄橘黄的。"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image3, "秋天是黄澄澄的，秋天是红通通，秋天是紫盈盈的，秋天是橘黄橘黄的。"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image4, "新鲜 香甜 酸甜 饱满"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image5, "那柚子皮上像涂过一层桐油，黄黄的、光光的，香味幽雅而清淡，使人闻着直流口水。"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image6, "一股甜滋滋的甘露顺着喉咙流进心田，使人舒服极了！"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image7, "苹安富贵，苹平安安"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image8, "樱桃真好吃！！！"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image9, "味道凉凉的，滑滑的，唇齿留香，回味无穷，特别好吃。"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image10, "瓜园远看一片绿汪汪的"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image11, "夏天必不可少的就是柠檬了！"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image12, "香甜可口 又香又甜"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image13, "多么惹人喜爱，多么橙黄啊"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image14, "一串串葡萄绿绿的，晶莹透明，真像是用水晶和玉石雕刻出来似的。"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image15, "柠檬金黄金黄的，像一块金子。"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image16, "那切开的西瓜好诱人，瓜瓤鲜红鲜红，籽儿乌黑乌黑。"))
        mFruitImageList.add(FruitImageBean(R.drawable.fruit_image17, "一串串葡萄绿绿的，晶莹透明，真像是用水晶和玉石雕刻出来似的。"))
    }

    private fun initWidgets() {
        mColyContainer = findViewById(R.id.coly_container)

        mToolbar = findViewById(R.id.toolbar)

        mRcvFruits = findViewById(R.id.rcv_fruits)
        mAdapter = FruitImagesAdapter(mFruitImageList)
        mRcvFruits.adapter = mAdapter

        mFloatingBtn = findViewById(R.id.fab)
    }

    private fun addListeners() {
        mToolbar.setOnClickListener{
            Toast.makeText(this, "点击了Toolbar！！！", Toast.LENGTH_SHORT).show()
        }

        mFloatingBtn.setOnClickListener{
            val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            it.startAnimation(animation)
            Toast.makeText(this, "点击了FloatingActionButton！！！", Toast.LENGTH_SHORT).show()
        }
    }
}