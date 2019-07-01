package ar.com.wolox.android.example.ui.home

import ar.com.wolox.android.R

import ar.com.wolox.wolmo.core.activity.WolmoActivity
import javax.inject.Inject

/**
 * Home Activity
 */

class HomeActivity @Inject constructor() : WolmoActivity() {

    @Inject internal lateinit var homeFragment: HomeFragment

    override fun layout(): Int {
        return R.layout.activity_home
    }

    override fun init() {
        replaceFragment(R.id.activityHomeBaseContent, homeFragment)
    }
}