package com.weeho.petim.lib.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.Editable;
import android.text.Html;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weeho.petim.lib.utils.Base64Util;

import java.io.ByteArrayOutputStream;

/**
 * 
 * Describe:工具类
 * 
 * Date:2015-7-8
 * 
 * Author:wangkui
 */

public class ViewUtil {
    public static final int LISTVIEW_WRAP_HEIGHT = -1;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /*
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    
    //px转sp
    public static int pxTosp(Context context, float pxValue) {
    	DisplayMetrics metrics = context.getResources().getDisplayMetrics();
    	return (int) (pxValue / (metrics.density / 160));
    }

    /**
     * 代码动态设置selector
     */
    @SuppressWarnings("deprecation")
	public static StateListDrawable newSelector(Context context, int

    idNormal, int idPressed, int idFocused, int idUnable) {
        StateListDrawable bg = new StateListDrawable();
        Drawable normal = idNormal == -1 ? null :

        context.getResources().getDrawable(idNormal);
        Drawable pressed = idPressed == -1 ? null :

        context.getResources().getDrawable(idPressed);
        Drawable focused = idFocused == -1 ? null :

        context.getResources().getDrawable(idFocused);
        Drawable unable = idUnable == -1 ? null :

        context.getResources().getDrawable(idUnable);
        bg.addState(new int[] { android.R.attr.state_pressed,

        android.R.attr.state_enabled }, pressed);
        bg.addState(new int[] { android.R.attr.state_enabled,

        android.R.attr.state_focused }, focused);
        bg.addState(new int[] { android.R.attr.state_enabled },

        normal);
        bg.addState(new int[] { android.R.attr.state_focused

        }, focused);
        bg.addState(new int[] {

        android.R.attr.state_window_focused }, unable);
        bg.addState(new int[] {}, normal);
        return bg;
    }

