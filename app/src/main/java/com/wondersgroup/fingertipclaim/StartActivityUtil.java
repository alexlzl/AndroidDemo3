//package com.wondersgroup.fingertipclaim;
//
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//
///**
// * @author 黄赛赛
// * @version V1.0
// * @Title: ${file_name}
// * @Package ${package_name}
// * @Description: ${todo}()
// * @date 2016/4/12 11:37
// */
//public class StartActivityUtil {
//    public static void startActivity(Class<?> cls, Activity activity) {
//        Intent intent = new Intent(activity, cls);
//        activity.startActivity(intent);
//        // new ActivityAnimator().fadeAnimation(this);
//    }
//
//    public static void startActivity(Class<?> cls, Fragment fragment) {
//        Intent intent = new Intent(fragment.getActivity(), cls);
//        fragment.startActivity(intent);
//        // new ActivityAnimator().fadeAnimation(this);
//    }
//    public static void startActivity(Class<?> cls, Context  mContext) {
//        Intent intent = new Intent(mContext, cls);
//        mContext.startActivity(intent);
//        // new ActivityAnimator().fadeAnimation(this);
//    }
//
//    public static void startActivity(Class<?> cls, Bundle bundle, Activity activity) {
//        Intent intent = new Intent(activity, cls);
//        if (bundle != null) {
//            intent.putExtras(bundle);
//        }
//        activity.startActivity(intent);
//        // new ActivityAnimator().fadeAnimation(this);
//    }
//
//    public static void startActivity(Class<?> cls, Bundle bundle,Fragment fragment) {
//        Intent intent = new Intent(fragment.getActivity(), cls);
//        if (bundle != null) {
//            intent.putExtras(bundle);
//        }
//        fragment.startActivity(intent);
//        // new ActivityAnimator().fadeAnimation(this);
//    }
//
//
//    public static void startActivity(Class<?> cls, Bundle bundle,  Context  mContext) {
//        Intent intent = new Intent(mContext, cls);
//        if (bundle != null) {
//            intent.putExtras(bundle);
//        }
//        mContext.startActivity(intent);
//        // new ActivityAnimator().fadeAnimation(this);
//    }
//    public static void startActivityForResult(Class<?> cls, int requestCode, Activity activity) {
//        Intent intent = new Intent(activity, cls);
//        activity.startActivityForResult(intent, requestCode);
//        // new ActivityAnimator().fadeAnimation(this);
//    }
//
//    public static void startActivityForResult(Class<?> cls, int requestCode, Fragment fragment) {
//        Intent intent = new Intent(fragment.getActivity(), cls);
//        fragment.startActivityForResult(intent, requestCode);
//        // new ActivityAnimator().fadeAnimation(this);
//    }
//
//    public static void startActivityForResult(Class<?> cls, int requestCode,
//                                         Bundle bundle, Activity activity) {
//        Intent intent = new Intent(activity, cls);
//        if (bundle != null) {
//            intent.putExtras(bundle);
//        }
//        activity.startActivityForResult(intent, requestCode);
//        // new ActivityAnimator().fadeAnimation(this);
//    }
//
//    public static void startActivityForResult(Class<?> cls, int requestCode,
//                                         Bundle bundle, Fragment fragment) {
//        Intent intent = new Intent(fragment.getActivity(), cls);
//        if (bundle != null) {
//            intent.putExtras(bundle);
//        }
//        fragment.startActivityForResult(intent, requestCode);
//        // new ActivityAnimator().fadeAnimation(this);
//    }
//
//    public static void showBrower(Activity activity, String url) {
//        Uri uri = Uri.parse(url);
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        activity.startActivity(intent);
//    }
//
//    public static void showBrower(Context activity, String url) {
//        Uri uri = Uri.parse(url);
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        activity.startActivity(intent);
//    }
//
//    public static void showPhone(Activity activity, String phoneNumber) {
//        Uri uri = Uri.parse("tel:" + phoneNumber);
//        Intent it = new Intent(Intent.ACTION_DIAL, uri);
//        activity.startActivity(it);
//    }
//
//    public static void showPhone(Fragment fragment, String phoneNumber) {
//        Uri uri = Uri.parse("tel:" + phoneNumber);
//        Intent it = new Intent(Intent.ACTION_DIAL, uri);
//        fragment.startActivity(it);
//    }
//    public static void sendSMS(Activity activity, String smsBody) {
//        Uri smsToUri = Uri.parse("smsto:");// Uri.parse("smsto:10000");
//        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
//        intent.putExtra("sms_body", smsBody);
//        activity.startActivity(intent);
//    }
//
//    public static void sendEmail(Activity activity, String subject,
//                                 String[] extra, String body) {
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, extra);
//        emailIntent.setType("message/rfc822");
//        activity.startActivity(emailIntent);
//    }
//
//    public static void showMap(Activity activity, String title, String lat,
//                               String lng) {
//        Uri mapUri = Uri.parse("http://maps.google.com/maps?q=" + lat + ","
//                + lng + "(" + title + ")&z=21&cbp=1");
//        Intent i = new Intent(Intent.ACTION_VIEW, mapUri);
//        activity.startActivity(i);
//    }
//
//    public static void showSetGPSDlg(final Activity activity,
//                                     final String content) {
//        new AlertDialog.Builder(activity)
//                .setMessage(content)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        dialog.dismiss();
//                        Intent fireAlarm = new Intent(
//                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                        fireAlarm.addCategory(Intent.CATEGORY_DEFAULT);
//                        activity.startActivityForResult(fireAlarm, 2);
//                        // //获取GPS现在的状态（打开或是关闭状态）
//                        // boolean gpsEnabled =
//                        // Settings.Secure.isLocationProviderEnabled(
//                        // getContentResolver(), LocationManager.GPS_PROVIDER );
//                        //
//                        // if(gpsEnabled)
//                        // {
//                        // //关闭GPS
//                        // Settings.Secure.setLocationProviderEnabled(
//                        // getContentResolver(), LocationManager.GPS_PROVIDER,
//                        // false );
//                        // }
//                        // else
//                        // {
//                        // //打开GPS
//                        // Settings.Secure.setLocationProviderEnabled(
//                        // getContentResolver(), LocationManager.GPS_PROVIDER,
//                        // true);
//                        // }
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        dialog.dismiss();
//                    }
//                }).create().show();
//    }
//
//}
