package ar.com.wolox.android.example.ui.home.profile

import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.Item
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_onboarding_questions.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class Allergic @Inject constructor() : WolmoFragment<AllergicPresenter>(), IAllergicView {

    private var items = ArrayList<Item>()
    private val itemAdapter = ItemViewAdapter(items)

    override fun layout(): Int = R.layout.fragment_onboarding_questions

    override fun init() {
        EventBus.getDefault().register(this)

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

    /**Evento que se dispara al clickear el botón Continuar en TurnOnCooktop*/
    @Subscribe
    fun onSaveFoodPreferences(event: TurnOnCooktop.SaveEvent) {}

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    companion object {
        fun newInstance() = Allergic()
    }
}

interface IAllergicView