package ar.com.wolox.android.example.ui.home.news

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
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
    }

    private fun addContacts() {
        for (i in 0..9) {
            mNewsDataList.add("Contact: $i")
        }
    }

    override fun goAddNews() {
        Toast.makeText(context, "Add News", Toast.LENGTH_SHORT).show()
    }
}
