package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.network.News
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import org.joda.time.DateTime
import org.ocpsoft.prettytime.PrettyTime
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val newsAdapterAPI: NewsAdapterAPI) : BasePresenter<INewsView>() {

    override fun onViewAttached() {}

    fun onAddNewsButtonPressed() = view.goAddNews()

    /**
     * This function doesn't compare
     * older news with recent ones,
     * for now.
     */
    fun onLoadOldNews() {
        newsAdapterAPI.loadOlderNews({ onSuccessOlderNews(newsList = it) }, { onEmptyList() }, { onFailureOlderNews() })
    }

    private fun onSuccessOlderNews(newsList: List<News>) {
        view.addOlderNews(
                newsList.run {
                    setReadableCreationTime(this)
                })
    }

    private fun onEmptyList() {
        view.completeLoading()
        view.nothingNewToShow()
    }

    private fun onFailureOlderNews() {
        view.onLoadOlderNewsError()
    }

    fun onLoadRecentNews() {
        view.startLoading()
        newsAdapterAPI.loadRecentNews({ onSuccessRecentNews(newsList = it) }, { onEmptyList() }, { onFailureRecentNews() })
    }

    private fun onSuccessRecentNews(newsList: List<News>) {
        view.completeLoading()
        view.addRecentNews(
                newsList.run {
                    setReadableCreationTime(this)
                })
    }

    private fun onFailureRecentNews() {
        view.completeLoading()
        view.onLoadRecentNewsError()
    }

    private fun setReadableCreationTime(newsList: List<News>): List<News> {
        val prettyTime = PrettyTime()

        for (i in 0..newsList.lastIndex) {
            newsList[i].readableCreationTime = prettyTime.format(DateTime(newsList[i].createdAt).toDate())
        }

        return newsList
    }
}