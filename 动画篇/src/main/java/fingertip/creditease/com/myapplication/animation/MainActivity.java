package fingertip.creditease.com.myapplication.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class MainActivity extends AppCompatActivity {

    private LinearInterpolator linearInterpolator;
    private AccelerateDecelerateInterpolator accelerateDecelerateInterpolator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
