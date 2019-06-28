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
import kotlinx.android.synthetic.main.fragment_home.view.*
import javax.inject.Inject

/**
 * Home Fragment
 */

class HomeFragment @Inject constructor() : WolmoFragment<BasePresenter<Any>>() {

    @Inject internal lateinit var mPageNews: NewsFragment
    @Inject internal lateinit var mPageProfile: ProfileFragment
    private lateinit var mFragmentHomePagerAdapter: SimpleFragmentPagerAdapter

    override fun layout(): Int {
        return R.layout.fragment_home
    }

    override fun init() {
        vToolbarFragmentHome.apply {
            setLogo(R.drawable.wolox_logo)
            setTitle(R.string.example_wolox)
        }

        vTabLayoutFragmentHome.apply {
            setupWithViewPager(vViewPagerFragmentHome)
            addTab(newTab().setText("News"))
            addTab(newTab().setText("Profile"))
            getTabAt(0)!!.setIcon(R.drawable.ic_news_list_on)
            getTabAt(1)!!.setIcon(R.drawable.ic_profile_off)
        }

        mFragmentHomePagerAdapter = SimpleFragmentPagerAdapter(childFragmentManager)

        mFragmentHomePagerAdapter.apply {
            addFragments(
                    Pair(mPageNews, "News"),
                    Pair(mPageProfile, "Profile")
            )
        }

        vViewPagerFragmentHome.adapter = mFragmentHomePagerAdapter

        onTabInit()
    }

    private fun onTabInit() {
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
