package com.openld.seniorstructure.testintentbigdata

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.openld.seniorstructure.R

class TestIntentTransBIgDataActivity : AppCompatActivity() {
    private lateinit var mBtnFail: AppCompatButton

    private lateinit var mBtnSuccess: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_intent_trans_big_data)

        initWidgets()

        addListeners()
    }

    private fun initWidgets() {
        mBtnFail = findViewById(R.id.btn_fail)
        mBtnSuccess = findViewById(R.id.btn_success)
    }

    private fun addListeners() {
        // 必崩溃
        mBtnFail.setOnClickListener {
            val intent = Intent(
                this,
                TestIntentTransBigDataResultActivity::class.java
            )
            val data = ByteArray(1024 * 1024)
            val bundle = Bundle()
            bundle.putByteArray("bigData", data)
            intent.putExtra("bundle", bundle)
            startActivity(intent)
        }

        // 可以传递大数据
        mBtnSuccess.setOnClickListener {
            val intent = Intent(
                this,
                TestIntentTransBigDataResultActivity::class.java
            )
            val data = ByteArray(1024 * 1024)
            val bundle = Bundle()
            val bigData = BigData(ByteArray(1024 * 1024))
            bundle.putBinder("bigData", bigData)
            intent.putExtra("bundle", bundle)
            startActivity(intent)
        }
    }
}

data class BigData(val byteArray: ByteArray) : Binder() {

}
