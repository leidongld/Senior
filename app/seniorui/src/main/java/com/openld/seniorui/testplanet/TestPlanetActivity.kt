package com.openld.seniorui.testplanet

import android.graphics.Point
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.openld.seniorui.R
import com.openld.seniorui.testplanet.planet.Point3F

class TestPlanetActivity : AppCompatActivity() {
    val p = Point3F()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_planet)

    }
}