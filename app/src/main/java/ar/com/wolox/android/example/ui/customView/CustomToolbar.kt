package ar.com.wolox.android.example.ui.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import ar.com.wolox.android.R

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {

    val newPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    lateinit var newRect: Rect
    private var squareColor: Int = 0

    fun init(set: AttributeSet?) {
        if (set == null) return

        val typedArray = context.obtainStyledAttributes(set, R.styleable.CustomToolbar)
        squareColor = typedArray.getColor(R.styleable.CustomToolbar_CustomToolbar_color, Color.BLACK)
        newPaint.color = squareColor
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        newRect = Rect()
        newPaint.color = Color.BLUE
        setCustomToolbarDimensions()
        canvas.drawRect(newRect, newPaint)
    }

    private fun setCustomToolbarDimensions() {
        newRect.top = 0
        newRect.bottom = R.dimen.spacing_largest_extra
        newRect.left = 0
        newRect.right = width
    }
}