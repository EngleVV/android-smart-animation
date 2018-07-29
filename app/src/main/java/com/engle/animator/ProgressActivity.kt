package com.engle.animator

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.LinearInterpolator
import com.engle.animator.UiHelper.Companion.dp2px

/**
 * Created by luwei on 2018/7/29.
 */

class ProgressActivity : AppCompatActivity() {

    private val progressView: ProgressView by bindView(R.id.progress_view)

    companion object {

        const val duration = 1000L

        fun navigateTo(context: Context) {
            val intent = Intent(context, ProgressActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.progress_activity)

        var objectAnimator = ObjectAnimator.ofInt(progressView, "offset", 0, dp2px(this, 60F), 0)
        var objectAnimator1 = ObjectAnimator.ofInt(progressView, "radiusOffset", 0, 10, 0, 10, 0)
        objectAnimator.duration = duration
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.repeatCount = ObjectAnimator.INFINITE

        objectAnimator1.duration = duration
        objectAnimator1.interpolator = LinearInterpolator()
        objectAnimator1.repeatCount = ObjectAnimator.INFINITE

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(objectAnimator, objectAnimator1)
        animatorSet.start()
    }
}