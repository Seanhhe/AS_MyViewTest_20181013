package tw.sean.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class AS_MyView extends View {
    public AS_MyView(Context context, AttributeSet attrs) {
        super(context, attrs);


        setBackgroundColor(Color.YELLOW);
        //super.onTouchEvent(event);註解掉，下方程式就不需要了
//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//      });
    }

    @Override
    protected void onDraw(Canvas canvas) { //on開頭的是事件觸發，非自行呼叫
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        canvas.drawLine( 0, 0, 200, 200, paint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX();
        float ey = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("brad","down: " + ex + "x" + ey);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("brad","up: " + ex + "x" + ey);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("brad","move: " + ex + "x" + ey);
                break;

        }

        if (event.getAction() == MotionEvent.ACTION_DOWN){

        }

        return true;//super.onTouchEvent(event); //return true;用在持續偵測

    }
}
