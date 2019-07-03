package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class NewsCreationFragment : WolmoFragment<NewsCreationPresenter>(), INewsCreationView {

    override fun layout(): Int = R.layout.fragment_news_creation

    override fun init() {
    }
}
