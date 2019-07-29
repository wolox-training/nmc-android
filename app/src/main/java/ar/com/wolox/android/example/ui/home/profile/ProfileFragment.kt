package ar.com.wolox.android.example.ui.home.profile

import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment @Inject constructor() : WolmoFragment<ProfilePresenter>(), IProfileView {

    private var items = ArrayList<String>()
    private val viewAdapter = ItemViewAdapter(items)
    private val viewManager = LinearLayoutManager(context)

    override fun layout(): Int {
        return R.layout.fragment_profile
    }

    override fun init() {
        loadItems()

        vItemRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun loadItems() {
        for (i in 0..4) {
            items.add("Item $i")
        }
    }
}
