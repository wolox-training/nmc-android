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

    private lateinit var mNewsDataList: ArrayList<String>
    private lateinit var mViewAdapter: RecyclerView.Adapter<*>
    private lateinit var mViewManager: RecyclerView.LayoutManager

    override fun layout(): Int {
        return R.layout.fragment_news
    }

    override fun init() {
        Fresco.initialize(context)
        mNewsDataList = ArrayList()

        addContacts()

        mViewManager = LinearLayoutManager(context)
        mViewAdapter = NewsDataAdapter(mNewsDataList)

        vRecyclerViewNews.apply {
            setHasFixedSize(true)
            layoutManager = mViewManager
            adapter = mViewAdapter
        }

        fab_icon.setOnClickListener {
            presenter.addNewsPressed()
        }

        vSwipeRefreshLayout.setOnRefreshListener {
            presenter.onRefresh()
        }
    }

    private fun addContacts() {
        for (i in 0..9) {
            mNewsDataList.add("Contact: $i")
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
}
