package com.openld.senior.uisection.testsnackbar

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.snackbar.Snackbar
import com.openld.senior.R

class TestSnackBarActivity : AppCompatActivity() {
    private lateinit var mBtnShowSnackBar: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_snack_bar)

        initWidgets()

        addListeners()
    }

    private fun initWidgets() {
        mBtnShowSnackBar = findViewById(R.id.btn_show_snack_bar)
    }

    private fun addListeners() {
        mBtnShowSnackBar.setOnClickListener {
            showSnackBar(it)
        }
    }

    private fun showSnackBar(v: View?) {
        val snackBa =
            v?.let {
                Snackbar.make(this, it, "手机马上要爆炸了，是否逃跑？", Snackbar.LENGTH_INDEFINITE).apply {
                    setAction("取消", View.OnClickListener {
                        Toast.makeText(it.context, "爆炸了！！！", Toast.LENGTH_SHORT).show()
                    })
                    show()
                }
            }
    }
}