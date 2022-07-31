package com.openld.seniorui.testplanet.planet

import android.graphics.PointF

/**
 * author: lllddd
 * created on: 2022/7/21 15:00
 * description:节点Tag定义
 */
data class Tag(
    val x: Float,
    val y: Float,
    val z: Float,
    val scale: Float = DEFAULT_SCALE,
    val popularity: Float = DEFAULT_POPULARITY
) {
    companion object {
        private const val DEFAULT_POPULARITY = 5.0F
        private const val DEFAULT_SCALE = 1.0F
    }

    var mPointF: PointF = PointF(x, y)

    var mPoint3F: Point3F = Point3F(x, y, z)

    var mColor = arrayOf<Float>(
        1.0F, 0.5F, 0.5F, 0.5F
    )

    fun setFlatX(x: Float) {
        mPointF.x = x
    }

    fun setFlatY(y: Float) {
        mPointF.y = y
    }

    fun setSpatialX(x: Float) {
        mPoint3F.x = x
    }

    fun setSpatialY(y: Float) {
        mPoint3F.y = y
    }

    fun setSpatialZ(z: Float) {
        mPoint3F.z = z
    }
}