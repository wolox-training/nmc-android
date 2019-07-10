package ar.com.wolox.android.example.ui.home.news.individualNew

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class IndividualNewFragment @Inject constructor() : WolmoFragment<IndividualNewPresenter>(), IIndividualNewView {

    override fun layout(): Int = R.layout.fragment_individual_new

    override fun init() {
    }
}
