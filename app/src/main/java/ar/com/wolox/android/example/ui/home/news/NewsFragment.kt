package ar.com.wolox.android.example.ui.home.news

import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.home.news.newsCreation.NewsCreationActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), INewsView {

    private val newsDataList = ArrayList<String>()
    private val viewAdapter = NewsDataAdapter(newsDataList)
    private val viewManager = LinearLayoutManager(context)

    override fun layout(): Int {
        return R.layout.fragment_news
    }

    override fun init() {
        Fresco.initialize(context)

        addContacts()

        vRecyclerViewNews.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val totalItems = layoutManager?.itemCount
                    val lastItem = viewManager.findLastVisibleItemPosition()

                    if (lastItem + 1 == totalItems) {
                        newsDataList.addAll(lastItem, presenter.loadMoreNews(NEWS_TO_REFRESH))
                    }
                    viewAdapter.notifyDataSetChanged()
                }
            })
        }

        fab_icon.setOnClickListener {
            presenter.onAddNewsButtonPressed()
        }

        vSwipeRefreshLayout.setOnRefreshListener {
            presenter.loadRecentNews()
        }
    }

    /**
     * This function will be removed when we get
     * the current news from the server.
     */
    private fun addContacts() {
        for (i in 0..9) {
            newsDataList.add("Contact: $i")
        }
    }

    override fun goAddNews() {
        val intent = Intent(context, NewsCreationActivity::class.java)
        startActivity(intent)
    }

    override fun nothingNewToShow() {
        vSwipeRefreshLayout.isRefreshing = false
        Toast.makeText(context, R.string.nothing_new_to_show, Toast.LENGTH_SHORT).show()
    }

    override fun addRecentNews(recentNews: java.util.ArrayList<String>) {
        vSwipeRefreshLayout.isRefreshing = false

        if (recentNews.isEmpty()) {
            nothingNewToShow()
        } else {
            newsDataList.addAll(0, recentNews)
            viewAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        private const val NEWS_TO_REFRESH = 5
    }
}
