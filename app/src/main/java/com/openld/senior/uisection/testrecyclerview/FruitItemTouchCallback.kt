package com.openld.senior.uisection.testrecyclerview

import android.graphics.Canvas
import android.graphics.Color
import androidx.annotation.NonNull
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.openld.senior.R
import com.openld.senior.uisection.testrecyclerview.listeners.FruitItemTouchMoveListener

/**
 * author: lllddd
 * created on: 2022/4/6 15:25
 * description:自定义水果触摸回调类
 */
class FruitItemTouchCallback() : ItemTouchHelper.Callback() {
    private lateinit var mFruitItemTouchMoveListener: FruitItemTouchMoveListener

    constructor(@NonNull fruitItemTouchMoveListener: TestRecyclerViewAdapter) : this() {
        this.mFruitItemTouchMoveListener = fruitItemTouchMoveListener
    }


    /**
     * Callback回调监听时先调用的，用来判断当前是什么动作，比如判断方向（意思就是我眼监听那个方向的拖动）
     */
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        // 0x0001
        val up = ItemTouchHelper.UP

        // 0x0010
        val down = ItemTouchHelper.DOWN

        // 0x0100
        val left = ItemTouchHelper.LEFT

        // 0x1000
        val right = ItemTouchHelper.RIGHT

        // FIXME:  这里有点问题，方向控制不准，按位或下来都是0啊
        val dragFlags = up or down
        val swipeFlags = left or right

        return makeMovementFlags(dragFlags, swipeFlags)
    }

    /**
     * 是否打开长按拖拽效果
     */
    override fun isLongPressDragEnabled(): Boolean {
        return super.isLongPressDragEnabled()
    }

    /**
     * 拖拽
     */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        if (viewHolder.itemViewType != target.itemViewType) {
            return false
        }
        // 在拖拽的过程中不断调用adapter.notifyItemMoved()方法
        return mFruitItemTouchMoveListener.onFruitItemMove(
            viewHolder.adapterPosition,
            target.adapterPosition
        )
    }


    /**
     * 侧滑
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        mFruitItemTouchMoveListener.onFruitItemRemove(viewHolder.adapterPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder?.itemView?.context?.resources?.let {
                viewHolder.itemView.setBackgroundColor(it.getColor(R.color.purple_200))
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        viewHolder?.itemView?.context?.resources?.let {
            viewHolder.itemView.setBackgroundColor(Color.WHITE)
        }
        // 不还原为1的话，滑动删除后会有空白条目
        viewHolder.itemView.alpha = 1F
        viewHolder.itemView.scaleX = 1F
        super.clearView(recyclerView, viewHolder)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            var alpha = 1 - Math.abs(dX) / viewHolder.itemView.width
            viewHolder.itemView.alpha = alpha
            viewHolder.itemView.scaleX = alpha
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun onChildDrawOver(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder?,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}