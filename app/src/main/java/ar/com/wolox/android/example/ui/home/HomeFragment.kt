package ar.com.wolox.android.example.ui.home

import androidx.core.util.Pair
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.home.news.NewsFragment
import ar.com.wolox.android.example.ui.home.profile.ProfileFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
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
        vTabLayoutFragmentHome.setupWithViewPager(vViewPagerFragmentHome)

        fragmentHomePagerAdapter = SimpleFragmentPagerAdapter(childFragmentManager)
        fragmentHomePagerAdapter.addFragments(
                Pair(page1news, "News"),
                Pair(page2profile, "Profile")
        )

        vViewPagerFragmentHome.adapter = fragmentHomePagerAdapter
    }
}
