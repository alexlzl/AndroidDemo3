package fingertip.creditease.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testBitmapShader(View view) {
        startActivity(new Intent().setClass(this, BitmapShaderActivity.class));
    }
    public void testLinearGradient(View view) {
        startActivity(new Intent().setClass(this, LinearGradientActivity.class));
    }
    public void testRadialGradient(View view) {
        startActivity(new Intent().setClass(this, RadialGradientActivity.class));
    }
    public void testSweepGradient(View view) {
        startActivity(new Intent().setClass(this, SweepGradientActivity.class));
    }
    public void testComposeShader(View view) {
        startActivity(new Intent().setClass(this, ComposeShaderActivity.class));
    }
}
