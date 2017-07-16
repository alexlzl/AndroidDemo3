package fingertip.creditease.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }


    public void test1(View view){

        startActivity(new Intent(this,MainActivity.class));
    }
    public void test2(View view){

        startActivity(new Intent(this,Main3Activity.class));
    }
}
