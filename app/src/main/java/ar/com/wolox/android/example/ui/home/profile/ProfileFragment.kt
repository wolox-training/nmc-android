package ar.com.wolox.android.example.ui.home.profile

import android.app.AlertDialog
import androidx.core.util.Pair
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.Item
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment @Inject constructor() : WolmoFragment<BasePresenter<Any>>(), IProfileView {

    @Inject lateinit var dietFragment: Diet
    @Inject lateinit var allergicFragment: Allergic
    @Inject lateinit var turnOnCooktop: TurnOnCooktop
    private lateinit var fragmentProfileAdapter: SimpleFragmentPagerAdapter

    override fun layout(): Int {
        return R.layout.fragment_profile
    }

    override fun init() {

        val avoidDialog = AlertDialog.Builder(activity).apply {
            setMessage("En la sección Mi Perfil podrás completar las preferencias")
            setPositiveButton("Aceptar") { _, _ -> }
        }.create()

        vTabLayoutProfile.setupWithViewPager(vViewPagerProfile)

        fragmentProfileAdapter = SimpleFragmentPagerAdapter(childFragmentManager).apply {
            addFragments(
                    Pair(dietFragment, ""),
                    Pair(allergicFragment, ""),
                    Pair(turnOnCooktop, "")
            )
        }

        vViewPagerProfile.apply {
            adapter = fragmentProfileAdapter
            offscreenPageLimit = VIEW_PAGER_TABS
        }

        avoid.setOnClickListener {
            if (vViewPagerProfile.currentItem != 2) {
                avoidDialog.show()
                vViewPagerProfile.currentItem = 2
            } else {}
        }
    }


    /**Para colocar items seleccionados en Diet, hardcoded*/
    private fun putItemsInDiet() {
        val items = ArrayList<Item>()
        for (i in 0..4) {
            items.add(Item("Item $i", true))
        }
        dietFragment.getItems(items)
    }

    companion object {
        const val VIEW_PAGER_TABS = 2
        const val SHARED_PREFERENCES = "onboarding"
    }
}
