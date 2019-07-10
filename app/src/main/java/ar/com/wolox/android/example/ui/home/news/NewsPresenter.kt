package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class NewsPresenter @Inject constructor() : BasePresenter<INewsView>() {

    override fun onViewAttached() {}

    fun onAddNewsButtonPressed() = view.goAddNews()

    fun onPullDownRefresh() {
        view.nothingNewToShow()
    }

    fun loadMoreNews(newsToRefresh: Int): Collection<String> {
        val oldNews = ArrayList<String>()
        for (i in 0..newsToRefresh) {
            oldNews.add("Contact: $newsToRefresh")
        }
        return oldNews
    }
}