package com.openld.senior.uisection.testrecyclerview.listeners

/**
 * author: lllddd
 * created on: 2022/4/7 10:57
 * description:
 */
interface FruitItemTouchMoveListener {
    /**
     * 当拖拽的时候回调
     * 拖拽条目并实现刷新效果
     *
     * @param fromPosition 起始位置
     * @param toPosition 目标位置
     * @return 是否执行了move
     */
    fun onFruitItemMove(fromPosition: Int, toPosition: Int): Boolean

    /**
     * 当条目被移除时回调
     *
     * @param position 移除的位置
     * @return 是否移除成功
     */
    fun onFruitItemRemove(position: Int): Boolean
}