package fingertip.creditease.com.testintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testIntent(View view ){
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        Intent sendIntent = new Intent(Intent.ACTION_MAIN);

// Always use string resources for UI text.
// This says something like "Share this photo with"
        String title = getResources().getString(R.string.test_intent);
// Create intent to show the chooser dialog
        Intent chooser = Intent.createChooser(sendIntent, title);

// Verify the original intent will resolve to at least one activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            Toast.makeText(this,"have",Toast.LENGTH_LONG).show();
            startActivity(chooser);
        }else {
            Toast.makeText(this,"没有目标",Toast.LENGTH_LONG).show();
        }
    }
}
