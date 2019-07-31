package ar.com.wolox.android.example.ui.home.profile

import androidx.core.util.Pair
import ar.com.wolox.android.R
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
    }

    companion object {
        val VIEW_PAGER_TABS = 2
    }
}
