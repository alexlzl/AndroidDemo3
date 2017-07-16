package fingertip.creditease.com.a50;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class SnackBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        Snackbar.make(view, "真要点我?", Snackbar.LENGTH_LONG)
                .setAction("真的!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SnackBar.this, "你真点我了!", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    public void tablayout(View view) {
        Intent intent = new Intent(this, TabLayoutTest.class);
        startActivity(intent);
    }
    public void coordinator(View view) {
        Intent intent = new Intent(this, CoordinatorLayout1.class);
        startActivity(intent);
    }
    public void coollaps(View view) {
        Intent intent = new Intent(this, CollapsingToolbarLayout1.class);
        startActivity(intent);
    }
}
