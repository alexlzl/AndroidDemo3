package com.test.task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView= (TextView) findViewById(R.id.task1_info2);
        textView.setText("taskid=="+getTaskId()+"==object=="+this.hashCode());
    }
}
