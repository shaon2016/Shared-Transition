package com.lastblade.circularsharedreavealedexample

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.transition.Explode
import android.view.Window
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startSharedTransition()
    }

    private fun startSharedTransition() {
        first.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val pairs = TransitionHelper.createSafeTransitionParticipants(this, false,
                        Pair<Any, String>(first, "target_image"))
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, *pairs)
                startActivity(intent, options.toBundle())
            } else startActivity(intent)
        }
    }

}
