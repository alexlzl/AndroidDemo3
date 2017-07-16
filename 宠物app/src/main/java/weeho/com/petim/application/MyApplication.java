package weeho.com.petim.application;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import weeho.com.petim.hxim.HxHelper;

/**
 * Created by wangkui on 2017/4/21.
 */

public class MyApplication extends Application {
    public static Context applicationContext;
    private static MyApplication instance;
    public static boolean debugToggle = true;
    // login user name
    public final String PREF_USERNAME = "username";

    /**
     * nickname for current user, the nickname instead of ID be shown when user receive notification from APNs
     */
    public static String currentUserNick = "";

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        instance = this;
        //init demo helper
        HxHelper.getInstance().init(applicationContext);
        //red packet code : 初始化红包SDK，开启日志输出开关
//        RedPacket.getInstance().initRedPacket(applicationContext, RPConstant.AUTH_METHOD_EASEMOB, new RPInitRedPacketCallback() {
//
//            @Override
//            public void initTokenData(RPValueCallback<TokenData> callback) {
//                TokenData tokenData = new TokenData();
//                tokenData.imUserId = EMClient.getInstance().getCurrentUser();
//                //此处使用环信id代替了appUserId 开发者可传入App的appUserId
//                tokenData.appUserId = EMClient.getInstance().getCurrentUser();
//                tokenData.imToken = EMClient.getInstance().getAccessToken();
//                //同步或异步获取TokenData 获取成功后回调onSuccess()方法
//                callback.onSuccess(tokenData);
//            }
//
//            @Override
//            public RedPacketInfo initCurrentUserSync() {
//                //这里需要同步设置当前用户id、昵称和头像url
//                String fromAvatarUrl = "";
//                String fromNickname = EMClient.getInstance().getCurrentUser();
//                EaseUser easeUser = EaseUserUtils.getUserInfo(fromNickname);
//                if (easeUser != null) {
//                    fromAvatarUrl = TextUtils.isEmpty(easeUser.getAvatar()) ? "none" : easeUser.getAvatar();
//                    fromNickname = TextUtils.isEmpty(easeUser.getNick()) ? easeUser.getUsername() : easeUser.getNick();
//                }
//                RedPacketInfo redPacketInfo = new RedPacketInfo();
//                redPacketInfo.fromUserId = EMClient.getInstance().getCurrentUser();
//                redPacketInfo.fromAvatarUrl = fromAvatarUrl;
//                redPacketInfo.fromNickName = fromNickname;
//                return redPacketInfo;
//            }
//        });
//        RedPacket.getInstance().setDebugMode(true);
        //end of red packet code
    }

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }
}
