package com.openld.senior.uisection.testtextinputlayout

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.textfield.TextInputLayout
import com.openld.senior.R
import kotlin.math.max

class TestTextInputLayoutActivity : AppCompatActivity() {
    private lateinit var mUsernameInputLayout: TextInputLayout
    private lateinit var mEdtUsername: AppCompatEditText

    private lateinit var mPasswordInputLayout: TextInputLayout
    private lateinit var mEdtPassword: AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_text_input_layout)

        initWidgets()

        addListeners()
    }

    private fun addListeners() {
        mEdtUsername.addTextChangedListener(MaxLengthTextWatcher(10, mUsernameInputLayout))
        mEdtPassword.addTextChangedListener(MaxLengthTextWatcher(10, mPasswordInputLayout))
    }

    private fun initWidgets() {
        mUsernameInputLayout = findViewById(R.id.tily_username)
        mEdtUsername = findViewById(R.id.edt_username)
        mPasswordInputLayout = findViewById(R.id.tily_password)
        mEdtPassword = findViewById(R.id.edt_password)
    }

    class MaxLengthTextWatcher(
        private val maxLength: Int,
        private val textInputLayout: TextInputLayout
    ) :
        TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (textInputLayout.editText!!.text.toString().length > maxLength) {
                textInputLayout.isErrorEnabled = true
                textInputLayout.error = "长度超过了${maxLength}位"
                textInputLayout.editText!!.setText(s.toString().substring(maxLength))
            } else {
                textInputLayout.isErrorEnabled = false
            }
        }

    }
}