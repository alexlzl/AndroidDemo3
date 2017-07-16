package fingertip.creditease.com.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.tv);
        getDensity();
    }

    private void getDensity() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.d("TAG","Density is "+displayMetrics.density+" densityDpi is "+displayMetrics.densityDpi+" height: "+displayMetrics.heightPixels+
                " width: "+displayMetrics.widthPixels);
        textView.setText(textView.getText().toString()+"======="+displayMetrics.density+" ===densityDpi is== "+displayMetrics.densityDpi+" height: "+displayMetrics.heightPixels+
                " width: "+displayMetrics.widthPixels);
    }
}
