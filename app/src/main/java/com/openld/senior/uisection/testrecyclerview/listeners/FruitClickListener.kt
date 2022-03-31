package com.openld.senior.uisection.testrecyclerview.listeners

import com.openld.senior.uisection.testrecyclerview.FruitBean

/**
 * author: lllddd
 * created on: 2022/3/31 12:35
 * description:水果点击事件
 */
interface FruitClickListener {
    fun onFruitClick(fruit: FruitBean, position: Int)
}