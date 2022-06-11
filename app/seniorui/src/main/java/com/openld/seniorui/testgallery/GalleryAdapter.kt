package com.openld.seniorui.testgallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.openld.seniorui.R

class GalleryAdapter(@NonNull context: Context, @NonNull fruitList: List<Int>) :
    PagerAdapter() {
    private val mContext = context
    private var mFruitList: List<Int> = fruitList

    override fun getCount(): Int {
        return mFruitList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return LayoutInflater.from(mContext).inflate(R.layout.item_gallery, container, false)
            .apply {
                val imgFruit: AppCompatImageView = findViewById(R.id.img_fruit)
                imgFruit.setImageResource(mFruitList[position])
                imgFruit.setOnClickListener {
                    Toast.makeText(mContext, "点击了第${position}个图片", Toast.LENGTH_SHORT).show()
                }

                tag = "gallery_item_$position"
                container.addView(this)
            }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
