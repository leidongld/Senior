package com.openld.seniorui.testanimation

import android.animation.*
import android.annotation.SuppressLint
import android.graphics.PointF
import android.os.Bundle
import android.view.View
import android.view.animation.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.openld.seniorui.R

class TestAnimationActivity : AppCompatActivity() {
    // 火龙果
    private lateinit var mImgPataya: AppCompatImageView
    // 葡萄
    private lateinit var mImgGrape: AppCompatImageView
    // 橘子
    private lateinit var mImgOrange: AppCompatImageView
    // 桃子
    private lateinit var mImgPeach: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_animation)

        initWidgets()

        addListeners()
    }

    private fun initWidgets() {
        mImgPataya = findViewById(R.id.img_pitaya)
        mImgGrape = findViewById(R.id.img_grape)
        mImgOrange = findViewById(R.id.img_orange)
        mImgPeach = findViewById(R.id.img_peach)
    }

    private fun addListeners() {
        mImgPataya.setOnClickListener {
            onClickPitaya(it)
        }

        mImgGrape.setOnClickListener {
            onClickGrape(it)
        }

        mImgOrange.setOnClickListener {
            onClickOrange(it)
        }

        mImgPeach.setOnClickListener {
            onClickPeach(it)
        }
    }

    /**
     * 点击桃子播放弹跳动画
     */
    private fun onClickPeach(view: View?) {
        Toast.makeText(this, "执行弹跳动画", Toast.LENGTH_SHORT).show()

        val anim1 = ObjectAnimator.ofFloat(view, "translationY", 0F, 400F)
        anim1.interpolator = BounceInterpolator()

        val anim2 = ObjectAnimator.ofFloat(view, "translationX", 0F, 700F)
        anim2.interpolator = LinearInterpolator()

        val anim3 = ObjectAnimator.ofFloat(view, "rotation", 0F, 360F)
        anim3.interpolator = OvershootInterpolator()
        anim3.duration = 700

        val animSet = AnimatorSet()
        animSet.duration = 2000
        animSet.playTogether(anim1, anim2, anim3)
        animSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                // 要回到起始位置就这么设置，否则去掉下面两行
                view!!.translationX = 0F
                view.translationY = 0F
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }
        })
        animSet.start()
    }

    /**
     * 点击橙子
     * 做一个平抛运动的动画
     * 使用估值器实现以下
     */
    @SuppressLint("Recycle")
    private fun onClickOrange(view: View?) {
        Toast.makeText(this, "执行平抛运动", Toast.LENGTH_SHORT).show()

        val initX = view!!.x
        val initY = view.y

        // 平抛运动动画
        val anim1 = ValueAnimator().apply {
            // 持续时间是
            duration = 5_000
            setObjectValues(PointF())
            // 估值器
            setEvaluator { fraction, startValue, endValue ->
                PointF().apply {
                    // 当前的执行时间，fraction百分比乘持续总时间，单位s
                    val time = fraction * duration / 1000F
                    x = initX + 200F * time // 初始速度200m/s
//                    y = initY + 0.5F * 10F * time * time;
                    y = initY + 0.5F * 100F * time * time;// 这里重力加速度改大了，为了效果
                }
            }
            addUpdateListener {
                // 得到此时间点的坐标就搞定了
                val pointF: PointF = it.animatedValue as PointF
                view.x = pointF.x
                view.y = pointF.y
            }
//            interpolator = AccelerateDecelerateInterpolator()
            start()
        }

        // 自旋动画
        val anim2 = ObjectAnimator.ofFloat(view, "rotation", 0F, 360F)
        anim2.duration = 2000
        anim2.interpolator = LinearInterpolator()
        anim2.repeatCount = 2

        val animSet = AnimatorSet()
        animSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                // 要回到起始位置就这么设置，否则去掉下面两行
                view.translationX = 0F
                view.translationY = 0F
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })
        animSet.playTogether(anim1, anim2)
        animSet.start()
    }

    /**
     * 点击了葡萄
     * 使用AnimatorSet播放组合动画
     */
    private fun onClickGrape(view: View?) {
        Toast.makeText(this, "执行摇摆动画", Toast.LENGTH_SHORT).show()

        val anim1 = ObjectAnimator.ofFloat(view, "rotationY", 0F, 30F, -30F, 0F)
        anim1.repeatCount = 2

        val anim2 = ObjectAnimator.ofFloat(view, "rotation", 0F, 30F, -30F, 0F)
        anim2.repeatCount = 2

        val animSet = AnimatorSet()
        animSet.duration = 300
        animSet.interpolator = DecelerateInterpolator()
        animSet.playTogether(anim1, anim2)
        animSet.start()
    }

    /**
     * 点击了火龙果
     * 使用 PropertyValuesHolder 的方式进行组合动画的播放
     */
    private fun onClickPitaya(view: View?) {
        Toast.makeText(this, "执行缩放动画", Toast.LENGTH_SHORT).show()

        val holder1: PropertyValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 1.0F, 0.5F, 1.0F)
        val holder2: PropertyValuesHolder = PropertyValuesHolder.ofFloat("scaleY", 1.0F, 0.7F, 1.0F)
        val holder3: PropertyValuesHolder = PropertyValuesHolder.ofFloat("alpha", 1.0F, 0.5F, 1.0F)

        ObjectAnimator.ofPropertyValuesHolder(view, holder1, holder2, holder3).apply {
            duration = 600
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = 2//加上原来的一次总共放了三次
            addUpdateListener {
                // 可以在这里处理动画过程中的一些事情
            }
            start()
        }
    }
}