    // 获取圆角bitmap
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, w, h);
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * 获取圆形图片
     */

    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        /**
         * 创建新位图,Config.ARGB_8888代表32位ARGB位图
         */
        Bitmap output = Bitmap.createBitmap(bitmap.getHeight(),
                bitmap.getWidth(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);// 设置是否使用抗锯齿功能
        paint.setFilterBitmap(true);// 过滤掉图像优化，加快显示
        paint.setDither(true);// 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);// 根据给定的Rect对象来构造一个RectF对象

        canvas.drawOval(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rectF, paint);
        return output;
    }

    /**
     * 对TextView设置不同状态时其文字颜色
     * 
     * @param normal
     *            正常状态下颜色值
     * @param pressed
     *            按下状态的颜色值
     * @param focused
     *            获取焦点后的颜色值
     * @param unable
     *            失去焦点后的颜色值
     * @return ColorStateList 颜色状态对象
     */
    public static ColorStateList createColorStateList(int normal, int pressed,
            int focused, int unable) {
        int[] colors = new int[] { pressed, focused, normal, focused, unable,
                normal };
        int[][] states = new int[6][];
        states[0] = new int[] { android.R.attr.state_pressed,
                android.R.attr.state_enabled };
        states[1] = new int[] { android.R.attr.state_enabled,
                android.R.attr.state_focused };
        states[2] = new int[] { android.R.attr.state_enabled };
        states[3] = new int[] { android.R.attr.state_focused };
        states[4] = new int[] { android.R.attr.state_window_focused };
        states[5] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

    /**
     * 获取屏幕长(像素)
     * 
     * @param context
     * @return
     */
    public static int getScreenWith(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * 获取屏幕高(像素)
     * 
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    /**
     * 获取DisplayMetrics对象
     * 
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(dm);
        return dm;
    }

    /**
     * 设置EditText中光标在最后
     * 
     * @param editText
     */
    public static void setEditTextToEnd(EditText editText) {
        if (null == editText) {
            return;
        }
        Editable editable = editText.getText();
        if (null != editable) {
            editText.setSelection(editable.length());
        }
    }

    /**
     * 设置EditText中光标在最前
     * 
     * @param editText
     */
    public static void setEditTextToBefore(EditText editText) {
        if (null == editText) {
            return;
        }
        editText.setSelection(0);
    }

    /**
     * 隐藏键盘
     */
    public static void hideKeyBoard(EditText et, Context context) {
        if (null == et || null == context) {
            return;
        }
        InputMethodManager inputManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(et.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    /**
     * 
     * Describe:主动显示键盘
     * 
     * Date:2015-7-8
     * 
     * Author:wangkui
     */
    public static void showKeyBoard(EditText et, Context context) {
        if (null == et || null == context) {
            return;
        }
        InputMethodManager inputManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(et, InputMethodManager.SHOW_FORCED);
        // inputManager.hideSoftInputFromWindow(et.getWindowToken(),
        // InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    /**
     * 隐藏键盘,不是很好用
     */
    @Deprecated
    public static void hideKeyBoard(Activity activity) {
        if (null != activity) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 
     * Describe:动态设置ListView的高度
     * 
     * Date:2015-7-8
     * 
     * Author:wangkui
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            if (listItem == null) {
                return;
            } else {
                listItem.measure(0, 0);
                // 统计所有子项的总高度
                totalHeight += listItem.getMeasuredHeight();
            }

        }
        /**
         * listView.getDividerHeight()获取子项间分隔符占用的高度
         * params.height最后得到整个ListView完整显示需要的高度
         */
        LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);
    }

    /**
     * 
     * Describe:设置带羊角符的价格
     * 
     * Date:2015-7-8
     * 
     * Author:wangkui
     */
    public static void setActivityPrice(TextView tv, String price) {
        tv.setText(Html.fromHtml("&#165;" + price));
    }

    /**
     * 
     * Describe:设置带羊角符和中划线的价格
     * 
     * Date:2015-7-8
     * 
     * Author:wangkui
     */
    public static void setNormalPrice(TextView tv, String price) {
        tv.setText(Html.fromHtml("&#165;" + price));
        tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    /**
     * 
     * Describe:计算 ListView的高度
     * 
     * Date:2015-7-8
     * 
     * Author:wangkui
     */
    public static void setHeight(ListView listView, ListAdapter adapter) {
        if (null == adapter) {
            return;
        }
        int h = 0;
        final int cnt = adapter.getCount();
        for (int i = 0; i < cnt; i++) {
            View item = adapter.getView(i, null, listView);
            int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(),
                    MeasureSpec.AT_MOST);
            item.measure(desiredWidth, 0);
            h += item.getMeasuredHeight();
        }

        LayoutParams lp = listView.getLayoutParams();
        lp.height = h + (listView.getDividerHeight() * (cnt - 1));
        listView.setLayoutParams(lp);
    }

    /**
     * 
     * Describe:高度为几行的高度，例如设置4则ListView高度为4行高度；如自适配，设置LISTVIEW_WRAP_HEIGHT
     * 
     * Date:2015-7-8
     * 
     * Author:wangkui
     */
    public static void setListViewHeight(ListView listView,
            ListAdapter adapter, int line) {
        if (null == adapter) {
            return;
        }
        int h = 0;
        int cnt = adapter.getCount();
        if (line == LISTVIEW_WRAP_HEIGHT) {
            cnt = adapter.getCount();
        } else {
            cnt = line;
        }

        for (int i = 0; i < cnt; i++) {
            View item = adapter.getView(i, null, listView);
            int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(),
                    MeasureSpec.AT_MOST);
            item.measure(desiredWidth, 0);
            h += item.getMeasuredHeight();
        }

        LayoutParams lp = listView.getLayoutParams();
        lp.height = h + (listView.getDividerHeight() * (cnt - 1));
        listView.setLayoutParams(lp);
    }

    public static void setSettleListHeight(ListView listView,
            ListAdapter adapter) {
        if (null == adapter) {
            return;
        }
        int h = 0;
        final int cnt = adapter.getCount();
        for (int i = 0; i < cnt; i++) {
            View item = adapter.getView(i, null, listView);
            item.measure(0, 0);
            h += item.getMeasuredHeight();
        }

        LayoutParams lp = listView.getLayoutParams();
        lp.height = h + (listView.getDividerHeight() * (cnt - 1)) - 5;
        listView.setLayoutParams(lp);
    }

    /**
     * 
     * Describe:获取listview高度参数
     * 
     * Date:2015-7-8
     * 
     * Author:wangkui
     */
    public static LayoutParams getListViewHeight(ListView listView,
            ListAdapter adapter) {
        if (null == adapter) {
            return null;
        }
        int h = 0;
        final int cnt = adapter.getCount();
        for (int i = 0; i < cnt; i++) {
            View item = adapter.getView(i, null, listView);
            item.measure(0, 0);
            h += item.getMeasuredHeight();
        }

        LayoutParams lp = listView.getLayoutParams();
        lp.height = h + (listView.getDividerHeight() * (cnt - 1));
        return lp;
    }

    /**
     * 
     * Describe:高度为几行的高度，例如设置4则ListView高度为4行高度；如自适配，设置LISTVIEW_WRAP_HEIGHT
     * 
     * Date:2015-7-8
     * 
     * Author:wangkui
     */
    public static int getListViewHeightValue(ListView listView,
            ListAdapter adapter) {
        if (null == listView || null == adapter) {
            return 0;
        }
        int h = 0;
        int cnt = adapter.getCount();

        for (int i = 0; i < cnt; i++) {
            View item = adapter.getView(i, null, listView);
            item.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            int desiredWidth = MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY
                    + listView.getWidth(), MeasureSpec.AT_MOST);
            item.measure(desiredWidth, 0);
            h += item.getMeasuredHeight();
        }

        LayoutParams lp = listView.getLayoutParams();
        lp.height = h + (listView.getDividerHeight() * (cnt - 1));
        return lp.height;
    }

    /**
     * listView是否可滑动(是否内容超出一屏幕,高度精准计算)
     * 
     * @param listView
     * @param adapter
     * @param header
     * @param footer
     * @author Skyin_wd
     * @return
     */
    public static boolean canListViewScroll(ListView listView,
            ListAdapter adapter, View header, View footer) {
        if (null == listView || null == adapter) {
            return false;
        }
        Rect listViewRect = new Rect();
        listView.getGlobalVisibleRect(listViewRect);

        int headerHeight = 0;
        int footerHeight = 0;
        try {
            if (listView.getHeaderViewsCount() > 0 && null != header) {
                header.setLayoutParams(new android.widget.AbsListView.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                header.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
                headerHeight = header.getMeasuredHeight();
            }

            if (listView.getFooterViewsCount() > 0 && null != footer) {
                footer.setLayoutParams(new android.widget.AbsListView.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                footer.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
                footerHeight = footer.getMeasuredHeight();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int listViewTotalHeight = listViewRect.bottom - listViewRect.top;
        int listViewContentHeight = ViewUtil.getListViewHeightValue(listView,
                adapter) + headerHeight + footerHeight;

        if (listViewTotalHeight > listViewContentHeight) {
            return false;
        }
        return true;
    }

    /**
     * 配合BitmapSerial实现bitmap的序列化
     * 
     * 因为Bitmap没有实现序列化，所以不能直接在序列化类(MyBitmap)
     * 
     * 中使用 BytesBitmap用于实现Bitmap和byte[]间的相互转换
     * 
     * 
     */
    public static Bitmap getBitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream baops = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 0, baops);
        return baops.toByteArray();
    }

    /**
     * 分页加载部分 获取宽度
     * 
     * @param
     */
    @SuppressWarnings("deprecation")
    public static int getDisplayWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
//        return display.getWidth();
        Point point = new Point();
        display.getSize(point);
        return point.x;
    }
    /**
     * 分页加载部分 获取高度
     *
     * @param
     */
    @SuppressWarnings("deprecation")
    public static int getDisplayHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point.y;
    }

    /**
     * 
     * @describe:将bitmap转换成string
     * 
     * 
     */
    public static String bitmaptoString(Bitmap bitmap, int bitmapQuality) {

        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, bitmapQuality, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64Util.encode(bytes);
        return string;

    }

    public static Bitmap stringtoBitmap(String string) {

        // 将字符串转换成Bitmap类型

        Bitmap bitmap = null;

        try {

            byte[] bitmapArray;

            bitmapArray = Base64.decode(string, Base64.DEFAULT);

            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,

            bitmapArray.length);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return bitmap;

    }

    /*
     * 获取控件宽
     */
    public static int getWidth(View view) {
        int w = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        int h = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return (view.getMeasuredWidth());
    }

    /*
     * 获取控件高
     */
    public static int getHeight(View view) {
        int w = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        int h = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return (view.getMeasuredHeight());
    }

    /*
     * 设置控件所在的位置X，并且不改变宽高， X为绝对位置，此时Y可能归0
     */
    public static void setLayoutX(View view, int x) {
        MarginLayoutParams margin = new MarginLayoutParams(
                view.getLayoutParams());
        margin.setMargins(x, margin.topMargin, x + margin.width,
                margin.bottomMargin);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                margin);
        view.setLayoutParams(layoutParams);
    }

    /*
     * 设置控件所在的位置Y，并且不改变宽高， Y为绝对位置，此时X可能归0
     */
    public static void setLayoutY(View view, int y) {
        MarginLayoutParams margin = new MarginLayoutParams(
                view.getLayoutParams());
        // margin.setMargins(margin.leftMargin, y, margin.rightMargin, y
        // + margin.height);
        margin.setMargins(0, 0, 0, y);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                margin);
        view.setLayoutParams(layoutParams);
    }

    /*
     * 设置控件所在的位置YY，并且不改变宽高， XY为绝对位置
     */
    public static void setLayout(View view, int x, int y) {
        MarginLayoutParams margin = new MarginLayoutParams(
                view.getLayoutParams());
        margin.setMargins(x, y, x + margin.width, y + margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                margin);
        view.setLayoutParams(layoutParams);
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float roundPx;
            float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
            if (width <= height) {
                roundPx = width / 2;

                left = 0;
                top = 0;
                right = width;
                bottom = width;

                height = width;

                dst_left = 0;
                dst_top = 0;
                dst_right = width;
                dst_bottom = width;
            } else {
                roundPx = height / 2;

                float clip = (width - height) / 2;

                left = clip;
                right = width - clip;
                top = 0;
                bottom = height;
                width = height;

                dst_left = 0;
                dst_top = 0;
                dst_right = height;
                dst_bottom = height;
            }

            Bitmap output = Bitmap
                    .createBitmap(width, height, Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final Paint paint = new Paint();
            final Rect src = new Rect((int) left, (int) top, (int) right,
                    (int) bottom);
            final Rect dst = new Rect((int) dst_left, (int) dst_top,
                    (int) dst_right, (int) dst_bottom);
            final RectF rectF = new RectF(dst);

            paint.setAntiAlias(true);// 设置画笔无锯齿

            canvas.drawARGB(0, 0, 0, 0); // 填充整个Canvas

            // 以下有两种方法画圆,drawRounRect和drawCircle
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);// 画圆角矩形，第一个参数为图形显示区域，第二个参数和第三个参数分别是水平圆角半径和垂直圆角半径。
            // canvas.drawCircle(roundPx, roundPx, roundPx, paint);

            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));// 设置两张图片相交时的模式,参考http://trylovecatch.iteye.com/blog/1189452
            canvas.drawBitmap(bitmap, src, dst, paint); // 以Mode.SRC_IN模式合并bitmap和已经draw了的Circle
            return output;
        } else {
            return null;
        }

    }

    /*
     * 根据bitmap的原比例缩放，这样截取圆形bitmap时 不会变形
     */
    public static Bitmap zoomBitmapProportion(Bitmap bitmap, int newWidth) {
        double width = bitmap.getWidth();
        double height = bitmap.getHeight();
        double scale = height / width;
        return zoomImage(bitmap, newWidth, newWidth * scale);

    }

    /**
     * 
     * Describe:压缩图片尺寸
     * 
     * Date:2015-7-8
     * 
     * Author:wangkui
     */
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
            double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

}
