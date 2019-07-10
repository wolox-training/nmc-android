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
        setLogo(woloxIcon)
        title = woloxTitle
    }

    fun setCustomTitle(customTitle: String) {
        title = "$woloxTitle - $customTitle"
    }

    companion object {
        private const val woloxIcon = R.drawable.wolox_logo
        private const val woloxTitle = "Wolox"
    }
}