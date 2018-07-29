package com.engle.animator

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.LinearInterpolator

class WaveActivity : AppCompatActivity() {

    private val waveView: WaveView by lazy {
        this.findViewById(R.id.test_wave_view) as WaveView
    }

    companion object {
        fun navigateTo(context: Context) {
            val intent = Intent(context, WaveActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wave_activity)


        var objectAnimator = ObjectAnimator.ofInt(waveView, "position", 0, 500)
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.duration = 5*1000L
        objectAnimator.repeatCount = ObjectAnimator.INFINITE
        objectAnimator.start()


    }
}
