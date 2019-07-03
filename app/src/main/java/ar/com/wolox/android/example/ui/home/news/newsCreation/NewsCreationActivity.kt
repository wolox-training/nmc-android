package ar.com.wolox.android.example.ui.home.news.newsCreation

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class NewsCreationActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_news_creation

    override fun init() {
        replaceFragment(R.id.vNewsCreationBaseActivity, NewsCreationFragment())
    }
}
