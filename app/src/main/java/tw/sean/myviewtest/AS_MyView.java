package tw.sean.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedList;

public class AS_MyView extends View {
    //private LinkedList<HashMap<String,Float>> line;//這是一條線的元素
    private LinkedList<LinkedList<HashMap<String,Float>>> lines;
    private GestureDetector gd;//手勢偵測器宣告
    private Paint paint = new Paint();

    public AS_MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);

        lines = new LinkedList<>();

        gd = new GestureDetector(context, new MyGestureListener());

        setBackgroundColor(Color.YELLOW);
        //super.onTouchEvent(event);註解掉，下方程式就不需要了
//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//      });
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override//右鍵Generate => override
        public boolean onDown(MotionEvent e) {
            Log.v("brad","onDown()");
            return true; //return super.onDown(e);
        }

        @Override //
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {

            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override //滑過去一次性 (慢慢滑要交給 @Override onScroll())
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float vX, float vY) {
            //Log.v("brad","onFling():" + vX + "x" + vY);
            //x的絕對值大於y的絕對值，代表左右移動
            //y的絕對值大於x的絕對值，代表上下移動
            //再巢狀迴圈判斷上、下、左、右
            if (Math.abs(vX) > Math.abs(vY) + 1000){
                if (vX > 0){
                    Log.v("brad","Right");
                }else{
                    Log.v("brad","Left");
                }
            }else if (Math.abs(vY) > Math.abs(vX) + 1000){
                if (vY > 0){
                    Log.v("brad","Down");
                }else{
                    Log.v("brad","Up");
                }
            }

            return super.onFling(e1, e2, vX, vY);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) { //on開頭的是事件觸發，非自行呼叫
        super.onDraw(canvas);

        //Log.v("brad","onDraw()"); //V2

        for (LinkedList<HashMap<String,Float>> line: lines){
            for (int i=1; i<line.size(); i++){//i從1開始因
                HashMap<String,Float> p0 = line.get(i-1);
                HashMap<String,Float> p1 = line.get(i);
                canvas.drawLine(p0.get("x"), p0.get("y"),
                    p1.get("x"),p1.get("y"), paint);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
// V2
//        float ex = event.getX();
//        float ey = event.getY();
//
//        HashMap<String,Float> point = new HashMap<>();
//        point.put("x", ex); point.put("y", ey);
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                Log.d("brad","down: " + ex + "x" + ey);
//                LinkedList<HashMap<String,Float>> line = new LinkedList<>();
//                line.add(point);
//                lines.add(line);
//
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.d("brad","up: " + ex + "x" + ey);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                //Log.d("brad","move: " + ex + "x" + ey);
//                lines.getLast().add(point);
//                break;
//
//        }
//
//
//        if (event.getAction() == MotionEvent.ACTION_DOWN){
//
//        }
//
//        HashMap<String,Float> point = new HashMap<>();
//        point.put("x", ex);
//        point.put("y", ey);
//        line.add(point);
//
//        invalidate();//
//
//        return true;//super.onTouchEvent(event); //return true;用在持續偵測

        return gd.onTouchEvent(event);

    }

    public void clear(){
        lines.clear();
        invalidate();
    }
}
