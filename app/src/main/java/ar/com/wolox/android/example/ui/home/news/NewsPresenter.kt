package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class NewsPresenter @Inject constructor() : BasePresenter<INewsView>() {

    override fun onViewAttached() {}

    fun onAddNewsButtonPressed() = view.goAddNews()

    fun onPullDownRefresh() = view.nothingNewToShow()

    fun loadMoreNews(newsToRefresh: Int): ArrayList<String> {
        val oldNews = ArrayList<String>()
        for (i in 0..newsToRefresh) {
            oldNews.add("Contact: $newsToRefresh")
        }
        return oldNews
    }

    fun loadRecentNews() {
        val recentNews = ArrayList<String>()
        for (i in 0..2) {
            recentNews.add("Contact: " + 2)
        }
        view.addRecentNews(recentNews)
    }
}