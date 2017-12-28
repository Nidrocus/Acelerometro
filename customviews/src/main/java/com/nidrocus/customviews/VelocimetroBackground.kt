package com.nidrocus.customviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by Cristian on 24/12/2017.
 */
class VelocimetroBackground @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr) {

    // intervalo en grados
    private val decremento = -10
    // posicion en la que cambia de intermedio a rapido
    private val rapida = 120f
    //posicion en la que cambia de lento a intermedio
    private val intermedia = 60f

    private var paint = Paint()

    init {
        paint.strokeWidth = resources.getDimension(R.dimen.ancho_linea_velocimetro)
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