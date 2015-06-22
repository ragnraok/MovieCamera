package com.ragnarok.moviecamera.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.ragnarok.moviecamera.util.CamLogger

/**
 * Created by ragnarok on 15/6/22.
 */

val STATE_REVEAL_UNKNOW = 0
val STATE_REVEAL_FINISH = 1
val STATE_REVEAL_START = 2

val REVEAL_DURATION = 300L
val INTERPOLARTOR = AccelerateDecelerateInterpolator();

class CircularRevealMaskView : View {
    val TAG: String = "MoviceCamera.CircularRevealMaskView"
    
    interface OnReavealChange {
        fun onStateChange(state: Int)
    }

    constructor(context: Context?) : super(context) {
    }

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {

    }

    var paint: Paint = Paint()
    var radiusAnimator: ObjectAnimator? = null
    var state = STATE_REVEAL_UNKNOW
    var radius = 0
    var startLocX = -1
    var startLocY = -1
    
    init {
        paint.setStyle(Paint.Style.FILL)
        paint.setColor(Color.BLUE)
    }
    
    public fun setColor(color: Int) {
        paint.setColor(color)
    }
    
    public fun startShow(locX: Int, locY: Int) {
        CamLogger.d(TAG, "startShow, locX: $locX, locY: $locY, state: $state")
        if (locX >= 0 && locY >= 0 && state != STATE_REVEAL_START) {
            startLocX = locX
            startLocY = locY
            
            radiusAnimator = ObjectAnimator.ofInt(this, "revealRadius", 0, getWidth() + getHeight()).setDuration(REVEAL_DURATION)
            radiusAnimator?.setInterpolator(INTERPOLARTOR)
            radiusAnimator?.addListener(object: AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    CamLogger.d(TAG, "show reveal finish, state: $state");
                    changeState(STATE_REVEAL_FINISH)
                }
            })
            radiusAnimator?.start()
            changeState(STATE_REVEAL_START)
        }
    }
    
    public fun startHide() {
        CamLogger.d(TAG, "startHide, startLocX: ${startLocX}, startLocY: ${startLocY}, radius: ${radius}, state: ${state}")
        if (startLocX >= 0 && startLocY >= 0 && radius > 0 && state != STATE_REVEAL_FINISH) {
            radiusAnimator = ObjectAnimator.ofInt(this, "revealRadius", getWidth() + getHeight(), 0).setDuration(REVEAL_DURATION)
            radiusAnimator?.setInterpolator(INTERPOLARTOR)
            radiusAnimator?.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    CamLogger.d(TAG, "hide reveal finish, state: ${state}")
                    changeState(STATE_REVEAL_FINISH)
                }
            })
            radiusAnimator?.start()
            changeState(STATE_REVEAL_START)
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        if (state == STATE_REVEAL_FINISH) {
            if (radius > 0) {
                canvas.drawRect(0f, 0f, getWidth().toFloat(), getHeight().toFloat(), paint)
            }
        } else {
            canvas.drawCircle(startLocX.toFloat(), startLocY.toFloat(), radius.toFloat(), paint)
        }
        canvas.restore()
    }

    private fun changeState(newState: Int) {
        if (newState != state) {
            state = newState
        }
    }
    
    public fun setRevealRadius(radius: Int) {
        this.radius = radius
        invalidate()
    }
    
    public fun resetState() {
        state = STATE_REVEAL_UNKNOW
    }
}