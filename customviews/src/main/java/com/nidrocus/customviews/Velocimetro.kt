package com.nidrocus.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.widget.FrameLayout
import android.view.animation.RotateAnimation

/**
 * Created by cristian on 28/12/2017.
 */
class Velocimetro @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr) {

    private val manecilla: View

    init {
        manecilla = Manecilla(context)
        val paramsBackground = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(VelocimetroBackground(context), paramsBackground)

        viewTreeObserver.addOnGlobalLayoutListener(this::onGlobalLayout)
    }

    private fun onGlobalLayout() {
        viewTreeObserver.removeOnGlobalLayoutListener(this::onGlobalLayout)
        val params = FrameLayout.LayoutParams(width / 4, 4, Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM)
        params.topMargin = -params.height
        params.marginStart = -params.width / 2

        if (manecilla.parent == null) addView(manecilla, params)
    }

    fun updatePosition(angle: Float) {
        val anim = RotateAnimation(manecilla.rotation, angle, Animation.RELATIVE_TO_SELF,
                1f, Animation.RELATIVE_TO_SELF, 0.5f)

        anim.interpolator = BounceInterpolator()
        anim.duration = 1000
        anim.isFillEnabled = true
        anim.fillAfter = true

        manecilla.startAnimation(anim)
    }
}