package fingertip.creditease.com.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import fingertip.creditease.com.tt.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void test(View view){

        ViewGroup.LayoutParams layoutParams=view.getLayoutParams();
        int w=layoutParams.width;
        Toast.makeText(this,""+w,Toast.LENGTH_LONG).show();
        layoutParams.width=view.getWidth()+100;
        view.setLayoutParams(layoutParams);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    static {

    }

    {

    }
}
