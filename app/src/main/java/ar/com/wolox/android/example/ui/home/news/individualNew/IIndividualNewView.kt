package ar.com.wolox.android.example.ui.home.news.individualNew

import ar.com.wolox.android.example.network.News

interface IIndividualNewView {
    fun startLoading()

    fun completeLoading()

    fun onRefreshNewError()

    fun refreshNewData(new: News)

    fun nothingNewToShow()
}