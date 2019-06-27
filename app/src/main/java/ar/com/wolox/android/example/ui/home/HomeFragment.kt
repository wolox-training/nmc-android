package ar.com.wolox.android.example.ui.home

import androidx.core.util.Pair
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.home.news.NewsFragment
import ar.com.wolox.android.example.ui.home.profile.ProfileFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * Home Fragment
 */

class HomeFragment @Inject constructor() : WolmoFragment<BasePresenter<Any>>() {

    @Inject internal lateinit var page1news: NewsFragment
    @Inject internal lateinit var page2profile: ProfileFragment
    private lateinit var fragmentHomePagerAdapter: SimpleFragmentPagerAdapter

    override fun layout(): Int {
        return R.layout.fragment_home
    }

    override fun init() {
        vToolbarFragmentHome.setLogo(R.drawable.wolox_logo)
        vToolbarFragmentHome.setTitle(R.string.example_wolox)

        vTabLayoutFragmentHome.setupWithViewPager(vViewPagerFragmentHome)

        fragmentHomePagerAdapter = SimpleFragmentPagerAdapter(childFragmentManager)
        fragmentHomePagerAdapter.addFragments(
                Pair(page1news, "News"),
                Pair(page2profile, "Profile")
        )
        vViewPagerFragmentHome.adapter = fragmentHomePagerAdapter

        vTabLayoutFragmentHome.getTabAt(0)!!.setIcon(R.drawable.ic_news_list_on)
        vTabLayoutFragmentHome.getTabAt(1)!!.setIcon(R.drawable.ic_profile_off)

        onTabView()
    }

    private fun onTabView() {
        vViewPagerFragmentHome.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(vTabLayoutFragmentHome))
        vTabLayoutFragmentHome.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (vTabLayoutFragmentHome.getTabAt(0)?.isSelected!!) {
                    vTabLayoutFragmentHome.getTabAt(0)!!.setIcon(R.drawable.ic_news_list_on)
                    vTabLayoutFragmentHome.getTabAt(1)!!.setIcon(R.drawable.ic_profile_off)
                } else {
                    vTabLayoutFragmentHome.getTabAt(0)!!.setIcon(R.drawable.ic_news_list_off)
                    vTabLayoutFragmentHome.getTabAt(1)!!.setIcon(R.drawable.ic_profile_on)
                }
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }
        })
    }
}
