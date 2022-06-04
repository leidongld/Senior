package com.openld.senior.uisection.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.openld.senior.R
import com.openld.senior.uisection.testanimation.TestAnimationActivity
import com.openld.senior.uisection.testanimparallax.TestAnimParallaxActivity
import com.openld.senior.uisection.testappbarlayout.TestAppBarLayoutActivity
import com.openld.senior.uisection.testbehavior.TestBehaviorActivity
import com.openld.senior.uisection.testcardview.TestCardViewActivity
import com.openld.senior.uisection.testcheckbox.TestCheckBoxActivity
import com.openld.senior.uisection.testcoordinatorlayout.TestCoordinatorLayoutActivity
import com.openld.senior.uisection.testdrawerlayout.TestDrawerLayoutActivity
import com.openld.senior.uisection.testevent.TestEventActivity
import com.openld.senior.uisection.testimmersion.TestImmersionActivity
import com.openld.senior.uisection.testmaterialdesignanimation.TestMDAnimActivity
import com.openld.senior.uisection.testnavigationview.TestNavigationViewActivity
import com.openld.senior.uisection.testpalette.TestPaletteActivity
import com.openld.senior.uisection.testrecyclerview.TestRecyclerViewActivity
import com.openld.senior.uisection.testsnackbar.TestSnackbarActivity
import com.openld.senior.uisection.testsvg.TestSVGActivity
import com.openld.senior.uisection.testtablayout.TestTabLayoutActivity
import com.openld.senior.uisection.testtextinputlayout.TestTextInputLayoutActivity
import com.openld.senior.uisection.testtoolbar.TestToolbarActivity
import com.openld.senior.uisection.testtransition.TestTransitionActivity
import com.openld.senior.utils.PageUtils

/**
 * 高级UI主页
 */
class UIMainActivity : AppCompatActivity() {
    private lateinit var mBtnRecyclerView: AppCompatButton

    private lateinit var mBtnDrawerLayout: AppCompatButton

    private lateinit var mBtnNavigationView: AppCompatButton

    private lateinit var mBtnSnackBar: AppCompatButton

    private lateinit var mBtnTextInputLayout: AppCompatButton

    private lateinit var mBtnToolBar: AppCompatButton

    private lateinit var mBtnCheckBox: AppCompatButton

    private lateinit var mBtnPalette: AppCompatButton

    private lateinit var mBtnTableLayout: AppCompatButton

    private lateinit var mBtnImmersion: AppCompatButton

    private lateinit var mBtnCardView: AppCompatButton

    private lateinit var mBtnCoordinateLayout: AppCompatButton

    private lateinit var mBtnAppBarLayout: AppCompatButton

    private lateinit var mBtnBehavior: AppCompatButton

    private lateinit var mBtnAnimation: AppCompatButton

    private lateinit var mBtnMDAnimation: AppCompatButton

    private lateinit var mBtnTransition: AppCompatButton

    private lateinit var mBtnSVG: AppCompatButton

    private lateinit var mBtnParallaxAnim: AppCompatButton

    private lateinit var mBtnEvent: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uimain)

        initWidgets()

        addListeners()
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

        mBtnTableLayout = findViewById(R.id.btn_table_layout)

        mBtnImmersion = findViewById(R.id.btn_immersion)

        mBtnCardView = findViewById(R.id.btn_card_view)

        mBtnCoordinateLayout = findViewById(R.id.btn_coordinator_layout)

        mBtnAppBarLayout = findViewById(R.id.btn_app_bar_layout)

        mBtnBehavior = findViewById(R.id.btn_behavior)

        mBtnAnimation = findViewById(R.id.btn_animation)

        mBtnMDAnimation = findViewById(R.id.btn_md_animation)

        mBtnTransition = findViewById(R.id.btn_transition)

        mBtnSVG = findViewById(R.id.btn_svg)

        mBtnParallaxAnim = findViewById(R.id.btn_parallax_anim)

        mBtnEvent = findViewById(R.id.btn_event)
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
            PageUtils.jumpToPage(this, TestSnackbarActivity::class.java)
        }

        mBtnTextInputLayout.setOnClickListener {
            PageUtils.jumpToPage(this, TestTextInputLayoutActivity::class.java)
        }

        mBtnToolBar.setOnClickListener {
            PageUtils.jumpToPage(this, TestToolbarActivity::class.java)
        }

        mBtnCheckBox.setOnClickListener {
            PageUtils.jumpToPage(this, TestCheckBoxActivity::class.java)
        }

        mBtnPalette.setOnClickListener {
            PageUtils.jumpToPage(this, TestPaletteActivity::class.java)
        }

        mBtnTableLayout.setOnClickListener {
            PageUtils.jumpToPage(this, TestTabLayoutActivity::class.java)
        }

        mBtnImmersion.setOnClickListener {
            PageUtils.jumpToPage(this, TestImmersionActivity::class.java)
        }

        mBtnCardView.setOnClickListener {
            PageUtils.jumpToPage(this, TestCardViewActivity::class.java)
        }

        mBtnCoordinateLayout.setOnClickListener {
            PageUtils.jumpToPage(this, TestCoordinatorLayoutActivity::class.java)
        }

        mBtnAppBarLayout.setOnClickListener {
            PageUtils.jumpToPage(this, TestAppBarLayoutActivity::class.java)
        }

        mBtnBehavior.setOnClickListener {
            PageUtils.jumpToPage(this, TestBehaviorActivity::class.java)
        }

        mBtnAnimation.setOnClickListener {
            PageUtils.jumpToPage(this, TestAnimationActivity::class.java)
        }

        mBtnMDAnimation.setOnClickListener {
            PageUtils.jumpToPage(this, TestMDAnimActivity::class.java)
        }

        mBtnTransition.setOnClickListener {
            PageUtils.jumpToPage(this, TestTransitionActivity::class.java)
        }

        mBtnSVG.setOnClickListener {
            PageUtils.jumpToPage(this, TestSVGActivity::class.java)
        }

        mBtnParallaxAnim.setOnClickListener {
            PageUtils.jumpToPage(this, TestAnimParallaxActivity::class.java)
        }

        mBtnEvent.setOnClickListener {
            PageUtils.jumpToPage(this, TestEventActivity::class.java)
        }
    }
}