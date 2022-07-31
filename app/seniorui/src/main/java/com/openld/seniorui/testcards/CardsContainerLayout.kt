package com.openld.seniorui.testcards

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.openld.seniorui.R
import kotlin.math.abs

/**
 * author: lllddd
 * created on: 2022/7/29 22:51
 * description:卡片容器布局
 */
class CardsContainerLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var mDensity = 0F
    private var mScreenWidth = 0

    private var mWidth = 0
    private var mHeight = 0

    private var mCardsCount = 0
    private var mCurrentIndex = 0

    private var mIsFold = false;

    private val DURATION = 600L
    private val DELAY = 60L

    var mOnCardClickListener: OnCardClickListener? = null

    init {
        orientation = LinearLayout.HORIZONTAL
        mDensity = context.resources.displayMetrics.density
        mScreenWidth = context.resources.displayMetrics.widthPixels
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setCards(@NonNull cards: List<CardBean>) {
        removeAllViews()

        for (index in cards.indices) {
            val childView = LayoutInflater.from(context).inflate(R.layout.item_card, this, false)
            val params =
                LinearLayout.LayoutParams((mWidth * 0.9F).toInt(), (mHeight * 0.9F).toInt())
            params.setMargins(
                (mWidth * 0.05F).toInt(),
                (mHeight * 0.05F).toInt(),
                (mWidth * 0.05F).toInt(),
                (mHeight * 0.05F).toInt()
            )

            childView.layoutParams = params

            val imageCard = childView.findViewById<ImageView>(R.id.img_card)
            imageCard.setImageResource(cards[index].image)

            val txtCard = childView.findViewById<TextView>(R.id.txt_card)
            txtCard.text = cards[index].title

            childView.setOnClickListener {
                Toast.makeText(context, "点击了第${index}个卡片", Toast.LENGTH_SHORT).show()
                childView.elevation = 10F
                childView.isClickable = false
                mCurrentIndex = index

                if (mIsFold) {// 当前是折叠态
                    // 点击展开
                    clickToUnFold(index)
                } else {// 当前是展开态
                    // 点击折叠
                    clickToFold(index)
                }

                mIsFold = !mIsFold
                mOnCardClickListener?.onCardClicked(index, mIsFold)
            }

            addView(childView)
        }
    }

    /**
     * 折叠，当前点击了第index个卡片
     */
    @SuppressLint("Recycle")
    private fun clickToFold(index: Int) {
        val totalDelay =
            abs(index - 0).coerceAtLeast(abs(index - (mCardsCount - 1))) * DELAY + DURATION

        for (i in 0 until childCount) {
            getChildAt(i).isClickable = false

            var left = index - 1
            var right = index + 1
            while (left >= 0 || right < childCount) {
                if (left >= 0 && right < childCount) {
                    val leftChild = getChildAt(left)
                    val rightChild = getChildAt(right)

                    leftChild.elevation = 10F - abs(index - left) * 0.1F
                    rightChild.elevation = 10F - abs(index - right) * 0.1F

                    val leftTranslationX = abs(index - left) * (mWidth - 100F)
                    val animLeft =
                        ObjectAnimator.ofFloat(leftChild, "translationX", 0F, leftTranslationX)
                    val animLeftScaleX =
                        ObjectAnimator.ofFloat(
                            leftChild,
                            "scaleX",
                            1F,
                            1F - abs(index - left) * 0.1F
                        )
                    val animLeftScaleY =
                        ObjectAnimator.ofFloat(
                            leftChild,
                            "scaleY",
                            1F,
                            1F - abs(index - left) * 0.1F
                        )

                    val rightTranslationX = abs(index - right) * (-mWidth + 100F)
                    val animRight =
                        ObjectAnimator.ofFloat(rightChild, "translationX", 0F, rightTranslationX)
                    val animRightScaleX = ObjectAnimator.ofFloat(
                        rightChild,
                        "scaleX",
                        1F,
                        1F - abs(index - left) * 0.1F
                    )
                    val animRightScaleY = ObjectAnimator.ofFloat(
                        rightChild,
                        "scaleY",
                        1F,
                        1F - abs(index - left) * 0.1F
                    )

                    val animSet = AnimatorSet().apply {
                        duration = DURATION
                        interpolator = AccelerateDecelerateInterpolator()
                        playTogether(
                            animLeft,
                            animLeftScaleX,
                            animLeftScaleY,
                            animRight,
                            animRightScaleX,
                            animRightScaleY
                        )
                        startDelay = (abs(index - left) * DELAY).toLong()
                        start()
                    }

                    left--;
                    right++;
                } else if (left >= 0) {
                    val leftChild = getChildAt(left)
                    leftChild.elevation = 10F - abs(index - left) * 0.1F

                    val leftTranslationX = abs(index - left) * (mWidth - 100F)
                    val animLeft =
                        ObjectAnimator.ofFloat(leftChild, "translationX", 0F, leftTranslationX)
                    val animLeftScaleX =
                        ObjectAnimator.ofFloat(
                            leftChild,
                            "scaleX",
                            1F,
                            1F - abs(index - left) * 0.1F
                        )
                    val animLeftScaleY =
                        ObjectAnimator.ofFloat(
                            leftChild,
                            "scaleY",
                            1F,
                            1F - abs(index - left) * 0.1F
                        )

                    val animSet = AnimatorSet().apply {
                        duration = DURATION
                        interpolator = AccelerateDecelerateInterpolator()
                        playTogether(animLeft, animLeftScaleX, animLeftScaleY)
                        startDelay = (abs(index - left) * DELAY).toLong()
                        start()
                    }

                    left--
                } else if (right < childCount) {
                    val rightChild = getChildAt(right)
                    rightChild.elevation = 10F - abs(index - right) * 0.1F

                    val rightTranslationX = abs(index - right) * (-mWidth + 100F)
                    val animRight =
                        ObjectAnimator.ofFloat(rightChild, "translationX", 0F, rightTranslationX)
                    val animRightScaleX = ObjectAnimator.ofFloat(
                        rightChild,
                        "scaleX",
                        1F,
                        1F - abs(index - right) * 0.1F
                    )
                    val animRightScaleY = ObjectAnimator.ofFloat(
                        rightChild,
                        "scaleY",
                        1F,
                        1F - abs(index - right) * 0.1F
                    )

                    val animSet = AnimatorSet().apply {
                        duration = DURATION
                        interpolator = AccelerateDecelerateInterpolator()
                        playTogether(animRight, animRightScaleX, animRightScaleY)
                        startDelay = (abs(index - left) * DELAY).toLong()
                        start()
                    }

                    right++;
                } else {
                    break
                }
            }

            postDelayed({
                getChildAt(index).isClickable = true
            }, totalDelay.toLong())
        }
    }

    /**
     * 展开，当前点击了第index个卡片
     */
    @SuppressLint("Recycle")
    private fun clickToUnFold(index: Int) {
        var left = index - 1
        var right = index + 1

        val totalDelay =
            abs(index - 0).coerceAtLeast(abs(1 + index - mCardsCount)) * DELAY + DURATION

        while (left >= 0 || right < childCount) {
            if (left >= 0 && right < childCount) {
                val leftChild = getChildAt(left)
                val rightChild = getChildAt(right)

                val animLeft =
                    ObjectAnimator.ofFloat(leftChild, "translationX", 0F)
                val animLeftScaleX = ObjectAnimator.ofFloat(leftChild, "scaleX", 1F)
                val animLeftScaleY = ObjectAnimator.ofFloat(leftChild, "scaleY", 1F)

                val animRight =
                    ObjectAnimator.ofFloat(rightChild, "translationX", 0F)
                val animRightScaleX = ObjectAnimator.ofFloat(rightChild, "scaleX", 1F)
                val animRightScaleY = ObjectAnimator.ofFloat(rightChild, "scaleY", 1F)

                val animSet = AnimatorSet().apply {
                    duration = DURATION
                    interpolator = AccelerateInterpolator()
                    playTogether(
                        animLeft,
                        animLeftScaleX,
                        animLeftScaleY,
                        animRight,
                        animRightScaleX,
                        animRightScaleY
                    )
                    startDelay = (abs(index - left) * DELAY).toLong()
                    start()
                }

                left--
                right++
            } else if (left >= 0) {
                val leftChild = getChildAt(left)

                val animLeft =
                    ObjectAnimator.ofFloat(leftChild, "translationX", 0F)
                val animLeftScaleX = ObjectAnimator.ofFloat(leftChild, "scaleX", 1F)
                val animLeftScaleY = ObjectAnimator.ofFloat(leftChild, "scaleY", 1F)

                val animSet = AnimatorSet().apply {
                    duration = DURATION
                    interpolator = AccelerateInterpolator()
                    playTogether(animLeft, animLeftScaleX, animLeftScaleY)
                    startDelay = (abs(index - left) * DELAY).toLong()
                    start()
                }

                left--
            } else if (right < childCount) {
                val rightChild = getChildAt(right)

                val animRight =
                    ObjectAnimator.ofFloat(rightChild, "translationX", 0F)
                val animRightScaleX = ObjectAnimator.ofFloat(rightChild, "scaleX", 1F)
                val animRightScaleY = ObjectAnimator.ofFloat(rightChild, "scaleY", 1F)

                val animSet = AnimatorSet().apply {
                    duration = DURATION
                    interpolator = AccelerateInterpolator()
                    playTogether(animRight, animRightScaleX, animRightScaleY)
                    startDelay = (abs(index - left) * DELAY).toLong()
                    start()
                }

                right++
            } else {
                break
            }
        }

        postDelayed({
            for (o in 0 until childCount) {
                getChildAt(o).isClickable = true
                getChildAt(o).elevation = 0F
            }
        }, totalDelay.toLong())
    }

}