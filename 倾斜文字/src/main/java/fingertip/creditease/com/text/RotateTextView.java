package fingertip.creditease.com.text;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/14 下午2:11
 */

public class RotateTextView extends TextView {
    private  static  final  String  NAMESPACE ="http://schemas.android.com/apk/res/android";
    private  static  final  String  ATTR_ROTATE = "rotate";
    private  static  final  int  DEFAULTVALUE_DEGREES = 45;
    private  int  degrees ;
    public  RotateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        degrees = attrs.getAttributeIntValue(NAMESPACE, ATTR_ROTATE, DEFAULTVALUE_DEGREES);

    }
    @Override
    protected  void  onDraw(Canvas canvas) {

        canvas.rotate(degrees,getMeasuredWidth()/2,getMeasuredHeight()/2);
        super.onDraw(canvas);
    }
}
