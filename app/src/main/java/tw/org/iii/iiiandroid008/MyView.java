package tw.org.iii.iiiandroid008;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
//這段重點與使用者互動
public class MyView extends View {
//系統上自動產生 再去觸發 user 看到長相的邏輯是從這邊來 // 繼承原生的VIEW//畫面呈現
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.BLUE);

        //一開始設定 初始化 按下去後觸發的事情 找出目的跟原因 建構式的構成 
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("brad","onClick");
            }
        });
    }
//畫的部份的機制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint= new Paint();
        paint.setStrokeWidth(10);
        canvas.drawLine(0,0,200,200,paint);  //座標系統
    }
}
