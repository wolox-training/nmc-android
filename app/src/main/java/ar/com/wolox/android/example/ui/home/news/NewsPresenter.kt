package ar.com.wolox.android.example.ui.home.news

import android.annotation.TargetApi
import android.os.Build
import ar.com.wolox.android.example.network.News
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import java.time.LocalDateTime
import javax.inject.Inject

@TargetApi(Build.VERSION_CODES.O)
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

    private fun onSuccessOlderNews(newsList: ArrayList<News>) {
        view.addOlderNews(newsList.apply { setReadableCreationTime(this) })
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

    private fun onSuccessRecentNews(newsList: ArrayList<News>) {
        view.completeLoading()
        newsList.apply {
            sortByDescending { it.getCreatedAt() }
            setReadableCreationTime(this)
        }
        view.addRecentNews(newsList)
    }

    private fun onFailureRecentNews() {
        view.completeLoading()
        view.onLoadRecentNewsError()
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