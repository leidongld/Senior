package com.openld.senior.uisection.testpalette

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Target
import androidx.recyclerview.widget.RecyclerView
import com.openld.senior.R
import kotlin.math.max
import kotlin.math.min

class FruitImagesAdapter(@NonNull val fruitImageList: MutableList<FruitImageBean>) :
    RecyclerView.Adapter<FruitImagesAdapter.ViewHolder>() {
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_fruit_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruitImageBean = fruitImageList[position]

        holder.imgFruit.setImageResource(fruitImageBean.imageRes)
        holder.txtFruitDesc.text = fruitImageBean.imageDesc

        val bitmap: Bitmap =
            BitmapFactory.decodeResource(mContext.resources, fruitImageBean.imageRes)

        // 调色板，得到Bitmap中的一些色彩信息
        // FIXME: 这里会卡主线程，后面最好使用线程池来做一个优化
        Palette.from(bitmap).generate(Palette.PaletteAsyncListener {
            val baseColor = it?.getMutedColor(Color.WHITE) ?: Color.WHITE
            val realColor = getColorWithAlpha(0.8f, baseColor)
            holder.txtFruitDesc.setBackgroundColor(realColor)

            val swatch = it?.getSwatchForTarget(Target.MUTED)
            holder.txtFruitDesc.setTextColor(swatch?.titleTextColor ?: Color.BLACK)
        })


    }

    /**
     * 给color添加透明度
     *
     * @param alpha 透明度 0f～1f
     * @param baseColor 基本颜色
     * @return
     */
    private fun getColorWithAlpha(alpha: Float, baseColor: Int): Int {
        val a = min(255, max(0, (alpha * 255).toInt())) shl 24
        val rgb = 0x00ffffff and baseColor
        return a + rgb
    }

    override fun getItemCount(): Int {
        return fruitImageList.size
    }

    class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgFruit: AppCompatImageView = itemView.findViewById(R.id.img_fruit)
        var txtFruitDesc: AppCompatTextView = itemView.findViewById(R.id.txt_fruit_desc)
    }
}


