package ar.com.wolox.android.example.ui.home.profile

import android.net.Uri
import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_turn_on_cooktop.*
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class TurnOnCooktop @Inject constructor() : WolmoFragment<TurnOnCooktopPresenter>(), ITurnOnCooktopView {

    override fun layout(): Int = R.layout.fragment_turn_on_cooktop

    override fun init() {
        vVideoPreview.setImageURI(Uri.parse(HARCODED_SHIBA))

        vButtonContinue.setOnClickListener {
            EventBus.getDefault().post(SaveEvent(true))
        }
    }

    companion object {
        fun newInstance() = TurnOnCooktop

        const val HARCODED_SHIBA = "https://webbonbon.com/wp-content/uploads/2018/01/cropped-doge.jpg"
    }

    class SaveEvent(var save: Boolean)
}

interface ITurnOnCooktopView