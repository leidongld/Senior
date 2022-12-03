package com.openld.seniorui.testdust.particle

/**
 * author: lllddd
 * created on: 2022/11/8 20:17
 * description:粒子
 */
data class Particle(
    var x : Float,
    var y : Float,
    val radius : Float,
    val speed : Float,
    val sinTheta : Float,
    val cosTheta : Float
    ) {
}