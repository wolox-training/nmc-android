package ar.com.wolox.android.example.ui.home.profile

import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_allergic.*
import javax.inject.Inject

class Allergic @Inject constructor() : WolmoFragment<AllergicPresenter>(), AllergicView {

    private var items = ArrayList<String>()
    private val itemAdapter = ItemViewAdapter(items)

    override fun layout(): Int = R.layout.fragment_allergic

    override fun init() {
        loadItems()

        vRecyclerViewAllergic.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }
    }

    private fun loadItems() {
        items.clear()
        for (i in 0..5) {
            items.add("Item $i")
        }
    }
}

interface AllergicView