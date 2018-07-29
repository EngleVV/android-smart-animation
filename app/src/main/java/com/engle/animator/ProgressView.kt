package com.engle.animator

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View



/**
 * Created by luwei on 2018/7/29.
 */
class ProgressView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    private var offset = 0
    private var radiusOffset = 0

    fun setRadiusOffset(radiusOffset: Int) {
        this.radiusOffset =radiusOffset
        invalidate()
    }

    fun setOffset(offset: Int) {
        this.offset = offset
        invalidate()
    }

    fun getOffset() : Int {
        return offset
    }

    companion object {
        val paint = Paint()
        var vWidth = 0
        var vHeight = 0

        var centerX = 0
        var centerY = 0
        var radius = 20

        var xferMode = PorterDuffXfermode(PorterDuff.Mode.LIGHTEN)

        var srcMode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
        var dstMode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        var commonMode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)
    }

    override fun onDraw(canvas: Canvas?) {

        vWidth = width
        vHeight = height

        centerX = vWidth/5 + offset
        centerY = vHeight/2

        paint.isAntiAlias = true

        val rightPos = centerX + radiusOffset + radius
        val leftPos = centerX - radiusOffset - radius

        val saved = canvas?.saveLayer(null, null, Canvas.ALL_SAVE_FLAG)

        if(rightPos > vWidth/2F && leftPos < vWidth/2F) {
            drawSrc(canvas)
            //drawDst(canvas)
            //drawCommonPart(canvas)
            Log.d("ABCD", "分开画")
        } else {
            drawAll(canvas)
            Log.d("ABCD", "一起画")
        }

        canvas?.restoreToCount(saved!!)


    }

    private fun drawAll(canvas: Canvas?) {
        paint.xfermode = xferMode

        paint.color = Color.RED
        canvas?.drawCircle(centerX.toFloat(), centerY.toFloat(), radius.toFloat()+radiusOffset, paint)
        paint.color = Color.YELLOW
        canvas?.drawCircle(vWidth - centerX.toFloat(), centerY.toFloat(), radius.toFloat()+radiusOffset, paint)

        paint.xfermode = null
    }

    private fun drawSrc(canvas: Canvas?) {

        paint.color = Color.RED
        canvas?.drawCircle(centerX.toFloat(), centerY.toFloat(), radius.toFloat()+radiusOffset, paint)
        paint.color = Color.YELLOW
        paint.xfermode = srcMode
        canvas?.drawCircle(vWidth - centerX.toFloat(), centerY.toFloat(), radius.toFloat()+radiusOffset, paint)

        paint.xfermode = null
    }

    private fun drawDst(canvas: Canvas?) {
        paint.xfermode = dstMode

        paint.color = Color.RED
        canvas?.drawCircle(centerX.toFloat(), centerY.toFloat(), radius.toFloat()+radiusOffset, paint)
        paint.color = Color.YELLOW
        canvas?.drawCircle(vWidth - centerX.toFloat(), centerY.toFloat(), radius.toFloat()+radiusOffset, paint)

        paint.xfermode = null
    }

    private fun drawCommonPart(canvas: Canvas?) {
        paint.xfermode = commonMode

        paint.color = Color.RED
        canvas?.drawCircle(centerX.toFloat(), centerY.toFloat(), radius.toFloat()+radiusOffset, paint)
        paint.color = Color.YELLOW
        canvas?.drawCircle(vWidth - centerX.toFloat(), centerY.toFloat(), radius.toFloat()+radiusOffset, paint)

        paint.xfermode = null
    }

}