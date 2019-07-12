package ar.com.wolox.android.example.ui.home.news

import android.content.Context
import android.widget.Toast
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.NewsServices
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject

class NewsPresenter @Inject constructor(retrofitServices: RetrofitServices) : BasePresenter<INewsView>() {

    private val mRetrofitServices = retrofitServices

    override fun onViewAttached() {}

    fun onAddNewsButtonPressed() = view.goAddNews()

    fun onPullDownRefresh() = view.nothingNewToShow()

    /**
     * This function doesn't compare
     * older news with recent ones,
     * for now.
     */
    fun loadMoreNews(newsToRefresh: Int, context: Context) {

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

    fun loadRecentNews(context: Context) {
        view.startLoading()

        mRetrofitServices.getService(NewsServices::class.java).getAllNews().enqueue(
                networkCallback {
                    onResponseSuccessful {
                        view.completeLoading()
                        if (it.isNullOrEmpty()) {
                            view.nothingNewToShow()
                        } else {
                            view.addRecentNews(it)
                        }
                    }
                    onResponseFailed { _, _ ->
                        Toast.makeText(context, context.resources.getString(R.string.fail_loading_recent_news), Toast.LENGTH_SHORT).show()
                        view.completeLoading()
                    }
                }
        )
    }
}