package ar.com.wolox.android.example.ui.home.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.Item
import kotlinx.android.synthetic.main.item_onboarding.view.*

class ItemViewAdapter(private val items: ArrayList<Item>) : RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_onboarding, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(itemHolder: ItemViewHolder, position: Int) {
        itemHolder.run {
            description.text = items[position].name
            onItemViewClicked(items[position])
            setItemSelected(items[position].selected)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val description = itemView.vItem
        val selectedIcon = itemView.vSelectButton

        fun setItemSelected(state: Boolean) {
            if (state) {
                selectedIcon.visibility = View.VISIBLE
                description.textSize = TEXT_SIZE
            }
        }

        fun onItemViewClicked(item: Item) {
            itemView.setOnClickListener {
                if (selectedIcon.visibility == View.VISIBLE) {
                    item.selected = false
                    selectedIcon.visibility = View.GONE
                    description.textSize = ORIGINAL_TEXT_SIZE
                } else {
                    item.selected = true
                    selectedIcon.visibility = View.VISIBLE
                    description.textSize = TEXT_SIZE
                }
            }
        }

        companion object {
            const val ORIGINAL_TEXT_SIZE = 16F
            const val TEXT_SIZE = 18F
        }
    }
}
