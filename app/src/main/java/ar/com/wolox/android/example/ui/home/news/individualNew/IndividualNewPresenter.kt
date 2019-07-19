package ar.com.wolox.android.example.ui.home.news.individualNew

import ar.com.wolox.android.example.network.News
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class IndividualNewPresenter @Inject constructor(private val individualNewAdapterApi: IndividualNewAdapterAPI) : BasePresenter<IIndividualNewView>() {

    fun onRefreshIndividualNew(id: String) {
        view.startLoading()
        individualNewAdapterApi.onRefreshIndividualNew(id, { onSuccessRefresh(new = it) }, { onEmptyNew() }, { onFailureRefresh() })
    }

    private fun onSuccessRefresh(new: News) {
        view.completeLoading()
        view.refreshNewData(new)
    }

    private fun onEmptyNew() {
        view.nothingNewToShow()
    }

    private fun onFailureRefresh() {
        view.completeLoading()
        view.onRefreshNewError()
    }
}