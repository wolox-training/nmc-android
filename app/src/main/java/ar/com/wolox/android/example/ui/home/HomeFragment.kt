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

        vTabLayoutFragmentHome.setupWithViewPager(vViewPagerFragmentHome)

        mFragmentHomePagerAdapter = SimpleFragmentPagerAdapter(childFragmentManager).apply {
            addFragments(
                    Pair(mPageNews, "News"),
                    Pair(mPageProfile, "Profile")
            )
        }

        vViewPagerFragmentHome.apply {
            adapter = mFragmentHomePagerAdapter
            addOnPageChangeListener(
                    TabLayout.TabLayoutOnPageChangeListener(vTabLayoutFragmentHome)
            )
        }

        onTabInit()
    }

    private fun onTabInit() {
        vTabLayoutFragmentHome.run {
            getTabAt(0)!!.setIcon(R.drawable.ic_news_list_selected_state)
            getTabAt(1)!!.setIcon(R.drawable.ic_profile_selected_state)
        }
    }
}
