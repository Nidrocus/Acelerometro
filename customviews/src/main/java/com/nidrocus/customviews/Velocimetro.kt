package com.nidrocus.customviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import android.view.Gravity

/**
 * Created by Cristian on 24/12/2017.
 */
class Velocimetro @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr) {

    // intervalo en grados
    private val decremento = -10
    private var paint = Paint()
    // posicion en la que cambia de intermedio a rapido
    private val rapida = 120f
    //posicion en la que cambia de lento a intermedio
    private val intermedia = 60f

    init {
        setWillNotDraw(false)
        paint.strokeWidth = resources.getDimension(R.dimen.ancho_linea_velocimetro)

        removeAllViews()
        val params = LayoutParams(width / 4, 4, Gravity.CENTER)
        params.topMargin = -4
        val view = Manecilla(getContext())
        addView(view, params)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.translate(width / 2f, height.toFloat() - paint.strokeWidth)

        var posicion = 180f
        while (posicion >= 0) {
            paint.color = if (posicion < rapida) if (posicion < intermedia) Color.GREEN else Color.YELLOW else Color.RED
            canvas.drawLine(width / 4f, paint.strokeWidth, width / 3f, paint.strokeWidth, paint)
            canvas.rotate(decremento.toFloat())
            posicion += decremento
        }
    }
}