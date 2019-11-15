package tw.org.iii.iiiandroid008;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MyView myView; //宣告
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = findViewById(R.id.myView); //要跟前面XML連動

    }
    //執行動作
    public void clearMyview(View view) {
        myView.clear();
    }

    public void undo(View view) {
        myView.undo();
    }

    public void redo(View view) {
        myView.redo();
    }
}
