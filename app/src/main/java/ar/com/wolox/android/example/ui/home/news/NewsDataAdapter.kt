package ar.com.wolox.android.example.ui.home.news

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.News
import ar.com.wolox.android.example.ui.home.news.individualNew.IndividualNewActivity
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.item_news.view.*

class NewsDataAdapter(private val newsDataList: ArrayList<News>) : RecyclerView.Adapter<NewsDataAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val newsHolder = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)

        return NewsViewHolder(newsHolder)
    }

    override fun onBindViewHolder(newsHolder: NewsViewHolder, position: Int) {
        newsHolder.run {
            contactName.text = newsDataList[position].title

            /**
             * I put this condition because some news are broken.
             */
            if (!newsDataList[position].picture.isNullOrEmpty()) {
                draweeView.setImageURI(Uri.parse(newsDataList[position].picture)) }

            newsDescription.text = newsDataList[position].text
            newsTime.text = newsDataList[position].readableCreationTime

            onItemClicked(newsDataList[position])
        }
    }

    override fun getItemCount(): Int = newsDataList.size

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactName = itemView.news_name!!
        val newsDescription = itemView.news_description!!
        val draweeView: SimpleDraweeView = itemView.news_image
        val newsTime = itemView.news_time!!
        val likeIcon = itemView.like_icon
        private val itemContext = itemView.context

        fun onItemClicked(new: News) {
            itemView.setOnClickListener(View.OnClickListener {
                IndividualNewActivity.starter(itemContext, new)
            })
        }
    }
}