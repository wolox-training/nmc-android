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

        presenter.onLoadRecentNews()

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
                        presenter.onLoadOldNews()
                    }
                }
            })
        }

        fab_icon.setOnClickListener {
            presenter.onAddNewsButtonPressed()
        }

        vSwipeRefreshLayout.setOnRefreshListener {
            presenter.onLoadRecentNews()
        }
    }

    override fun goAddNews() {
        val intent = Intent(context, NewsCreationActivity::class.java)
        startActivity(intent)
    }

    override fun nothingNewToShow() {
        Toast.makeText(context, R.string.nothing_new_to_show, Toast.LENGTH_SHORT).show()
    }

    override fun addRecentNews(recentNews: List<News>) {
        newsDataList.addAll(0, recentNews)
        viewAdapter.notifyDataSetChanged()
    }

    override fun addOlderNews(olderNews: List<News>) {
        newsDataList.addAll(newsDataList.size, olderNews)
        viewAdapter.notifyDataSetChanged()
    }

    override fun startLoading() {
        vSwipeRefreshLayout.isRefreshing = true
    }

    override fun completeLoading() {
        vSwipeRefreshLayout.isRefreshing = false
    }

    override fun onLoadOlderNewsError() {
        Toast.makeText(context, R.string.fail_loading_older_news, Toast.LENGTH_SHORT).show()
    }

    override fun onLoadRecentNewsError() {
        Toast.makeText(context, R.string.fail_loading_recent_news, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
