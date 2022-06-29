package com.openld.seniorui.testslidingmenu

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.openld.seniorui.R

class TestSlidingMenuActivity : AppCompatActivity() {
    private lateinit var mSlidingMenuContainer: SlidingMenuContainer

    private lateinit var mMenu: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_sliding_menu)

        initWidgets()
    }

    private fun initWidgets() {
        mSlidingMenuContainer = findViewById(R.id.sld_menu_container)

        mMenu = findViewById(R.id.menu)

        mMenu.findViewById<TextView>(R.id.txt1).setOnClickListener {
            Toast.makeText(this, "点击了巴旦木", Toast.LENGTH_SHORT).show()
        }

        mMenu.findViewById<TextView>(R.id.txt2).setOnClickListener {
            Toast.makeText(this, "点击了碧根果", Toast.LENGTH_SHORT).show()
        }

        mMenu.findViewById<TextView>(R.id.txt3).setOnClickListener {
            Toast.makeText(this, "点击了饼干", Toast.LENGTH_SHORT).show()
        }

        mMenu.findViewById<TextView>(R.id.txt4).setOnClickListener {
            Toast.makeText(this, "点击了布丁", Toast.LENGTH_SHORT).show()
        }

        mMenu.findViewById<TextView>(R.id.txt5).setOnClickListener {
            Toast.makeText(this, "点击了蚕豆", Toast.LENGTH_SHORT).show()
//        }
        }
    }
}