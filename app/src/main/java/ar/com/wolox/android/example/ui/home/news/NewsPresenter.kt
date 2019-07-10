package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class NewsPresenter @Inject constructor() : BasePresenter<INewsView>() {

    override fun onViewAttached() {}

    fun onAddNewsButtonPressed() = view.goAddNews()

    /**
     * This function is temporal,
     * and only add as much as
     * newsToRefresh's value.
     */
    fun loadMoreNews(newsToRefresh: Int): ArrayList<String> {
        val oldNews = ArrayList<String>()
        for (i in 0..newsToRefresh) {
            oldNews.add("Contact: $newsToRefresh")
        }
        return oldNews
    }

    /**
     * This function is temporal,
     * and puts only 2 news at the top.
     */
    fun loadRecentNews() {
        val recentNews = ArrayList<String>()
        for (i in 1..2) {
            recentNews.add("Contact: " + 2)
        }
        view.addRecentNews(recentNews)
    }
}