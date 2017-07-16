package fingertip.creditease.com.taskaffinity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fingertip.creditease.com.R;

public class Task2MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view){
        Intent intent=new Intent(this,Task2Main2Activity.class);
        startActivity(intent);
    }
}
