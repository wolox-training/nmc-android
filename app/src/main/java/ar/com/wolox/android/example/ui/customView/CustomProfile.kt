package ar.com.wolox.android.example.ui.customView

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import ar.com.wolox.android.R
import ar.com.wolox.android.example.utils.inflate
import kotlinx.android.synthetic.main.item_custom_profile.view.*

class CustomProfile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(R.layout.item_custom_profile, true)
        /**vTitle.text = ""
        vEpigraph.text = ""
        vContinueButton.text = ""
        avoid.text = ""*/
    }

    fun setTitle(title: String) {
        vTitle.text = title
    }

    fun setVideo() {}

    fun setEpigraph(epigraph: String) {
        vEpigraph.text = epigraph
    }

    fun setContinueButtonText(buttonText: String) {
        vContinueButton.text = buttonText
    }

    fun setAvoidText(avoidText: String) {
        avoid.text = avoidText
    }
}