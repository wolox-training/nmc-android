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

    private val woloxIcon = R.drawable.wolox_logo

    init {
        setBackgroundColor(Color.TRANSPARENT)
        setLogo(woloxIcon)
        setTitle(R.string.example_wolox)
    }

    fun setHomeTitle() {
        setTitle(R.string.toolbar_wolox_home)
    }

    fun setNewsCreationTitle() {
        setTitle(R.string.toolbar_wolox_news_creation)
    }
}