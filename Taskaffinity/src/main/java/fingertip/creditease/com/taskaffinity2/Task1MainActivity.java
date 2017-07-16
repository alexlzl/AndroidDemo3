package fingertip.creditease.com.taskaffinity2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import test.taskaffinity.R;


public class Task1MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view){
        Intent intent=new Intent(this,Task1Main2Activity.class);
        startActivity(intent);
    }
}
