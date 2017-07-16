package fingertip.creditease.com.testlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ListView) findViewById(R.id.lv);
        ParentAdapter parentAdapter=new ParentAdapter(this);
        listView.setAdapter(parentAdapter);

    }
}
