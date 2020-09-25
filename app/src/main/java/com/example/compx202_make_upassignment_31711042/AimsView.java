package com.example.compx202_make_upassignment_31711042;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class AimsView extends View {
    private Paint paint;

    Context context;
    public AimsView(Context context) {
        this(context, null);
    }
    public AimsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public AimsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        this.context = context;
        paint.setColor(context.getResources().getColor(R.color.colorPrimary));
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(w/2,h/2,70, paint);

    }

    int w;
    int h;
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        this.w =w;
//        this.h =h;
//    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        w=MeasureSpec.getSize(widthMeasureSpec);
        h=MeasureSpec.getSize(heightMeasureSpec);

    }
}
