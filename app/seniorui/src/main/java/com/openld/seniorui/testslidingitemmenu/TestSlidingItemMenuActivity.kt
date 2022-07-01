package com.openld.seniorui.testslidingitemmenu

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.openld.seniorui.R

class TestSlidingItemMenuActivity : AppCompatActivity() {
    private lateinit var mContainer: LinearLayout

    private lateinit var mSlidingItemMenu: SlidingItemMenuLayout

    private lateinit var mTxtContent: TextView
    private lateinit var mTxtDel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_sliding_item_menu)

        initWidgets()

        addListeners()
    }

    private fun initWidgets() {
        mContainer = findViewById(R.id.container)
        mSlidingItemMenu = findViewById(R.id.sliding_item_menu)
        mTxtContent = findViewById(R.id.txt_content)
        mTxtDel = findViewById(R.id.txt_del)
    }

    private fun addListeners() {
        mContainer.setOnClickListener {
            Toast.makeText(this, "点击了最外层容器", Toast.LENGTH_SHORT).show()
        }

        mTxtContent.setOnClickListener {
            Toast.makeText(this, "点击了内容", Toast.LENGTH_SHORT).show()
        }

        mTxtDel.setOnClickListener {
            Toast.makeText(this, "点击了删除", Toast.LENGTH_SHORT).show()
        }
    }
}