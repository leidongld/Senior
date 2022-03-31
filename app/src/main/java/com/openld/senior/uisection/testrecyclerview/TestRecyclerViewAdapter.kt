package com.openld.senior.uisection.testrecyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.openld.senior.R
import com.openld.senior.uisection.testrecyclerview.listeners.FruitClickListener
import com.openld.senior.uisection.testrecyclerview.listeners.FruitLongClickListener

/**
 * author: lllddd
 * created on: 2022/3/30 21:05
 * description:测试RecyclerView的适配器
 */
class TestRecyclerViewAdapter(
    @NonNull val context: Context,
    @NonNull val fruitList: List<FruitBean>
) :
    Adapter<TestRecyclerViewAdapter.Companion.ViewHolder>() {

    private val mContext = context
    private val mFruitList: MutableList<FruitBean> = fruitList as MutableList<FruitBean>

    // 点击监听器
    private lateinit var mFruitClickListener: FruitClickListener

    // 长点击监听器
    private lateinit var mFruitLongClickListener: FruitLongClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(mContext).inflate(
                R.layout.item_test_recycler_view, parent, false
            )
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = mFruitList[position]

        if (fruit == null) {
            return
        }

        holder.imgFruit.setImageResource(fruit.image)
        holder.txtFruitName.setText(fruit.name)
        holder.txtFruitPrice.setText("${fruit.price} 元")

        holder.imgFruit.setOnClickListener {
            if (mFruitClickListener != null) {
                mFruitClickListener.onFruitClick(fruit, holder.layoutPosition)
            }
        }

        holder.imgFruit.setOnLongClickListener {
            if (mFruitLongClickListener != null) {
                mFruitLongClickListener.onFruitLongCLick(fruit, holder.layoutPosition)
            }
            true
        }
    }

    override fun getItemCount(): Int {
        return mFruitList.size
    }

    /**
     * 添加一个水果
     *
     * @param position 将要加的位置
     * @param fruit 要添加的水果
     */
    fun addFruit(position: Int, @NonNull fruit: FruitBean) {
        try {
            mFruitList.add(position, fruit)
            notifyItemInserted(position)
        } catch (e: Exception) {
            throw RuntimeException("添加水果错误，请检查位置position是否合理！！！")
        }
    }

    /**
     * 移除一个水果
     *
     * @param position 移除水果所在的位置
     */
    fun removeFruit(position: Int) {
        try {
            mFruitList.removeAt(position)
            notifyItemRemoved(position)
        } catch (e: Exception) {
            throw RuntimeException("移除水果错误，请检查位置position是否合理！！！")
        }
    }

    /**
     * 设置水果点击监听器
     *
     * @param listener 水果点击监听器
     */
    fun setFruitClickListener(@NonNull listener: FruitClickListener) {
        this.mFruitClickListener = listener
    }

    /**
     * 设置水果长点击监听器
     *
     * @param listener 水果长点击监听器
     */
    fun setFruitLongClickListener(@NonNull listener: FruitLongClickListener) {
        this.mFruitLongClickListener = listener
    }

    companion object {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imgFruit: AppCompatImageView = itemView.findViewById(R.id.img_fruit)
            val txtFruitName: AppCompatTextView = itemView.findViewById(R.id.txt_fruit_name)
            val txtFruitPrice: AppCompatTextView = itemView.findViewById(R.id.txt_fruit_price)
        }
    }
}
