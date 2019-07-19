package ar.com.wolox.android.example.ui.home.news.individualNew

import ar.com.wolox.android.example.network.News
import ar.com.wolox.android.example.network.NewsServices
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject

class IndividualNewAdapterAPI @Inject constructor(private val retrofitServices: RetrofitServices) {

    fun onRefreshIndividualNew(id: String, onSuccessRefresh: (News) -> Unit, onEmptyNew: () -> Unit, onFailureRefresh: () -> Unit) {
        retrofitServices.getService(NewsServices::class.java).getIndividualNew(id).enqueue(
                networkCallback {
                    onResponseSuccessful {
                            if (it != null) {
                                onSuccessRefresh(it[0])
                            } else {
                                onEmptyNew()
                            }
                    }
                    onResponseFailed { _, _ -> onFailureRefresh() }
                }
        )
    }
}
