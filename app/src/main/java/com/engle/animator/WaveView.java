package com.engle.animator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by luwei on 2018/7/22.
 */

public class WaveView extends View {

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Path path2 = new Path();
    private Path path = new Path();
    private Paint paint = new Paint();

    private int position = 0;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        invalidate();
    }

    Xfermode mode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        paint.setColor(Color.parseColor("#8B9AAF"));
        paint.setStyle(Paint.Style.FILL);

        path.reset();
        path2.reset();

        float mCenterY = getHeight();
        float mWaveLength = 1000F;
        float yRate = 2F;
        float amplitude = 60F;

        int mWaveCount = (int) Math.round(getWidth() / mWaveLength + 1.5);

        float xPosition = (position*10)%mWaveLength;

        path.moveTo(-mWaveLength + xPosition, mCenterY - position*yRate);
        path2.moveTo(mWaveLength - xPosition + getWidth(), mCenterY - position*yRate);

        for(int i = 0; i <  mWaveCount; i++) {

            float x1 = (-mWaveLength * 3 / 4) + (i * mWaveLength) + xPosition;
            float y1 = mCenterY + amplitude - position*yRate;
            float x2 = (-mWaveLength / 2) + (i * mWaveLength) + xPosition;
            float y2 = mCenterY-position*yRate;
            float x3 = (-mWaveLength / 4) + (i * mWaveLength) + xPosition;
            float y3 = mCenterY - amplitude - position*yRate;
            float x4 = i * mWaveLength + xPosition;
            float y4 = mCenterY-position*yRate;

            path.quadTo(x1, y1, x2, y2);
            path.quadTo(x3, y3, x4, y4);

            path2.quadTo(-x1+getWidth(), y1, -x2+getWidth(), y2);
            path2.quadTo(-x3+getWidth(), y3, -x4+getWidth(), y4);

            //path2.quadTo((-mWaveLength2 * 3 / 4) + (i * mWaveLength2) + position*speedRate - xOffset, mCenterY + 60 - position*yRate, (-mWaveLength2 / 2) + (i * mWaveLength2) + position*speedRate - xOffset, mCenterY-position*yRate);
            //path2.quadTo((-mWaveLength2 / 4) + (i * mWaveLength2) + position*speedRate - xOffset, mCenterY - 60 - position*yRate, i * mWaveLength2 + position*speedRate - xOffset, mCenterY-position*yRate);

        }

        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());

        path2.lineTo(getWidth(), getHeight());
        path2.lineTo(0, getHeight());
        path.close();
        path2.close();

        paint.setXfermode(mode);

        canvas.drawPath(path2, paint);
        paint.setColor(Color.parseColor("#E9ECFA"));

        canvas.drawPath(path, paint);



        canvas.restore();


    }
}
