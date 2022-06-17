package com.openld.seniorui.testinflate

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.openld.seniorui.R

class TestInflateActivity : AppCompatActivity() {
    // 下方添加子View的布局
    private lateinit var mLlyRoot: LinearLayout

    // 膨胀View或者ViewGroup   root != null   attachToRoot = true
    private lateinit var mBtnInflateViewAttach: Button
    private lateinit var mBtnInflateViewGroupAttach: Button

    // 膨胀View或者ViewGroup   root != null   attachToRoot = false
    private lateinit var mBtnInflateViewNotAttach: Button
    private lateinit var mBtnInflateViewGroupNotAttach: Button

    // 膨胀View或者ViewGroup   root == null   隐含attachToRoot = false
    private lateinit var mBtnInflateViewNoRoot: Button
    private lateinit var mBtnInflateViewGroupNoRoot: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_inflate)

        initWidgets()

        addListeners()
    }

    private fun initWidgets() {
        mBtnInflateViewAttach = findViewById(R.id.btn_inflate_view_attach)
        mBtnInflateViewGroupAttach = findViewById(R.id.btn_inflate_viewgroup_attach)

        mBtnInflateViewNotAttach = findViewById(R.id.btn_inflate_view_not_attach)
        mBtnInflateViewGroupNotAttach = findViewById(R.id.btn_inflate_viewgroup_not_attach)

        mBtnInflateViewNoRoot = findViewById(R.id.btn_inflate_view_no_root)
        mBtnInflateViewGroupNoRoot = findViewById(R.id.btn_inflate_viewgroup_no_root)

        mLlyRoot = findViewById(R.id.lly_root)
    }

    private fun addListeners() {
        mBtnInflateViewAttach.setOnClickListener {
            onClickInflateViewAttach()
        }

        mBtnInflateViewGroupAttach.setOnClickListener {
            onClickInflateViewGroupAttach()
        }

        mBtnInflateViewNotAttach.setOnClickListener {
            onClickInflateViewNotAttach()
        }

        mBtnInflateViewGroupNotAttach.setOnClickListener {
            onClickInflateViewGroupNotAttach()
        }

        mBtnInflateViewNoRoot.setOnClickListener {
            onClickInflateViewNoRoot()
        }

        mBtnInflateViewGroupNoRoot.setOnClickListener {
            onClickInflateViewGroupNoRoot()
        }

        mLlyRoot.setOnLongClickListener {
            mLlyRoot.removeAllViews()
            Toast.makeText(this, "清除了所有子View！！！", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun onClickInflateViewAttach() {
        val view = LayoutInflater.from(this).inflate(R.layout.this_is_view, mLlyRoot, true)

        // 此时不能再调用addView()了因为已经add了一次
        //The specified child already has a parent. You must call removeView() on the child's parent first.
//        mLlyRoot.addView(view)
    }

    private fun onClickInflateViewGroupAttach() {
        val viewG = LayoutInflater.from(this).inflate(R.layout.this_is_viewgroup, mLlyRoot, true)

        // 此时不能再调用addView()了因为已经add了一次
        //The specified child already has a parent. You must call removeView() on the child's parent first.
//        mLlyRoot.addView(viewG)
    }

    private fun onClickInflateViewNotAttach() {
        val view = LayoutInflater.from(this).inflate(R.layout.this_is_view, mLlyRoot, false)
        mLlyRoot.addView(view)
    }

    private fun onClickInflateViewGroupNotAttach() {
        val viewG = LayoutInflater.from(this).inflate(R.layout.this_is_viewgroup, mLlyRoot, false)
        mLlyRoot.addView(viewG)
    }

    private fun onClickInflateViewNoRoot() {
        // root = null 的时候布局会丢失
        val view = LayoutInflater.from(this).inflate(R.layout.this_is_view, null)
        mLlyRoot.addView(view)
    }

    private fun onClickInflateViewGroupNoRoot() {
        // root = null 的时候布局会丢失
        val viewG = LayoutInflater.from(this).inflate(R.layout.this_is_viewgroup, null)
        mLlyRoot.addView(viewG)
    }
}