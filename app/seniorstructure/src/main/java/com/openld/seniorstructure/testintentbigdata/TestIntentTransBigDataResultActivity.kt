package com.openld.seniorstructure.testintentbigdata

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.openld.seniorstructure.R

class TestIntentTransBigDataResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_intent_trans_big_data_result)

        val bundle = intent.getBundleExtra("bundle")
        val ba = bundle?.getBinder("bigData") as BigData
        Toast.makeText(this, "" + ba.byteArray.size, Toast.LENGTH_LONG).show()
    }
}