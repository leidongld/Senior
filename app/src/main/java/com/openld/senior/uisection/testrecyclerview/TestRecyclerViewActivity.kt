package com.openld.senior.uisection.testrecyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.openld.senior.R
import com.openld.senior.uisection.testrecyclerview.listeners.FruitClickListener
import com.openld.senior.uisection.testrecyclerview.listeners.FruitLongClickListener

/**
 * 测试学习一下RecyclerView
 */
class TestRecyclerViewActivity : AppCompatActivity(), FruitClickListener, FruitLongClickListener {
    private lateinit var mRcv: RecyclerView

    private lateinit var mBtnChange: AppCompatButton

    private lateinit var mBtnAddItem: AppCompatButton

    private lateinit var mBtnRemoveOne: AppCompatButton

    private lateinit var mAdapter: TestRecyclerViewAdapter

    private var mFruitList = mutableListOf<FruitBean>()

    private var mShowMode = ShowMode.LIST

    /**
     * 列表展示的模式
     */
    private enum class ShowMode {
        LIST,
        GRID
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_recycler_view)

        // 初始化数据
        initData()

        // 初始化控件
        initWidgets()

        // 增加监听器
        addListeners()
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        mFruitList.add(FruitBean("苹果", 5.0f, R.drawable.icon_apple))
        mFruitList.add(FruitBean("香蕉", 3.0f, R.drawable.icon_banana))
        mFruitList.add(FruitBean("树莓", 15.0f, R.drawable.icon_berry))
        mFruitList.add(FruitBean("樱桃", 50.0f, R.drawable.icon_cherry))
        mFruitList.add(FruitBean("榴莲", 60.0f, R.drawable.icon_durian))
        mFruitList.add(FruitBean("葡萄", 9.0f, R.drawable.icon_grape))
        mFruitList.add(FruitBean("哈密瓜", 8.0f, R.drawable.icon_hami_melon))
        mFruitList.add(FruitBean("奇异果", 5.0f, R.drawable.icon_kiwi_fruit))
        mFruitList.add(FruitBean("柠檬", 5.0f, R.drawable.icon_lemon))
        mFruitList.add(FruitBean("芒果", 5.0f, R.drawable.icon_mongo))
        mFruitList.add(FruitBean("橙子", 5.0f, R.drawable.icon_orange))
        mFruitList.add(FruitBean("鸭梨", 5.0f, R.drawable.icon_pear))
        mFruitList.add(FruitBean("柿子", 5.0f, R.drawable.icon_persimmon))
        mFruitList.add(FruitBean("菠萝", 5.0f, R.drawable.icon_pineapple))
        mFruitList.add(FruitBean("火龙果", 5.0f, R.drawable.icon_pitaya))
        mFruitList.add(FruitBean("草莓", 5.0f, R.drawable.icon_strawberry))
        mFruitList.add(FruitBean("西瓜", 5.0f, R.drawable.icon_watermelon))
        mFruitList.add(FruitBean("莲雾", 5.0f, R.drawable.icon_wax_apple))
    }

    /**
     * 增加监听器
     */
    private fun addListeners() {
        mBtnChange.setOnClickListener {
            if (mShowMode == ShowMode.LIST) {
                mRcv.layoutManager = GridLayoutManager(this, 2)
                mShowMode = ShowMode.GRID
                Toast.makeText(this, "切换为列表展示", Toast.LENGTH_SHORT).show()
            } else {
                mRcv.layoutManager = LinearLayoutManager(this)
                mShowMode = ShowMode.LIST
                Toast.makeText(this, "切换为网格展示", Toast.LENGTH_SHORT).show()
            }
        }

        mBtnAddItem.setOnClickListener {
            mAdapter.addFruit(0, FruitBean("蜜桃", 20.0f, R.drawable.icon_peach))
            mRcv.scrollToPosition(0)
            Toast.makeText(this, "添加了一个水果", Toast.LENGTH_SHORT).show()
        }

        mBtnRemoveOne.setOnClickListener {
            mAdapter.removeFruit(0)
            Toast.makeText(this, "移除了一个水果", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 初始化控件
     */
    private fun initWidgets() {
        mRcv = findViewById(R.id.rcv)
        mAdapter = TestRecyclerViewAdapter(this, mFruitList)
        mAdapter.setFruitClickListener(this)
        mAdapter.setFruitLongClickListener(this)
        // 设置一下默认的动画效果
        mRcv.itemAnimator = DefaultItemAnimator()
        mRcv.addItemDecoration(TestRecyclerViewItemDecoration(this).apply {
            setOrientation(1)
        })
        mRcv.adapter = mAdapter

        mBtnChange = findViewById(R.id.btn_change)

        mBtnAddItem = findViewById(R.id.btn_add_item)

        mBtnRemoveOne = findViewById(R.id.btn_remove_item)
    }

    /**
     * 水果点击监听
     *
     * @param fruit 点击的水果
     * @param position 点击水果的位置
     */
    override fun onFruitClick(fruit: FruitBean, position: Int) {
        Toast.makeText(this, "点击了第${position}个水果", Toast.LENGTH_SHORT).show()
    }

    /**
     * 水果长按监听
     *
     * @param fruit 点击的水果
     * @param position 点击水果的位置
     */
    override fun onFruitLongCLick(fruit: FruitBean, position: Int) {
        Toast.makeText(this, "长按点击了第${position}个水果", Toast.LENGTH_SHORT).show()
    }
}