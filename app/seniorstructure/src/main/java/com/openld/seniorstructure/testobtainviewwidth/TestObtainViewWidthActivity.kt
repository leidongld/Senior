package com.openld.seniorstructure.testobtainviewwidth

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.openld.seniorstructure.R

class TestObtainViewWidthActivity : AppCompatActivity() {
    private lateinit var mBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_obtain_view_width)

        initWidgets()

        mBtn.post {
            Log.d(
                ">>>>>>",
                "onCreate 中调用mBtn.post  btn.width = ${mBtn.width}  btn.measuredWidth = ${mBtn.measuredWidth}"
            )
        }

        Log.d(
            ">>>>>>",
            "onCreate end: btn.width = ${mBtn.width}  btn.measuredWidth = ${mBtn.measuredWidth}"
        )
    }

    private fun initWidgets() {
        mBtn = findViewById(R.id.btn)
    }

    override fun onStart() {
        Log.d(
            ">>>>>>",
            "onStart start: btn.width = ${mBtn.width}  btn.measuredWidth = ${mBtn.measuredWidth}"
        )

        super.onStart()

        Log.d(
            ">>>>>>",
            "onStart end: btn.width = ${mBtn.width}  btn.measuredWidth = ${mBtn.measuredWidth}"
        )
    }

    override fun onResume() {
        Log.d(
            ">>>>>>",
            "onResume start: btn.width = ${mBtn.width}  btn.measuredWidth = ${mBtn.measuredWidth}"
        )

        super.onResume()

        Log.d(
            ">>>>>>",
            "onResume end: btn.width = ${mBtn.width}  btn.measuredWidth = ${mBtn.measuredWidth}"
        )
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (hasFocus) {
            Log.d(
                ">>>>>>",
                "onWindowFocusChanged  btn.width = ${mBtn.width}  btn.measuredWidth = ${mBtn.measuredWidth}"
            )
        }
    }
}