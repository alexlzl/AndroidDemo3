package fingertip.creditease.com.testglide;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2016/12/20 下午4:40
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
