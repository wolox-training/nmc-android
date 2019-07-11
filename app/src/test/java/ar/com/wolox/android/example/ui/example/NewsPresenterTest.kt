package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.example.ui.home.news.INewsView
import ar.com.wolox.android.example.ui.home.news.NewsPresenter
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

import org.junit.Before
import org.junit.Test

class NewsPresenterTest {
    private lateinit var mNewsPresenter: NewsPresenter
    private lateinit var mINewsView: INewsView

    @Before
    fun createInstances() {
        mINewsView = mock(INewsView::class.java)
        mNewsPresenter = NewsPresenter()
        mNewsPresenter.attachView(mINewsView)
    }

    @Test
    fun showNewsCreationView() {
        mNewsPresenter.onAddNewsButtonPressed()
        verify(mINewsView, times(1)).goAddNews()
    }

    @Test
    fun showNothingNewNotification() {
        mNewsPresenter.onPullDownRefresh()
        verify(mINewsView, times(1)).nothingNewToShow()
    }
}