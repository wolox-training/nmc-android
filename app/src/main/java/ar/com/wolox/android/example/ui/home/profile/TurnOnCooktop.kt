package ar.com.wolox.android.example.ui.home.profile

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class TurnOnCooktop @Inject constructor() : WolmoFragment<TurnOnCooktopPresenter>(), IConnectToCooktop {

    override fun layout(): Int = R.layout.fragment_turn_on_cooktop

    override fun init() {
    }
}

interface IConnectToCooktop