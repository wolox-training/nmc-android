package ar.com.wolox.android.example.ui.home.profile

import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.Item
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_onboarding_questions.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class Diet @Inject constructor() : WolmoFragment<DietPresenter>(), DietView {

    private var items = ArrayList<Item>()
    private val itemAdapter = ItemViewAdapter(items)

    override fun layout(): Int = R.layout.fragment_onboarding_questions

    override fun init() {
        EventBus.getDefault().register(this)

        loadItems()

        vRecyclerViewDiet.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }
    }

    fun isAnySelected(): Boolean {
        for (i in 0..items.size) {
            if (items[i].isSelected()) {
                return true
            }
        }
        return false
    }

    /**Dudo que esta función haga las cosas bien*/
    fun getItems(lastItems: ArrayList<Item>) {
        val newItemAdapter = ItemViewAdapter(lastItems)
        vRecyclerViewDiet.adapter = newItemAdapter
    }

    private fun loadItems() {
        for (i in 0..4) {
            items.add(Item("Item $i", false))
        }
    }

    /**Evento que se ejecuta al tocar el botón Continuar en TurnOnCooktop*/
    @Subscribe
    fun onSaveFoodPreferences(event: TurnOnCooktop.SaveEvent) {}

    override fun onDetach() {
        EventBus.getDefault().unregister(this)
        super.onDetach()
    }

    companion object {
        fun newInstance() = Diet()
    }
}

interface DietView