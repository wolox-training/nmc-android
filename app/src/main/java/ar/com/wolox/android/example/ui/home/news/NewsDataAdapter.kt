package ar.com.wolox.android.example.ui.home.news

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.News
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.item_news.view.*

@TargetApi(Build.VERSION_CODES.O)
class NewsDataAdapter(private val newsDataList: ArrayList<News>) : RecyclerView.Adapter<NewsDataAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val newsHolder = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)

        return NewsViewHolder(newsHolder)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(newsHolder: NewsViewHolder, position: Int) {
        newsHolder.run {
            contactName.text = newsDataList[position].getTitle()
            draweeView.setImageURI(Uri.parse(newsDataList[position].getPictureUrl()))
            newsDescription.text = newsDataList[position].getText()
            newsTime.text = newsDataList[position].getCreatedAt().minute.toString() + "m"
        }
    }

    override fun getItemCount(): Int = newsDataList.size

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactName = itemView.news_name
        val newsDescription = itemView.news_description
        val draweeView: SimpleDraweeView = itemView.news_image
        val newsTime = itemView.news_time
    }
}