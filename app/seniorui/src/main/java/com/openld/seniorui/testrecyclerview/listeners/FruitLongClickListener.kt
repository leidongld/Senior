package com.openld.seniorui.testrecyclerview.listeners

import com.openld.seniorui.testrecyclerview.FruitBean


/**
 * author: lllddd
 * created on: 2022/3/31 12:36
 * description:水果长点击事件
 */
interface FruitLongClickListener {
    fun onFruitLongCLick(fruit: FruitBean, position: Int)
}