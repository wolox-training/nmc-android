package ar.com.wolox.android.example.ui.home.profile

import android.app.AlertDialog
import android.widget.Toast
import androidx.core.util.Pair
import androidx.viewpager.widget.ViewPager
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.home.profile.turnOnCooktop.TurnOnCooktop
import ar.com.wolox.android.example.utils.onClickListener
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
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
                }

                override fun onPageScrollStateChanged(state: Int) {
                }
            })
        }

        avoid.onClickListener {
            if (vViewPagerProfile.currentItem != 2) {
                avoidDialog.show()
                vViewPagerProfile.currentItem = 2
            } else {
            }
        }

        /**I really don't know if this is OK*/
        turnOnCooktop.setParentFragment(this)
    }

    /**
     * This function is called from TurnOnCooktop
     * and will Post data from Diet and Allergic fragments.
     * */
    fun saveAllData() {
        Toast.makeText(context, "Continue touched", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = ProfileFragment()
        const val VIEW_PAGER_TABS = 2
    }
}
