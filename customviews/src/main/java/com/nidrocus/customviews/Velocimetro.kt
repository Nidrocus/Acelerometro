package com.nidrocus.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.view.animation.RotateAnimation
import java.util.concurrent.TimeUnit

/**
 * Created by cristian on 28/12/2017.
 */
class Velocimetro @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr) {

    private val manecilla: View
    private var maxVelocity: Float = 0f

    init {
        manecilla = Manecilla(context)
        addView(VelocimetroBackground(context), FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        viewTreeObserver.addOnGlobalLayoutListener(this::onGlobalLayout)
    }

    private fun onGlobalLayout() {
        viewTreeObserver.removeOnGlobalLayoutListener(this::onGlobalLayout)
        val params = FrameLayout.LayoutParams(width / 4, 4, Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM)
        params.topMargin = -params.height
        params.marginStart = -params.width / 2

        if (manecilla.parent == null) addView(manecilla, params)

        manecilla.pivotX = manecilla.width.toFloat()
        manecilla.pivotY = manecilla.height / 2f
    }

    private fun updatePosition(angle: Float) {
        manecilla.rotation = angle

//        val anim = RotateAnimation(manecilla.rotation, angle, Animation.RELATIVE_TO_SELF,
//                1f, Animation.RELATIVE_TO_SELF, 0.5f)
//
//        anim.interpolator = LinearInterpolator()
//        //anim.duration = 1000
//        anim.isFillEnabled = true
//        anim.fillAfter = true
//
//        manecilla.startAnimation(anim)
    }

    fun setMaxVelocity(maxVelocity: Float) {
        this.maxVelocity = maxVelocity
    }

    fun setVelocity(velocity: Float) {
        if (velocity > 0 && velocity < maxVelocity) {
            val percentage = velocity * 100 / maxVelocity
            updatePosition(percentage * 180 / 100f)
        }
    }
}