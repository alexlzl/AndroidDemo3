package fingertip.creditease.com.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view){
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.scale);
        view.startAnimation(animation);

    }
    public void test1(View view){
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.scalse_o);
        view.startAnimation(animation);

    }
}
