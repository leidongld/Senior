package com.openld.seniorui.testtagcontainer

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.openld.seniorui.R
import com.openld.seniorutils.utils.DisplayUtils
import kotlin.random.Random

class TestTagContainerActivity : AppCompatActivity(), TagContainerView.OnTagClickListener,
    TagContainerView.OnTagLongClickListener {
    private lateinit var mTagContainer: TagContainerView

    private lateinit var mTagList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_tag_container)

        initData()

        initWidgets()

        addListeners()
    }

    private fun initData() {
        mTagList = mutableListOf<String>()
        mTagList.add("我心依狂")
        mTagList.add("人间烟火")
        mTagList.add("泡沫")
        mTagList.add("Fight")
        mTagList.add("归去来")
        mTagList.add("童话")
        mTagList.add("绿色")
        mTagList.add("人世间")
        mTagList.add("如愿")
        mTagList.add("江南Style")
        mTagList.add("溯")
        mTagList.add("漠河舞厅")
        mTagList.add("孤勇者")
        mTagList.add("问明月")
        mTagList.add("奔赴星空")
        mTagList.add("落海")
        mTagList.add("半生雪")
        mTagList.add("水星记")
        mTagList.add("千千万万")
        mTagList.add("四季予你")
        mTagList.add("富士山下")
        mTagList.add("踏山河")
        mTagList.add("房间")
        mTagList.add("半生雪")
        mTagList.add("丑八怪")
        mTagList.add("痴心绝对")
        mTagList.add("独角戏")
        mTagList.add("飘摇")
        mTagList.add("安河桥")
        mTagList.add("星语心愿")
        mTagList.add("走马")
        mTagList.add("方圆几里")
        mTagList.add("月半弯")
        mTagList.add("认真的雪")
        mTagList.add("短发")
        mTagList.add("泡沫")
        mTagList.add("一生所爱")
        mTagList.add("黄昏")
        mTagList.add("千千阙歌")
        mTagList.add("太多")
        mTagList.add("年少有为")
        mTagList.add("再回首")
        mTagList.add("当爱已成往事")
        mTagList.add("我们的纪念")
        mTagList.add("借")
        mTagList.add("麻雀")
        mTagList.add("如果云知道")
        mTagList.add("时光慢旅")
        mTagList.add("当你老了")
        mTagList.add("狼")
        mTagList.add("追光者")
        mTagList.add("离人")
    }

    private fun initWidgets() {
        mTagContainer = findViewById(R.id.tag_container)
        mTagContainer.setTags(mTagList)

        val random = Random(1)

        for (index in 0 until mTagContainer.childCount) {
            val offsetDp = random.nextInt(10)
            val xOffset = DisplayUtils.dp2px(this, offsetDp).toFloat()

            if (offsetDp % 2 == 0) {
                val animator = ObjectAnimator.ofFloat(
                    mTagContainer.getChildAt(index),
                    "translationX",
                    -xOffset,
                    0f,
                    xOffset,
                    0f
                ).apply {
                    duration = 200
                    interpolator = AccelerateDecelerateInterpolator()
                    repeatCount = 4
                    start()
                }
            } else {
                val animator = ObjectAnimator.ofFloat(
                    mTagContainer.getChildAt(index),
                    "translationX",
                    xOffset,
                    0f,
                    -xOffset,
                    0f
                ).apply {
                    duration = 200
                    interpolator = AccelerateDecelerateInterpolator()
                    repeatCount = 4
                    start()
                }
            }
        }
    }

    private fun addListeners() {
        mTagContainer.setOnTagClickListener(this)
        mTagContainer.setOnTagLongClickListener(this)
    }

    override fun onTagClicked(index: Int, tag: TextView, tagStr: String) {
        Toast.makeText(this, "点击了第${index}个tag\n${tagStr}", Toast.LENGTH_SHORT).show()

        val animScaleX = ObjectAnimator.ofFloat(tag, "scaleX", 1.0f, 1.2f, 1.0f)
        animScaleX.repeatCount = 1

        val animScaleY = ObjectAnimator.ofFloat(tag, "scaleY", 1.0f, 1.2f, 1.0f)
        animScaleY.repeatCount = 1

        val set = AnimatorSet().apply {
            duration = 300
            interpolator = AccelerateDecelerateInterpolator()
            playTogether(animScaleX, animScaleY)
            start()
        }

    }

    override fun onTagLongClicked(index: Int, tag: TextView, tagStr: String, isSelected: Boolean) {
        if (isSelected) {
            Toast.makeText(this, "选择了\n${tagStr}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "取消了\n${tagStr}", Toast.LENGTH_SHORT).show()
        }
    }
}