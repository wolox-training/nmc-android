package ar.com.wolox.android.example.ui.home.profile

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment @Inject constructor() : WolmoFragment<ProfilePresenter>(), IProfileView {
    override fun layout(): Int {
        return R.layout.fragment_profile
    }

    override fun init() {
        vToolbarProfile.title = "Profile"
    }
}
