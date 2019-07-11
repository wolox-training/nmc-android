package ar.com.wolox.android.example.ui.home.news

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.item_news.view.*
import kotlin.collections.ArrayList

class NewsDataAdapter(private val newsDataList: ArrayList<String>) : RecyclerView.Adapter<NewsDataAdapter.NewsViewHolder>() {

    private val uri = Uri.parse(IMAGE_URL)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val newsHolder = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)

        return NewsViewHolder(newsHolder)
    }

    override fun onBindViewHolder(newsHolder: NewsViewHolder, position: Int) {
        newsHolder.run {
            contactName.text = newsDataList[position]
            draweeView.setImageURI(uri)
        }
    }

    override fun getItemCount() = newsDataList.size

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactName = itemView.news_name
        val draweeView: SimpleDraweeView = itemView.news_image
    }

    companion object {
        private const val IMAGE_URL = "https://www.decentfashion.in/wp-content/uploads/2018/02/funnuioy-images-32-300x225.jpg"
    }
}