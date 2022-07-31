package com.openld.seniorui.testcards

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.openld.seniorui.R

class TestCardsActivity : AppCompatActivity() {
    private lateinit var mScrollView: CardsHorizontalScrollView

    private lateinit var mCardsContainerLayout: CardsContainerLayout

    private lateinit var mCardList: MutableList<CardBean>

    private var mWidth = 0

    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_cards)

        mWidth = resources.displayMetrics.widthPixels

        mCardList = ArrayList<CardBean>()
        mCardList.add(CardBean(R.drawable.scene1, "阴阳师卡片 0"))
        mCardList.add(CardBean(R.drawable.scene2, "阴阳师卡片 1"))
        mCardList.add(CardBean(R.drawable.scene3, "阴阳师卡片 2"))
        mCardList.add(CardBean(R.drawable.scene4, "阴阳师卡片 3"))
        mCardList.add(CardBean(R.drawable.scene5, "阴阳师卡片 4"))
        mCardList.add(CardBean(R.drawable.scene6, "阴阳师卡片 5"))
        mCardList.add(CardBean(R.drawable.scene7, "阴阳师卡片 6"))
        mCardList.add(CardBean(R.drawable.scene8, "阴阳师卡片 7"))
        mCardList.add(CardBean(R.drawable.scene9, "阴阳师卡片 8"))
        mCardList.add(CardBean(R.drawable.scene10, "阴阳师卡片 9"))
        mCardList.add(CardBean(R.drawable.scene11, "阴阳师卡片 10"))
        mCardList.add(CardBean(R.drawable.scene12, "阴阳师卡片 11"))
        mCardList.add(CardBean(R.drawable.scene13, "阴阳师卡片 12"))
        mCardList.add(CardBean(R.drawable.scene14, "阴阳师卡片 13"))

        mScrollView = findViewById(R.id.scroll_container)

        mScrollView.mOnCardScrollListener = object : OnCardScrollListener {
            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onCardScrolled(index: Int) {
                Toast.makeText(this@TestCardsActivity, "滑到了第${index}个卡片", Toast.LENGTH_SHORT).show()
            }
        }
        mScrollView.post {
            mScrollView.setCards(mCardList)
        }

        mCardsContainerLayout = findViewById(R.id.cards_container_layout)
        mCardsContainerLayout.mOnCardClickListener = object : OnCardClickListener {
            @SuppressLint("ClickableViewAccessibility")
            override fun onCardClicked(position: Int, isFold: Boolean) {
                mScrollView.mIsFold = isFold
                if (isFold) {
                    mScrollView.setOnTouchListener { v, event -> true }
                } else {
                    mScrollView.setOnTouchListener { v, event -> false }
                }
            }
        }

    }
}