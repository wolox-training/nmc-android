package ar.com.wolox.android.example.ui.home.profile

import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.Item
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_onboarding_questions.*
import javax.inject.Inject

class Allergic @Inject constructor() : WolmoFragment<AllergicPresenter>(), IAllergicView {

    private var items = ArrayList<Item>()
    private val itemAdapter = ItemViewAdapter(items)

    override fun layout(): Int = R.layout.fragment_onboarding_questions

    override fun init() {
        vSubtitle.text = "¿Sos alérgico o intolerante a algún alimento?"

        loadItems()

        vRecyclerViewDiet.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }
    }

    private fun loadItems() {
        for (i in 5..10) {
            items.add(Item("Item $i", false))
        }
    }

    companion object {
        fun newInstance() = Allergic()
    }
}

interface IAllergicView