package fingertip.creditease.com.toolbar;

import android.content.Context;
import android.graphics.Canvas;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/6 上午10:47
 */

public class TestCoordinatorLayout extends CoordinatorLayout {
    public TestCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return super.drawChild(canvas, child, drawingTime);
    }
}
