package com.openld.seniorui.testdust

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.openld.seniorui.R
import com.openld.seniorui.testdust.view.DustView

class TestDustActivity : AppCompatActivity() {
    private lateinit var mDustView: DustView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dust)

        mDustView = findViewById(R.id.dust_view)
    }
}