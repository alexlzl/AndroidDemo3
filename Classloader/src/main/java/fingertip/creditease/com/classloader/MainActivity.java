package fingertip.creditease.com.classloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private String TAG=getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClassLoader classLoader = getClassLoader();
        if (classLoader != null){
            Log.e(TAG, "[onCreate] classLoader "  + " : " + classLoader.toString());
            while (classLoader.getParent()!=null){
                classLoader = classLoader.getParent();
                Log.e(TAG,"[onCreate] classLoader "  + " : " + classLoader.toString());
            }
        }
    }
}
