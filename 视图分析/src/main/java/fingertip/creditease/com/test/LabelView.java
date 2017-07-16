package fingertip.creditease.com.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.view.View;

import fingertip.creditease.com.tt.R;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/16 下午3:20
 */

public class LabelView extends View {
    private Paint mTextPaint;
    private String mText;
    private int mAscent;

    /**
     * Constructor.  This version is only needed if you will be instantiating
     * the object manually (not from a layout XML file).
     * @param context
     */
    public LabelView(Context context) {
        super(context);
        initLabelView();
    }

    /**
     * Construct object, initializing with any attributes we understand from a
     * layout file. These attributes are defined in
     * SDK/assets/res/any/classes.xml.
     *
     * @see android.view.View#View(android.content.Context, android.util.AttributeSet)
     */
    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLabelView();

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.LabelView);

        CharSequence s = a.getString(R.styleable.LabelView_text);
        if (s != null) {
            setText(s.toString());
        }

        // Retrieve the color(s) to be used for this view and apply them.
        // Note, if you only care about supporting a single color, that you
        // can instead call a.getColor() and pass that to setTextColor().
        setTextColor(a.getColor(R.styleable.LabelView_textColor, 0xFF000000));

        int textSize = a.getDimensionPixelOffset(R.styleable.LabelView_textSize, 0);
        if (textSize > 0) {
            setTextSize(textSize);
        }

        a.recycle();
    }

    private final void initLabelView() {
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        // Must manually scale the desired text size to match screen density
        mTextPaint.setTextSize(16 * getResources().getDisplayMetrics().density);
        mTextPaint.setColor(0xFF000000);
        setPadding(3, 3, 3, 3);
    }

    /**
     * Sets the text to display in this label
     * @param text The text to display. This will be drawn as one line.
     */
    public void setText(String text) {
        mText = text;
        requestLayout();
        invalidate();
    }

    /**
     * Sets the text size for this label
     * @param size Font size
     */
    public void setTextSize(int size) {
        // This text size has been pre-scaled by the getDimensionPixelOffset method
        mTextPaint.setTextSize(size);
        requestLayout();
        invalidate();
    }

    /**
     * Sets the text color for this label.
     * @param color ARGB value for the text
     */
    public void setTextColor(int color) {
        mTextPaint.setColor(color);
        invalidate();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /**
     * @see android.view.View#measure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void layout(@Px int l, @Px int t, @Px int r, @Px int b) {
        super.layout(l, t, r, b);
    }

    /**
     * Render the text
     *
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mText, getPaddingLeft(), getPaddingTop() - mAscent, mTextPaint);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    /**
     * Determines the width of this view
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            result = (int) mTextPaint.measureText(mText) + getPaddingLeft()
                    + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    /**
     * Determines the height of this view
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        mAscent = (int) mTextPaint.ascent();
        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = (int) (-mAscent + mTextPaint.descent()) + getPaddingTop()
                    + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;
    }


}
