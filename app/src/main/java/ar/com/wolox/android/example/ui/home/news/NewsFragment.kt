package ar.com.wolox.android.example.ui.home.news

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), INewsView {

    private lateinit var vRecyclerView: RecyclerView
    private lateinit var vViewAdapter: RecyclerView.Adapter<*>
    private lateinit var vViewManager: RecyclerView.LayoutManager

    override fun layout(): Int {
        return R.layout.fragment_news
    }

    override fun init() {
        vViewManager = LinearLayoutManager(context)

        vRecyclerViewNews.apply {
            setHasFixedSize(true)

            layoutManager = vViewManager

            adapter = vViewAdapter
        }
    }
}
