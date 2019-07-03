package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class NewsCreationFragment @Inject constructor() : WolmoFragment<NewsPresenter>() {

    override fun layout(): Int = R.layout.fragment_news_creation

    override fun init() {
    }
}
