package com.openld.seniorui.testanimparallax

/**
 * author: lllddd
 * created on: 2022/5/23 10:23
 * description:视差动画View自定义的Tag
 */
class ParallaxViewTag(
    var index: Int,
    var xIn: Float,
    var xOut: Float,
    var yIn: Float,
    var yOut: Float,
    var alphaIn: Float,
    var alphaOut: Float
) {
    constructor() : this(-1, 1F, 1F, 1F, 1F, 1F, 1F)

    override fun toString(): String {
        return "ParallaxViewTag [index = ${index}, xIn = ${xIn}, xOut = ${xOut}, yIn = ${yIn}, yOut = ${yOut}, alphaIn = ${alphaIn}, alphaOut = ${alphaOut}]"
    }
}