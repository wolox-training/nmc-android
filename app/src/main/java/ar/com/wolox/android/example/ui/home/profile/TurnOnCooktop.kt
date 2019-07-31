package ar.com.wolox.android.example.ui.home.profile

import android.net.Uri
import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_turn_on_cooktop.*
import javax.inject.Inject

class TurnOnCooktop @Inject constructor() : WolmoFragment<TurnOnCooktopPresenter>(), IConnectToCooktop {

    override fun layout(): Int = R.layout.fragment_turn_on_cooktop

    override fun init() {
        vVideoPreview.setImageURI(Uri.parse(HARCODED_SHIBA))
    }

    companion object {
        const val HARCODED_SHIBA = "https://webbonbon.com/wp-content/uploads/2018/01/cropped-doge.jpg"
    }
}

interface IConnectToCooktop