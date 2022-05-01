package com.openld.senior.uisection.testtablayout

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.openld.senior.R

/**
 * author: lllddd
 * created on: 2022/4/29 15:53
 * description:
 */
class TabFragment(private val contentLayoutId: Int) : Fragment(contentLayoutId) {
    private lateinit var mTxt: AppCompatTextView
    private lateinit var mImg: AppCompatImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.apply {
            mTxt = findViewById(R.id.txt)

            val bundle = arguments
            val title = arguments?.getString("title")

            mTxt.text = title

            setBackgroundColor(
                Color.rgb(
                    (Math.random() * 255).toInt(),
                    (Math.random() * 255).toInt(),
                    (Math.random() * 255).toInt()
                )
            )
        }
    }
}