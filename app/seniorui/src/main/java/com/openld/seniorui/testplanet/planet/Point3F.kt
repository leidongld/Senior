package com.openld.seniorui.testplanet.planet

/**
 * author: lllddd
 * created on: 2022/7/21 14:57
 * description:三维空间的坐标点
 */
data class Point3F(var x: Float = DEFAULT_X, var y: Float = DEFAULT_Y, var z: Float = DEFAULT_Z) {
    companion object {
        private const val DEFAULT_X = 0F
        private const val DEFAULT_Y = 0F
        private const val DEFAULT_Z = 0F
    }
}