package com.openld.seniorui.testcards

/**
 * author: lllddd
 * created on: 2022/7/30 13:23
 * description:卡片点击监听
 */
interface OnCardClickListener {
    /**
     * 卡片点击的监听
     *
     * @param position 点击的卡片的位置
     * @param isFold 当前卡包是否折叠
     */
    fun onCardClicked(position: Int, isFold: Boolean)
}