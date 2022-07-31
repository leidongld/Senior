package com.openld.seniorui.testcards

/**
 * author: lllddd
 * created on: 2022/7/31 9:38
 * description:卡片滚动监听
 */
interface OnCardScrollListener {
    /**
     * 当前滚动到的卡片游标
     *
     * @param index 卡片游标
     */
    fun onCardScrolled(index: Int)
}