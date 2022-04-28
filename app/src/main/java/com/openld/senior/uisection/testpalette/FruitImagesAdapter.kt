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
import androidx.recyclerview.widget.RecyclerView
import com.openld.senior.R

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
        val palette = Palette.from(bitmap).generate(Palette.PaletteAsyncListener {
            holder.txtFruitDesc.setBackgroundColor(it?.getLightVibrantColor(Color.WHITE) ?: Color.WHITE)
        })

    }

    override fun getItemCount(): Int {
        return fruitImageList.size
    }

    class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgFruit: AppCompatImageView = itemView.findViewById(R.id.img_fruit)
        var txtFruitDesc: AppCompatTextView = itemView.findViewById(R.id.txt_fruit_desc)
    }
}


