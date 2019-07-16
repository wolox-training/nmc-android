package ar.com.wolox.android.example.ui.home.news

import android.annotation.TargetApi
import android.os.Build
import ar.com.wolox.android.example.network.News
import ar.com.wolox.android.example.network.NewsServices
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import java.time.LocalDateTime
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
    fun onLoadOldNews() {
        mRetrofitServices.getService(NewsServices::class.java).getOlderNews().enqueue(
                networkCallback {
                    onResponseSuccessful {
                        if (it.isNullOrEmpty()) {
                            view.nothingNewToShow()
                        } else {
                            view.addOlderNews(it.apply { setReadableCreationTime(this) })
                        }
                    }
                    onResponseFailed { _, _ ->
                        view.onLoadOlderNewsError()
                    }
                }
        )
    }

    fun onLoadRecentNews() {
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
                        view.completeLoading()
                        view.onLoadRecentNewsError()
                    }
                }
        )
    }

    private fun sortByMostRecent(newsList: ArrayList<News>) {
        newsList.apply {
            sortByDescending { it.getCreatedAt() }
            setReadableCreationTime(this)
        }
        view.addRecentNews(newsList)
    }

    private fun setReadableCreationTime(newsList: ArrayList<News>): ArrayList<News> {
        val now = LocalDateTime.now()

        for (i in 0..newsList.lastIndex) {
            var yearsOfDifference = newsList[i].getCreatedAt().year - now.year
            val monthOfTheNewIsNow = newsList[i].getCreatedAt().month == now.month

            if (yearsOfDifference == 0) {
                if (monthOfTheNewIsNow) {
                    val daysOfDifference = now.dayOfMonth - newsList[i].getCreatedAt().dayOfMonth
                    if (daysOfDifference == 0) {
                        val hoursOfDifference = now.hour - newsList[i].getCreatedAt().hour
                        if (hoursOfDifference == 0) {
                            val minutesOfDifference = now.minute - newsList[i].getCreatedAt().minute
                            if (minutesOfDifference == 0) {
                                newsList[i].setReadableTime("Right Now")
                            } else {
                                newsList[i].setReadableTime("$minutesOfDifference min")
                            }
                        } else {
                            newsList[i].setReadableTime("$hoursOfDifference Hours")
                        }
                    } else {
                        newsList[i].setReadableTime("$daysOfDifference Days")
                    }
                } else {
                    val monthsOfDifference = now.month.value - newsList[i].getCreatedAt().month.value
                    newsList[i].setReadableTime("$monthsOfDifference Months")
                }
            } else {
                yearsOfDifference *= -1
                newsList[i].setReadableTime("$yearsOfDifference Years")
            }
        }
        return newsList
    }
}