package ar.com.wolox.android.example.ui.home.profile.turnOnCooktop

import android.net.Uri
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.home.profile.ProfileFragment
import ar.com.wolox.android.example.utils.onClickListener
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_turn_on_cooktop.*
import javax.inject.Inject

class TurnOnCooktop @Inject constructor() : WolmoFragment<TurnOnCooktopPresenter>(), ITurnOnCooktopView {

    private lateinit var profileFragment: ProfileFragment
    var onContinueClick: (() -> Unit)? = null

    override fun layout(): Int = R.layout.fragment_turn_on_cooktop

    override fun init() {

        vVideoPreview.setImageURI(Uri.parse(HARCODED_SHIBA))

        vButtonContinue.onClickListener {
            onContinueClick?.let { it() }
        }
    }

    fun setParentFragment(fragment: ProfileFragment) {
        profileFragment = fragment
    }

    companion object {
        fun newInstance() = TurnOnCooktop()
        const val HARCODED_SHIBA = "https://webbonbon.com/wp-content/uploads/2018/01/cropped-doge.jpg"
    }
}

interface ITurnOnCooktopView