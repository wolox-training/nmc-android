package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.network.News
import ar.com.wolox.android.example.network.NewsServices
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject

class NewsAdapterAPI @Inject constructor(private val retrofitServices: RetrofitServices) {

    fun loadOlderNews(onSuccessOlderNews: (ArrayList<News>) -> Unit, onEmptyList: () -> Unit, onFailureOlderNews: () -> Unit) {
        retrofitServices.getService(NewsServices::class.java).getOlderNews().enqueue(
                networkCallback {
                    onResponseSuccessful {
                        if (it.isNullOrEmpty()) {
                            onEmptyList()
                        } else {
                            onSuccessOlderNews(it)
                        }
                    }
                    onResponseFailed { _, _ -> onFailureOlderNews() }
                }
        )
    }

    fun loadRecentNews(onSuccessRecentNews: (ArrayList<News>) -> Unit, onEmptyList: () -> Unit, onFailureRecentNews: () -> Unit) {
        retrofitServices.getService(NewsServices::class.java).getAllNews().enqueue(
                networkCallback {
                    onResponseSuccessful {
                        if (it.isNullOrEmpty()) {
                            onEmptyList()
                        } else {
                            onSuccessRecentNews(it)
                        }
                    }
                    onResponseFailed { _, _ -> onFailureRecentNews() }
                }
        )
    }
}