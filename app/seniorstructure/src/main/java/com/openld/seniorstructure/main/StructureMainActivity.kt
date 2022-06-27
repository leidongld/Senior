package com.openld.seniorstructure.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.openld.seniorstructure.R
import com.openld.seniorstructure.main.testintentbigdata.TestIntentTransBIgDataActivity
import com.openld.seniorstructure.main.testintentbigdata.TestIntentTransBigDataResultActivity
import com.openld.seniorutils.utils.PageUtils

class StructureMainActivity : AppCompatActivity() {
    private lateinit var mBtnIntentBigData: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_structure_main)

        initWidgets()

        addListeners()
    }

    private fun initWidgets() {
        mBtnIntentBigData = findViewById(R.id.btn_intent_big_data)
    }

    private fun addListeners() {
        mBtnIntentBigData.setOnClickListener {
            PageUtils.jumpToPage(this, TestIntentTransBIgDataActivity::class.java)
        }
    }
}