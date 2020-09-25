package com.example.compx202_make_upassignment_31711042;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class RoundBallView extends View {
    private float x=0;
    private float y=0;
    float frameTime = 2;
    private Paint paint;

    Context context;
    public RoundBallView(Context context) {
        this(context, null);
    }
    public RoundBallView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public RoundBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        this.context = context;
        paint.setColor(context.getResources().getColor(R.color.colorAccent));
        y =  Utils.getScreenHeight(context)/2;
        x =  Utils.getScreenWidth(context)/2;
    }

    public void init() {
        y =  Utils.getScreenHeight(context)/2;
        x =  Utils.getScreenWidth(context)/2;
        invalidate();
    }


    interface onBallStatusListener{
        void onLocation(float x,float y);
    }
    public void refreshUI(float xAcceleration,float yAcceleration,onBallStatusListener onBallStatusListener) {
        x-=xAcceleration*frameTime;
        y+=yAcceleration*frameTime;
        onBallStatusListener.onLocation(x,y);
        if (x > getWidth()) {
            x = getWidth();
        } else if (x < 0) {
            x = 0;
        }
        if (y > getHeight()) {
            y = getHeight();
        } else if (y < 0) {
            y = 0;
        }
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x,y,45, paint);

    }




}
