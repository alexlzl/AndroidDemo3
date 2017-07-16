package fingertip.creditease.com.text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/19 下午11:01
 */

public class RotateTextView1 extends View {
    private Paint paint;

    public RotateTextView1(Context context) {
        super(context);
        init();
    }

    public RotateTextView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init(){
        paint=new Paint();

//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(10);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.GREEN);
        Path path=new Path();
        Path path1 = new Path(); //定义一条路径
//        super.onDraw(canvas);
//        canvas.drawColor(Color.GREEN);
        path.moveTo(0,0);
//        path.lineTo(50,60);
//        path.lineTo(200,80);
        path.lineTo(getWidth(),getHeight());
        path.lineTo(getWidth(),getHeight()-100);
        path.lineTo(100,0);
        canvas.drawPath(path,paint);
        paint.setColor(Color.RED);
        paint.setTextSize(50);

       path1.moveTo(50,0);
//        path1.lineTo(60,90);
        path1.lineTo(getWidth(),getHeight()-50);
        PathMeasure pathMeasure=new PathMeasure(path1,false);
        float pathl=pathMeasure.getLength();
        float w=paint.measureText("10点开抢");
        float o=(pathl-w)/2;
        canvas.drawTextOnPath("10点开抢", path1, o, 10, paint);
//        path.moveTo(0, 0); //移动到 坐标10,10
//        path.lineTo(50, 60);
//        path.lineTo(200,80);
//        path.lineTo(10, 10);

//        canvas.drawPath(path, paint);
    }


//    @Override
//    public Parcelable onSaveInstanceState() {
//        Parcelable superState = super.onSaveInstanceState();
//        SavedState ss = new SavedState(superState);
//        ss.childrenStates = new SparseArray();
////        for (int i = 0; i < getChildCount(); i++) {
////            getChildAt(i).saveHierarchyState(ss.childrenStates);
////        }
//        saveHierarchyState(ss.childrenStates);
//        return ss;
//    }
//
//    @Override
//    public void onRestoreInstanceState(Parcelable state) {
//        SavedState ss = (SavedState) state;
//        super.onRestoreInstanceState(ss.getSuperState());
////        for (int i = 0; i < getChildCount(); i++) {
////            getChildAt(i).restoreHierarchyState(ss.childrenStates);
////        }
//        restoreHierarchyState(ss.childrenStates);
//    }
//
//    @Override
//    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
////        dispatchFreezeSelfOnly(container);
//    }
//
//    @Override
//    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
////        dispatchThawSelfOnly(container);
//    }
//
//    static class SavedState extends BaseSavedState {
//        SparseArray childrenStates;
//
//        SavedState(Parcelable superState) {
//            super(superState);
//        }
//
//        private SavedState(Parcel in, ClassLoader classLoader) {
//            super(in);
//            childrenStates = in.readSparseArray(classLoader);
//        }
//
//        @Override
//        public void writeToParcel(Parcel out, int flags) {
//            super.writeToParcel(out, flags);
//            out.writeSparseArray(childrenStates);
//        }
//
//        public static final ClassLoaderCreator<SavedState> CREATOR
//                = new ClassLoaderCreator<SavedState>() {
//            @Override
//            public SavedState createFromParcel(Parcel source, ClassLoader loader) {
//                return new SavedState(source, loader);
//            }
//
//            @Override
//            public SavedState createFromParcel(Parcel source) {
//                return createFromParcel(null);
//            }
//
//            public SavedState[] newArray(int size) {
//                return new SavedState[size];
//            }
//        };
//    }
}
