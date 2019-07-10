package ar.com.wolox.android.example.ui.home.news.individualNew

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import javax.inject.Inject

class IndividualNewActivity @Inject constructor() : WolmoActivity() {

    @Inject internal lateinit var individualNewFragment: IndividualNewFragment

    override fun layout(): Int = R.layout.activity_individual_new

    override fun init() {
        replaceFragment(R.id.activityIndividualNewBaseContent, individualNewFragment)
    }
}
