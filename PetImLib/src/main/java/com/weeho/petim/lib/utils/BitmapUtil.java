package com.weeho.petim.lib.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;


public class BitmapUtil {

    /**
     * @describe:bitmap转换成string
     * @author:wangkui
     * @time:2014-11-19上午11:52:55
     */
    public static String bitmapPathToString(String filePath) {

        Bitmap compressBitmap = getCompressBitmap(filePath);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        compressBitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteStream);
        byte[] b = byteStream.toByteArray();

        return Base64.encodeToString(b, Base64.DEFAULT);

    }

    /**
     * @describe:根据路径返回压缩后的bitmap
     * @author:wangkui
     * @time:2014-11-19上午11:52:42
     */
    public static Bitmap getCompressBitmap(String filePath) {
        // 如果我们把inJustDecodeBounds它设为true，那么BitmapFactory.decodeFile(String
        // path, Options
        // opt)并不会真的返回一个Bitmap给你，它仅仅会把它的宽，高取回来给你，这样就不会占用太多的内存
        final Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * @describe:计算图片的缩放值
     * @author:wangkui
     * @time:2014-11-19上午11:52:28
     */
    public static int calculateInSampleSize(Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * @describe:将图片添加到图库
     * @author:wangkui
     * @time:2014-11-19上午11:52:03
     */
    public static void galleryAddPic(Context context, String path) {
        Intent mediaScanIntent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(path);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

    /**
     * 根据路径删除图片
     *
     * @param path
     */
    public static void deleteTempFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 获取保存图片的目录
     *
     * @return
     */
    public static File getAlbumDir() {
        File dir = new File(Environment.getExternalStorageDirectory(),
                getAlbumName());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 获取保存 隐患检查的图片文件夹名称
     *
     * @return
     */
    public static String getAlbumName() {
        return "sfbest";
    }


    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return degree;
        }
        return degree;
    }


    /**
     * 02. * 旋转图片，使图片保持正确的方向。
     * 03. * @param bitmap 原始图片
     * 04. * @param degrees 原始图片的角度
     * 05. * @return Bitmap 旋转后的图片
     * 06.
     */
    public static Bitmap rotateBitmap(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        ;
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }


    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 150) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中

            options -= 10;// 每次都减少10
//            if (options <= 0) {
//                options = 100;
//            }
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }
 
    public static Bitmap getimage(String srcPath) {
        Options newOpts = new Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 1080f;// 这里设置高度为800f
        float ww = 720f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }

    public static Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        Options newOpts = new Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }


    public static Bitmap InputToBitmap(InputStream bis, int wantSize) {
        byte[] bytes = null;
        try {
            bytes = toByteArray(bis);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        int insimple = 0;
        Options opts = new Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(bis, null, opts);

        int size = (opts.outWidth * opts.outHeight);
        if (size > 1024 * wantSize) {
            insimple = (int) Math.sqrt(size / (1024 * wantSize)) + 1;


        }

        opts.inJustDecodeBounds = false;
        opts.inSampleSize = insimple;

        //Bitmap bitmap = BitmapFactory.decodeStream(bis, null, opts);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
        if (bis != null) {
            try {
                bis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        return bitmap;

    }

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

   
    	public static final int ALL = 347120;
        public static final int TOP = 547120;
        public static final int LEFT = 647120;
        public static final int RIGHT = 747120;
        public static final int BOTTOM = 847120;
        
        /**
         * 
         * 指定图片的切边，对图片进行圆角处理
         * @param type 具体参见：{@link BitmapFillet.ALL} , {@link BitmapFillet.TOP} , 
         * 				{@link BitmapFillet.LEFT} , {@link BitmapFillet.RIGHT} , {@link BitmapFillet.BOTTOM}
         * @param bitmap 需要被切圆角的图片
         * @param roundPx 要切的像素大小
         * @return
         *
         */
        public static Bitmap fillet(int type,Bitmap bitmap,int roundPx) {
            try {
            	// 其原理就是：先建立一个与图片大小相同的透明的Bitmap画板
            	// 然后在画板上画出一个想要的形状的区域。
            	// 最后把源图片帖上。
            	final int width = bitmap.getWidth();
            	final int height = bitmap.getHeight();
            	
                Bitmap paintingBoard = Bitmap.createBitmap(width,height, Config.ARGB_8888);
                Canvas canvas = new Canvas(paintingBoard);
                canvas.drawARGB(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);
                
                final Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(Color.BLACK);   
                
                if( TOP == type ){
                	clipTop(canvas,paint,roundPx,width,height);
                }else if( LEFT == type ){
                	 clipLeft(canvas,paint,roundPx,width,height);
                }else if( RIGHT == type ){
                	clipRight(canvas,paint,roundPx,width,height);
                }else if( BOTTOM == type ){
                	clipBottom(canvas,paint,roundPx,width,height);
                }else{
                	clipAll(canvas,paint,roundPx,width,height);
                }
                
                paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN)); 
                //帖子图
                final Rect src = new Rect(0, 0, width, height);
                final Rect dst = src;
                canvas.drawBitmap(bitmap, src, dst, paint);   
                return paintingBoard;
            } catch (Exception exp) {        
                return bitmap;
            }
        }
        
        private static void clipLeft(final Canvas canvas,final Paint paint,int offset,int width,int height){
            final Rect block = new Rect(offset,0,width,height);
            canvas.drawRect(block, paint);
            final RectF rectF = new RectF(0, 0, offset * 2 , height);
            canvas.drawRoundRect(rectF, offset, offset, paint);
        }
        
        private static void clipRight(final Canvas canvas,final Paint paint,int offset,int width,int height){
            final Rect block = new Rect(0, 0, width-offset, height);
            canvas.drawRect(block, paint);
            final RectF rectF = new RectF(width - offset * 2, 0, width , height);
            canvas.drawRoundRect(rectF, offset, offset, paint);
        }
        
        private static void clipTop(final Canvas canvas,final Paint paint,int offset,int width,int height){
            final Rect block = new Rect(0, offset, width, height);
            canvas.drawRect(block, paint);
            final RectF rectF = new RectF(0, 0, width , offset * 2);
            canvas.drawRoundRect(rectF, offset, offset, paint);
        }
        
        private static void clipBottom(final Canvas canvas,final Paint paint,int offset,int width,int height){
            final Rect block = new Rect(0, 0, width, height - offset);
            canvas.drawRect(block, paint);
            final RectF rectF = new RectF(0, height - offset * 2 , width , height);
            canvas.drawRoundRect(rectF, offset, offset, paint);
        }
        
        private static void clipAll(final Canvas canvas,final Paint paint,int offset,int width,int height){
        	final RectF rectF = new RectF(0, 0, width , height);
            canvas.drawRoundRect(rectF, offset, offset, paint);
        }
}
