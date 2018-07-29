package com.engle.animator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

/**
 * Created by luwei on 2018/7/29.
 */

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private val waveBtn: Button by bindView(R.id.wave_btn)
    private val progress1Btn: Button by bindView(R.id.progress1_btn)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        waveBtn.setOnClickListener(this)
        progress1Btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.wave_btn -> WaveActivity.navigateTo(this)
            R.id.progress1_btn -> ProgressActivity.navigateTo(this)
        }
    }
}