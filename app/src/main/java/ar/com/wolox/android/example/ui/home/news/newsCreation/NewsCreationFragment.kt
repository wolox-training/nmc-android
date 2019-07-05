package ar.com.wolox.android.example.ui.home.news.newsCreation

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_news_creation.*

class NewsCreationFragment : WolmoFragment<NewsCreationPresenter>(), INewsCreationView {

    override fun layout(): Int = R.layout.fragment_news_creation

    override fun init() {
        vToolbarNewsCreation.setNewsCreationTitle()
    }
}
