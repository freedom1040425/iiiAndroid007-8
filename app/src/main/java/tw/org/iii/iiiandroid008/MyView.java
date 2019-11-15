package tw.org.iii.iiiandroid008;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.LinkedList;

                //建構式
                // 系統上自動產生 再去觸發 user 看到長相的邏輯是從這邊來 // 繼承原生的VIEW//畫面呈現
public class MyView extends View {
    private LinkedList<LinkedList<Point>> lines,recycle; //宣告新增物件
        public MyView(Context context, @Nullable AttributeSet attrs){
            super(context, attrs);
            setBackgroundColor(Color.BLUE);//畫布顏色
            lines = new LinkedList<>(); //線的清單 給他初始直
            recycle = new LinkedList<>(); //資源回收桶
        }

                //一開始設定 初始化 按下去後觸發的事情 找出目的跟原因 建構式的構成
                /*    setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.v("brad","onClick");  //由下面觸發 user 離開畫面才會出現一次
                        }
                    });

                }*/  //這段重點與使用者互動
                //on 開頭是系統觸發 非我們設定
    @Override
                    //從父類別來的  安卓裡面 所有VIEW都可以被摸
                    //抓取使用者
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX(), ey = event.getY();
        Point point = new Point(ex, ey);
                //很多點跟超過第二條線 //如何決定 新線
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            recycle.clear();//新線開始 資源回收桶清空
            LinkedList<Point> line = new LinkedList<>();
            lines.add(line);
        }
        lines.getLast().add(point);
             //line.add(point); //改成上面的，把目前點收集起來 放在最後的那條線身上
             //隨時可以看到 線 java ==>repaint
        invalidate();//觸發安卓方法 系統會幫你弄

                //Log.v("brad", ex+"X"+ey);
                  /*  if (event.getAction()==MotionEvent.ACTION_DOWN){
                        Log.v("brad","1");//下筆
                    }
                    else if (event.getAction()==MotionEvent.ACTION_UP){
                        Log.v("brad","2"); //離筆
                    }
                    else if(event.getAction()==MotionEvent.ACTION_MOVE){
                        Log.v("brad","3"); //畫筆移動過程
                    }*/
                //return super.onTouchEvent(event); //回傳 BOOLEAN //是super 去觸發上面的CLick
        return true;
                //false 只有下去側一次 true 過程中一直測到 //簽名程式 想要一直觸發 那就是true 看需求
    }
       //list 可重複 有順序  簽名點 是有順訊 可以重複
    //物件導向~就是什麼都是物件 敵人是物件 敵人有子彈 >>has a 方法都一樣 屬性可不同(絕對性的不同)!
    //set 不可重複 沒有順序

    //畫線的部份的機制


    @Override //產生畫布
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
                //思路 每次收集到線~要給user看到的畫面
             /*for(Point point:line){
                //兩個點才會有線
                canvas.drawLine(0,0,200,200,paint);  //座標系統
                }*/
        for (LinkedList<Point> line : lines) {
            for (int i = 1; i < line.size(); i++){ //每一點連起來  線要從1個點開始
                Point p0 = line.get(i - 1);
                Point p1 = line.get(i);
                canvas.drawLine(p0.x, p0.y, p1.x, p1.y, paint);
            }


                    /*for(int i=1; i<line.size();i++){ //每一點連起來
                       Point p0 =line.get(i-1);
                       Point p1 = line.get(i);
                       canvas.drawLine(p0.x,p0.y,p1.x,p1.y,paint);
                       }//剩下隨時都想看的到
                     */  //劃一條線
        }
                        //類別是拿來生產物件
                        //ex 汽車類別 汽車裏面有輪胎 買輪胎 才能動 別家公司輪胎(public)要能吻合汽車規格(參數傳遞)
                        // 汽車種類 輪胎買不到 自己蓋了輪胎工廠在自己公司裡面 (這時候就沒有規格 自己講好就好 內部類別private (
                        // 可以與外部類別直接溝通 可以直接呼叫))
                        // 方便存取
    }
            //方法
        public void clear(){
        lines.clear();//線清掉
        invalidate();//畫面清掉

        }

        public void  undo() {
            if (lines.size() > 0) { //有線才丟掉
                recycle.add(lines.removeLast());//最後那一步 並且丟進資源回收桶
                invalidate();//畫面清掉

            }
        }
        //從資源回收桶撈出剛剛丟的那一步
        public void redo(){
            if (recycle.size() > 0) { //有線才能拿
                lines.add(recycle.removeLast());//倒回那一步，是從回收桶拿的
                invalidate();//畫面清掉

            }


        }
        private class Point { //類別

            float x, y;

            Point(float x, float y) {
                this.x = x;
                this.y = y;
            }
        }


}


//可以畫多條線(等做完一條線再來想)
