package com.openld.seniorui.testedttext

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.openld.seniorui.R

/**
 * 这里想要看一个问题就是inputType设置为number或者phone的时候，无法自动换行了
 */
class TestEditTextActivity : AppCompatActivity() {
    private val TAG = "TestEditTextActivity"

    private lateinit var mEdtInput: EditText

    private lateinit var mTxt: AppCompatTextView

    private lateinit var mBtnCommit: AppCompatButton

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_edit_text)

        initWidgets()

        addListeners()
    }

    private fun addListeners() {
        mBtnCommit.setOnClickListener {
            val inputContent = mEdtInput.text.toString()

            // 在用户输入不合法的时候是可以比较精准地给用户提示的
            if (TextUtils.isEmpty(inputContent)) {
                mEdtInput.error = "输入不能为空"
            } else if (inputContent.length > 10) {
                mEdtInput.error = "输入不能超过10位"
            } else {
                Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initWidgets() {
        // 在初始化控件的时候推荐使用findViewById()而不是findViewWithTag()是因为前者更快到和有编译期检查
        mEdtInput = findViewById(R.id.edt_input)

//        mEdtInput.setHorizontallyScrolling(false)
//        mEdtInput.setText("12345678910")
        // 该属性控制是否能选中文本，输入框该属性其实都是true，设置false也没用
//        mEdtInput.setTextIsSelectable(false)

        mTxt = findViewById(R.id.txt)
        // 只有在代码中动态设置该属性，点击获取焦点的时候才能有跑马灯效果
        mTxt.ellipsize = TextUtils.TruncateAt.MARQUEE

        val ss = SpannableString("谁是我们的朋友，谁是我们的敌人，这是革命的首要问题！！！")
        ss.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.green)),
            5,
            7,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        ss.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.red)),
            13,
            15,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        mTxt.text = ss
        mTxt.requestFocus()

        mBtnCommit = findViewById(R.id.btn_commit)

        Log.d(
            TAG,
            "输入组件的tag = ${mEdtInput.tag}}\n文本展示组件的tag = ${mTxt.tag}\n按钮组件的tag = ${mBtnCommit.tag}"
        )
    }
}