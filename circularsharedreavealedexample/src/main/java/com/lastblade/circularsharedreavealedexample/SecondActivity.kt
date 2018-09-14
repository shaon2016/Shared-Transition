package com.lastblade.circularsharedreavealedexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.animation.AccelerateInterpolator
import android.view.ViewAnimationUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        setupEnterTransition()
    }

    private fun setupEnterTransition() {
        val imageCurvedTransition = TransitionInflater.from(this).inflateTransition(R.transition.arc_motion_transition)
        window.sharedElementEnterTransition = imageCurvedTransition
        imageCurvedTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition?) {
                // Removing listener here is very important because shared element transition is
                // executed again backwards on exit. If we don't remove the listener this code will be triggered again.
                transition?.removeListener(this)
                animateImageReveal()
            }

            override fun onTransitionResume(transition: Transition?) {
            }

            override fun onTransitionPause(transition: Transition?) {
            }

            override fun onTransitionCancel(transition: Transition?) {
            }

            override fun onTransitionStart(transition: Transition?) {
            }
        })
    }

    /**
     * DOC
     * @Link https://developer.android.com/training/animation/reveal-or-hide-view#Reveal
     */
    private fun animateImageReveal() {
        val cx = (second.left + second.right) / 2
        val cy = (second.top + second.bottom) / 2
        val finalRadius = Math.max(second.width, second.height)

        val anim = ViewAnimationUtils.createCircularReveal(second, cx, cy, 0f, finalRadius.toFloat())
        second.visibility = View.VISIBLE
        anim.duration = 1000
        anim.interpolator = AccelerateInterpolator()
        anim.start()
    }
}
