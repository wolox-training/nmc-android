package ar.com.wolox.android.example.ui.home.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import kotlinx.android.synthetic.main.item_onboarding.view.*

class ItemViewAdapter(private val items: ArrayList<String>) : RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_onboarding, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(itemHolder: ItemViewHolder, position: Int) {
        itemHolder.description.text = items[position]
        itemHolder.onItemViewClicked()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val description = itemView.vItem
        val selectedIcon = itemView.vSelectButton

        fun onItemViewClicked() {
            itemView.setOnClickListener {
                selectedIcon.visibility = VISIBILITY
                description.textSize = TEXT_SIZE
            }
        }

        companion object {
            val VISIBILITY = 1
            val TEXT_SIZE = 18F
        }
    }
}
