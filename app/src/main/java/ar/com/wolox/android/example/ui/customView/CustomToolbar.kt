package ar.com.wolox.android.example.ui.customView

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import ar.com.wolox.android.R

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.toolbarStyle
) : Toolbar(context, attrs, defStyleAttr) {

    init {
        setBackgroundColor(Color.TRANSPARENT)
        setLogo(WOLOX_ICON)
        title = WOLOX_TITLE
    }

    fun setCustomTitle(customTitle: String) {
        title = "$WOLOX_TITLE - $customTitle"
    }

    companion object {
        private const val WOLOX_ICON = R.drawable.wolox_logo
        private const val WOLOX_TITLE = "Wolox"
    }
}