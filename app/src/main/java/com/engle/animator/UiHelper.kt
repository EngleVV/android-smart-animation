package com.engle.animator

import android.content.Context

/**
 * Created by luwei on 2018/7/29.
 */

class UiHelper {

    companion object {
        fun dp2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }
    }

}