package ar.com.wolox.android.example.ui.home.profile

import android.app.AlertDialog
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.Item
import ar.com.wolox.android.example.utils.onClickListener

import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment @Inject constructor() : WolmoFragment<BasePresenter<Any>>(), IProfileView {

    private var items = ArrayList<Item>()
    private val itemAdapter = ItemViewAdapter(items)

    override fun layout(): Int {
        return R.layout.fragment_profile
    }

    override fun init() {

        loadItems()

        val avoidDialog = AlertDialog.Builder(activity).apply {
            setMessage("En la sección Mi Perfil podrás completar las preferencias")
            setPositiveButton("Aceptar") { _, _ -> }
        }.create()

        vOnboardingRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }

        avoid.onClickListener {
            avoidDialog.show()
        }
    }

    /**
     * This function is called from TurnOnCooktop
     * and will Post data from Diet and Allergic fragments.
     * */
    fun saveAllData() {
        Toast.makeText(context, "Continue touched", Toast.LENGTH_SHORT).show()
    }

    private fun loadItems() {
        for (i in 0..10) {
            items.add(Item("Item $i", false))
        }
    }

    companion object {
        fun newInstance() = ProfileFragment()
        const val VIEW_PAGER_TABS = 2
    }
}
