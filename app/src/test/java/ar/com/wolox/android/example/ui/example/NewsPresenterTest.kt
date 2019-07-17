package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.example.ui.home.news.INewsView
import ar.com.wolox.android.example.ui.home.news.NewsAdapterAPI
import ar.com.wolox.android.example.ui.home.news.NewsPresenter
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

import org.junit.Before
import org.junit.Test

class NewsPresenterTest {
    private lateinit var newsPresenter: NewsPresenter
    private lateinit var iNewsView: INewsView
    private lateinit var newsAdapterAPI: NewsAdapterAPI

    @Before
    fun createInstances() {
        iNewsView = mock(INewsView::class.java)
        newsAdapterAPI = mock(NewsAdapterAPI::class.java)
        newsPresenter = NewsPresenter(newsAdapterAPI)
        newsPresenter.attachView(iNewsView)
    }

    @Test
    fun showNewsCreationView() {
        newsPresenter.onAddNewsButtonPressed()
        verify(iNewsView, times(1)).goAddNews()
    }
}