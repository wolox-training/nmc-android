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
import kotlinx.android.synthetic.main.fragment_turn_on_cooktop.*
import javax.inject.Inject

class ProfileFragment @Inject constructor() : WolmoFragment<BasePresenter<Any>>(), IProfileView {

    private var items = ArrayList<Item>()
    val y = 0.3
    private val itemAdapter = ItemViewAdapter(items)

    override fun layout(): Int {
        return R.layout.fragment_profile
    }

    /**
     * Falta pedir por API las foodPreferences.
     */

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

        vOnboardingFoodProfilesContinueButton.onClickListener {
            startOnboardingWithVideos()
        }

        avoid.onClickListener {
            avoidDialog.show()
        }
    }

    fun saveFoodPreferences() {
        Toast.makeText(context, "Continue touched", Toast.LENGTH_SHORT).show()
    }

    private fun startOnboardingWithVideos() {
        requireFragmentManager()
                .beginTransaction()
                .replace(R.id.vNewsCreationBaseActivity, OnboardingVideos.newInstance())
                .commit()
    }

    private fun loadItems() {
        for (i in 0..100) {
            items.add(Item("Item $i", false))
        }
    }

    companion object {
        fun newInstance() = ProfileFragment()
        const val VIEW_PAGER_TABS = 2
    }
}
