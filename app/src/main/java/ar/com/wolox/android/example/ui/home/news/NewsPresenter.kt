package ar.com.wolox.android.example.ui.home.news

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.widget.Toast
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.News
import ar.com.wolox.android.example.network.NewsServices
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject

@TargetApi(Build.VERSION_CODES.O)
class NewsPresenter @Inject constructor(retrofitServices: RetrofitServices) : BasePresenter<INewsView>() {

    private val mRetrofitServices = retrofitServices

    override fun onViewAttached() {}

    fun onAddNewsButtonPressed() = view.goAddNews()

    /**
     * This function doesn't compare
     * older news with recent ones,
     * for now.
     */
    fun onLoadOldNews(newsToRefresh: Int, context: Context) {

        mRetrofitServices.getService(NewsServices::class.java).getAllNews().enqueue(
                networkCallback {
                    onResponseSuccessful {
                        if (it.isNullOrEmpty()) {
                            view.nothingNewToShow()
                        } else {
                            view.addOlderNews(it)
                        }
                    }
                    onResponseFailed { _, _ ->
                        Toast.makeText(context, context.resources.getString(R.string.fail_loading_older_news), Toast.LENGTH_SHORT).show() }
                }
        )
    }

    fun onLoadRecentNews(context: Context) {
        view.startLoading()

        mRetrofitServices.getService(NewsServices::class.java).getAllNews().enqueue(
                networkCallback {
                    onResponseSuccessful {
                        view.completeLoading()
                        if (it.isNullOrEmpty()) {
                            view.nothingNewToShow()
                        } else {
                            sortByMostRecent(it)
                        }
                    }
                    onResponseFailed { _, _ ->
                        Toast.makeText(context, context.resources.getString(R.string.fail_loading_recent_news), Toast.LENGTH_SHORT).show()
                        view.completeLoading()
                    }
                }
        )
    }

    private fun sortByMostRecent(newsList: ArrayList<News>) {
        newsList.sortByDescending { it.getCreatedAt() }
        view.addRecentNews(newsList)
    }
}