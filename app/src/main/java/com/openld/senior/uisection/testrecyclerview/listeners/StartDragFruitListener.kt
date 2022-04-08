package com.openld.senior.uisection.testrecyclerview.listeners

import androidx.recyclerview.widget.RecyclerView

/**
 * author: lllddd
 * created on: 2022/4/6 16:50
 * description:水果拖拽监听器
 */
interface StartDragFruitListener {
    /**
     * 水果被拖拽
     */
    fun onFruitStartDrag(viewHolder: RecyclerView.ViewHolder)
}