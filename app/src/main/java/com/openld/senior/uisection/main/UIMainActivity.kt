package com.openld.senior.uisection.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.openld.senior.R
import com.openld.senior.uisection.testcheckbox.TestCheckBoxActivity
import com.openld.senior.uisection.testdrawerlayout.TestDrawerLayoutActivity
import com.openld.senior.uisection.testnavigationview.TestNavigationViewActivity
import com.openld.senior.uisection.testpalette.TestPaletteActivity
import com.openld.senior.uisection.testrecyclerview.TestRecyclerViewActivity
import com.openld.senior.uisection.testsnackbar.TestSnackBarActivity
import com.openld.senior.uisection.testtextinputlayout.TestTextInputLayoutActivity
import com.openld.senior.uisection.testtoolbar.TestToolBarActivity
import com.openld.senior.utils.PageUtils

class UIMainActivity : AppCompatActivity() {
    private lateinit var mBtnRecyclerView: AppCompatButton

    private lateinit var mBtnDrawerLayout: AppCompatButton

    private lateinit var mBtnNavigationView: AppCompatButton

    private lateinit var mBtnSnackBar: AppCompatButton

    private lateinit var mBtnTextInputLayout: AppCompatButton

    private lateinit var mBtnToolBar: AppCompatButton

    private lateinit var mBtnCheckBox: AppCompatButton

    private lateinit var mBtnPalette: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uimain)

        initWidgets()

        addListeners()
    }

    private fun addListeners() {
        mBtnRecyclerView.setOnClickListener {
            PageUtils.jumpToPage(this, TestRecyclerViewActivity::class.java)
        }

        mBtnDrawerLayout.setOnClickListener {
            PageUtils.jumpToPage(this, TestDrawerLayoutActivity::class.java)
        }

        mBtnNavigationView.setOnClickListener {
            PageUtils.jumpToPage(this, TestNavigationViewActivity::class.java)
        }

        mBtnSnackBar.setOnClickListener {
            PageUtils.jumpToPage(this, TestSnackBarActivity::class.java)
        }

        mBtnTextInputLayout.setOnClickListener {
            PageUtils.jumpToPage(this, TestTextInputLayoutActivity::class.java)
        }

        mBtnToolBar.setOnClickListener {
            PageUtils.jumpToPage(this, TestToolBarActivity::class.java)
        }

        mBtnCheckBox.setOnClickListener {
            PageUtils.jumpToPage(this, TestCheckBoxActivity::class.java)
        }

        mBtnPalette.setOnClickListener {
            PageUtils.jumpToPage(this, TestPaletteActivity::class.java)
        }
    }

    private fun initWidgets() {
        mBtnRecyclerView = findViewById(R.id.btn_recycler_view)

        mBtnDrawerLayout = findViewById(R.id.btn_drawer_layout)

        mBtnNavigationView = findViewById(R.id.btn_navigation_view)

        mBtnSnackBar = findViewById(R.id.btn_snack_bar)

        mBtnTextInputLayout = findViewById(R.id.btn_text_input_layout)

        mBtnToolBar = findViewById(R.id.btn_toolbar)

        mBtnCheckBox = findViewById(R.id.btn_checkbox)

        mBtnPalette = findViewById(R.id.btn_palette)
    }
}