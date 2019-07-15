package ar.com.wolox.android.example.ui.home.news

import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.News
import ar.com.wolox.android.example.ui.home.news.newsCreation.NewsCreationActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), INewsView {

    private val newsDataList = ArrayList<News>()
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
                        presenter.onLoadOldNews(NEWS_TO_REFRESH, context)
                    }
                }
            })
        }

        fab_icon.setOnClickListener {
            presenter.onAddNewsButtonPressed()
        }

        vSwipeRefreshLayout.setOnRefreshListener {
            presenter.onLoadRecentNews(context!!)
        }
    }

    /**
     * This function will be removed when we get
     * the current news from the server.
     */
    private fun addContacts() {
        for (i in 0..9) {
            newsDataList.add(News(PREDEF_TITLE, PREDEF_URL_SHIBA, PREDEF_TEXT))
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

    override fun addRecentNews(recentNews: java.util.ArrayList<News>) {
        newsDataList.addAll(0, recentNews)
        viewAdapter.notifyDataSetChanged()
    }

    override fun addOlderNews(olderNews: java.util.ArrayList<News>) {
        newsDataList.addAll(newsDataList.size, olderNews)
        viewAdapter.notifyDataSetChanged()
    }

    override fun startLoading() {
        vSwipeRefreshLayout.isRefreshing = true
    }

    override fun completeLoading() {
        vSwipeRefreshLayout.isRefreshing = false
    }

    companion object {
        private const val NEWS_TO_REFRESH = 2
        private const val PREDEF_TITLE = "Ali Connors"
        private const val PREDEF_URL_SHIBA = "https://pbs.twimg.com/profile_images/378800000351275038/4a1032af7d42f51cf1280203e4d92cdd.jpeg"
        private const val PREDEF_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    }
}
