package com.openld.seniorui.testtoolbar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import com.openld.seniorui.R

/**
 * 测试Toolbar
 */
class TestToolbarActivity : AppCompatActivity() {
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_tool_bar)

        initWidgets()

        addListeners()
    }

    private fun addListeners() {
        mToolbar.setOnMenuItemClickListener {
            Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
            true
        }

        mToolbar.setNavigationOnClickListener {
            Toast.makeText(this, "点击了Toolbar的导航图标", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initWidgets() {
        mToolbar = findViewById(R.id.toolbar)
        // 就是使用自定义的ToolBar代替Activity Window中的ActionBar
        setSupportActionBar(mToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val searchItem = menu!!.findItem(R.id.toolbar_menu_item1)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.isIconified = true
        searchView.setIconifiedByDefault(true)

        // 设置默认的搜素按钮可见
//        searchView.isSubmitButtonEnabled = true

        // 设置搜索跳转图标
//        val imageView =
//            searchView.findViewById<ImageView>(androidx.constraintlayout.widget.R.id.search_go_btn)
//        imageView.visibility = View.VISIBLE
//        imageView.setImageResource(R.drawable.icon_lemon)

        // TODO: SearchView各种监听器的用法

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)
    }
}