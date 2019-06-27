package ar.com.wolox.android.example.ui.home.news

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsDataAdapter(private val newsDataAdapter: Array<String>) : RecyclerView.Adapter<NewsDataAdapter.NewsViewHolder>() {

    class NewsViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsDataAdapter.NewsViewHolder {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: NewsDataAdapter.NewsViewHolder, position: Int) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}