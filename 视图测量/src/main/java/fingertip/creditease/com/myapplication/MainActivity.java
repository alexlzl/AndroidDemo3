package fingertip.creditease.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "TAG";
    TextView textView;
//    TextView textView2;
    CustomView customView;
    CustomViewGroup customViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView= (CustomView) findViewById(R.id.customv);
        textView= (TextView) findViewById(R.id.tv);
        customViewGroup= (CustomViewGroup) findViewById(R.id.parent);


//        textView2= (TextView) findViewById(R.id.tv_p);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        customView.setTextView(textView);
//        Log.e(TAG, "MainActivity====onWindowFocusChanged"+"textView2=="+textView2.getWidth()+"textView2=="+textView2 .getHeight());
//        Log.e(TAG, "MainActivity====onWindowFocusChanged"+"customViewwidth=="+customView.getWidth()+"customViewheight=="+customView.getHeight());
//        Log.e(TAG, "MainActivity====onWindowFocusChanged"+"textViewwidth=="+textView.getWidth()+"textViewheight=="+textView .getHeight());
    }
}
