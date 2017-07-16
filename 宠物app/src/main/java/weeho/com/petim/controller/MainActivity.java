package weeho.com.petim.controller;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.Utils;
import com.weeho.petim.lib.utils.MD5Tools;
import com.weeho.petim.lib.utils.VerificationMD5;

import java.util.List;

import weeho.com.petim.R;
import weeho.com.petim.application.MyApplication;
import weeho.com.petim.db.InviteMessgeDao;
import weeho.com.petim.fragment.AchievementFragment;
import weeho.com.petim.fragment.ImFragemnt;
import weeho.com.petim.fragment.MyFragment;
import weeho.com.petim.hxim.Constant;
import weeho.com.petim.hxim.HxHelper;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private FrameLayout fragment_content;
    private ImageView ivHome;
    private TextView main_activity_tab_home_tv;
    private RelativeLayout main_activity_tab_communicate;
    private ImageView ivAchieve;
    private TextView tvAchieve;
    private RelativeLayout main_activity_tab_achievement;
    private ImageView main_activity_tab_shopcart_iv;
    private TextView tvHome;
    private Button main_activity_product_num;
    private RelativeLayout main_activity_tab_shopping_cart;
    private ImageView ivMy;
    private TextView tvMy;
    private RelativeLayout main_activity_tab_my;
    private LinearLayout main_activity_tab_parent;
    private RelativeLayout relative_parent;

    private FragmentManager fragmentManager;
    private ImFragemnt imFragment;
    private AchievementFragment achieveFragment;
    private MyFragment myFragment;
    private FragmentType currentFragmentType;
    private String pw;
    private String TAG = "MainActivity";
    private LocalBroadcastManager broadcastManager;
    private BroadcastReceiver broadcastReceiver;
    private TextView unreadLabel;
    private TextView unreadAddressLable;
    private InviteMessgeDao inviteMessgeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
//        Register();
        LoadHxIm();
    }

    private void initData() {
        inviteMessgeDao = new InviteMessgeDao(this);
        fragmentManager = getSupportFragmentManager();
        showComunitionFragment();
    }

    /**
     *
     * 测试用注册环信账户
     * */
    private void Register(){
//        try {
//            pw = Md5Pw("123456");
//            EMClient.getInstance().createAccount("wangk", pw);//同步方法
//        } catch (HyphenateException e) {
//            e.printStackTrace();
//        }
                pw = "123456";
        new Thread(new Runnable() {
            public void run() {
                try {
                    // call method in SDK
                        EMClient.getInstance().createAccount("wangku", pw);
                    runOnUiThread(new Runnable() {
                        public void run() {
//                            if (!RegisterActivity.this.isFinishing())
//                                pd.dismiss();
                            // save current user
                            HxHelper.getInstance().setCurrentUserName("wangku");
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registered_successfully), Toast.LENGTH_SHORT).show();
//                            finish();
                        }
                    });
                } catch (final HyphenateException e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
//                            if (!RegisterActivity.this.isFinishing())
//                                pd.dismiss();
                            int errorCode=e.getErrorCode();
                            if(errorCode== EMError.NETWORK_ERROR){
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_anomalies), Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.USER_ALREADY_EXIST){
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.User_already_exists), Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.USER_AUTHENTICATION_FAILED){
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.registration_failed_without_permission), Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.USER_ILLEGAL_ARGUMENT){
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.illegal_user_name),Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registration_failed), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        }).start();
    }
    //加密注册密码
    private String Md5Pw(String pw){
       return MD5Tools.MD5(pw);

    }
    public int getUnreadMsgCountTotal() {
        int unreadMsgCountTotal = 0;
        int chatroomUnreadMsgCount = 0;
        unreadMsgCountTotal = EMClient.getInstance().chatManager().getUnreadMessageCount();
        for(EMConversation conversation:EMClient.getInstance().chatManager().getAllConversations().values()){
            if(conversation.getType() == EMConversation.EMConversationType.ChatRoom)
                chatroomUnreadMsgCount=chatroomUnreadMsgCount+conversation.getUnreadMsgCount();
        }
        return unreadMsgCountTotal-chatroomUnreadMsgCount;
    }
    public void updateUnreadAddressLable() {
        runOnUiThread(new Runnable() {
            public void run() {
                int count = getUnreadAddressCountTotal();
                if (count > 0) {
                    unreadAddressLable.setVisibility(View.VISIBLE);
                } else {
                    unreadAddressLable.setVisibility(View.INVISIBLE);
                }
            }
        });


    }
    /**
     * get unread event notification count, including application, accepted, etc
     *
     * @return
     */
    public int getUnreadAddressCountTotal() {
        int unreadAddressCountTotal = 0;
        unreadAddressCountTotal = inviteMessgeDao.getUnreadMessagesCount();
        return unreadAddressCountTotal;
    }
//    public void updateUnreadLabel() {
//        int count = getUnreadMsgCountTotal();
//        if (count > 0) {
//            unreadLabel.setText(String.valueOf(count));
//            unreadLabel.setVisibility(View.VISIBLE);
//        } else {
//            unreadLabel.setVisibility(View.INVISIBLE);
//        }
//    }
    private void registerBroadcastReceiver() {
        broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_CONTACT_CHANAGED);
        intentFilter.addAction(Constant.ACTION_GROUP_CHANAGED);
//        intentFilter.addAction(RPConstant.REFRESH_GROUP_RED_PACKET_ACTION);
        // refresh conversation list
//red packet code : 处理红包回执透传消息
//end of red packet code
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
//                updateUnreadLabel();
                //更新未读消息个数
                updateUnreadAddressLable();
                if (currentFragmentType == FragmentType.IMfRAGMRNT) {
                    // refresh conversation list
                    if (imFragment != null) {
                        imFragment.refresh();
                    }
                }
//                else if (currentTabIndex == 1) {
//                    if(contactListFragment != null) {
//                        contactListFragment.refresh();
//                    }
//                }
//                String action = intent.getAction();
//                if(action.equals(Constant.ACTION_GROUP_CHANAGED)){
//                    if (EaseCommonUtils.getTopActivity(MainActivity.this).equals(GroupsActivity.class.getName())) {
//                        GroupsActivity.instance.onResume();
//                    }
//                }
//                //red packet code : 处理红包回执透传消息
//                if (action.equals(RPConstant.REFRESH_GROUP_RED_PACKET_ACTION)){
//                    if (conversationListFragment != null){
//                        conversationListFragment.refresh();
//                    }
//                }
                //end of red packet code
            }
        };
        broadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }
    private void refreshUIWithMessage() {
        runOnUiThread(new Runnable() {
            public void run() {
                // refresh unread count
                updateUnreadAddressLable();
                if (currentFragmentType == FragmentType.IMfRAGMRNT) {
                    // refresh conversation list
                    if (imFragment != null) {
                        imFragment.refresh();
                    }
                }
            }
        });
    }
    EMMessageListener messageListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            // notify new message
            for (EMMessage message : messages) {
               HxHelper.getInstance().getNotifier().onNewMsg(message);
            }
            refreshUIWithMessage();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //red packet code : 处理红包回执透传消息
//            for (EMMessage message : messages) {
//                EMCmdMessageBody cmdMsgBody = (EMCmdMessageBody) message.getBody();
//                final String action = cmdMsgBody.action();//获取自定义action
//                if (action.equals(RPConstant.REFRESH_GROUP_RED_PACKET_ACTION)) {
//                    RedPacketUtil.receiveRedPacketAckMessage(message);
//                }
//            }
            //end of red packet code
            refreshUIWithMessage();
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {}
    };

    @Override
    protected void onResume() {
        super.onResume();
        HxHelper sdkHelper = HxHelper.getInstance();
        sdkHelper.pushActivity(this);

        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

    /*
        * 初始化拉去环信聊天记录
        * */
    private void LoadHxIm(){
        EMClient.getInstance().login("wangkui", "123456", new EMCallBack() {

            @Override
            public void onSuccess() {
                Log.d(TAG, "login: onSuccess");


                // ** manually load all local groups and conversation
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                // update current user's display name for APNs
                boolean updatenick = EMClient.getInstance().pushManager().updatePushNickname(
                        MyApplication.currentUserNick.trim());
                if (!updatenick) {
                    Log.e("LoginActivity", "update current user nick fail");
                }

                // get user's info (this should be get from App's server or 3rd party service)
                HxHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();


            }

            @Override
            public void onProgress(int progress, String status) {
                Log.d(TAG, "login: onProgress");
            }

            @Override
            public void onError(final int code, final String message) {
                Log.d(TAG, "login: onError: " + code);
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), getString(R.string.Login_failed) + message,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void initView() {
        fragment_content = (FrameLayout) findViewById(R.id.fragment_content);
        ivHome = (ImageView) findViewById(R.id.main_activity_tab_home_iv);
        tvHome = (TextView) findViewById(R.id.main_activity_tab_home_tv);
        main_activity_tab_communicate = (RelativeLayout) findViewById(R.id.main_activity_tab_communicate);
        ivAchieve = (ImageView) findViewById(R.id.main_activity_tab_find_iv);
        tvAchieve = (TextView) findViewById(R.id.main_activity_tab_find_tv);
        main_activity_tab_achievement = (RelativeLayout) findViewById(R.id.main_activity_tab_achievement);
        main_activity_tab_shopcart_iv = (ImageView) findViewById(R.id.main_activity_tab_shopcart_iv);
        main_activity_product_num = (Button) findViewById(R.id.main_activity_product_num);
        main_activity_tab_shopping_cart = (RelativeLayout) findViewById(R.id.main_activity_tab_shopping_cart);
        ivMy = (ImageView) findViewById(R.id.main_activity_tab_my_iv);
        tvMy = (TextView) findViewById(R.id.main_activity_tab_my_tv);
        main_activity_tab_my = (RelativeLayout) findViewById(R.id.main_activity_tab_my);
        main_activity_tab_parent = (LinearLayout) findViewById(R.id.main_activity_tab_parent);
        relative_parent = (RelativeLayout) findViewById(R.id.relative_parent);
        unreadAddressLable = (TextView) findViewById(R.id.unreadAddressLable);

        main_activity_product_num.setOnClickListener(this);

        main_activity_tab_communicate.setOnClickListener(this);
        main_activity_tab_achievement.setOnClickListener(this);
        main_activity_tab_my.setOnClickListener(this);

        ivHome.setOnClickListener(this);
        ivAchieve.setOnClickListener(this);
        ivMy.setOnClickListener(this);
    }
    public enum FragmentType{
        IMfRAGMRNT,ACHIEVE,MYCENTER
    }
    private void showComunitionFragment() {
        // ViewUtil.hideKeyBoard(etInput, baseContext);
        currentFragmentType = FragmentType.IMfRAGMRNT;
        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.out_alpha, 0);
        // 隐藏存在的fragment
		hideFragment(transaction);

        if (imFragment == null) {
            // 对象不存在
            imFragment = new ImFragemnt();
        }
        if (!imFragment.isAdded()) {
            // 没有被添加过
            transaction.add(R.id.fragment_content, imFragment)
                    .commitAllowingStateLoss();
        } else {
            // 被添加过
            transaction.show(imFragment).commitAllowingStateLoss();
        }
    }

    private void hideFragment(FragmentTransaction transaction) {
		if (achieveFragment != null) {
			transaction.hide(achieveFragment);
		}
		if (imFragment != null) {
			transaction.hide(imFragment);
		}
		if (myFragment != null) {
			transaction.hide(myFragment);
		}
	}

    private void showAchieveFragment() {
        // ViewUtil.hideKeyBoard(etInput, baseContext);
        currentFragmentType = FragmentType.ACHIEVE;
        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.out_alpha, 0);
        // 隐藏存在的fragment
		hideFragment(transaction);

        if (achieveFragment == null) {
            // 对象不存在
            achieveFragment = new AchievementFragment();
        }
        if (!achieveFragment.isAdded()) {
            // 没有被添加过
            transaction.add(R.id.fragment_content, achieveFragment)
                    .commitAllowingStateLoss();
        } else {
            // 被添加过
            transaction.show(achieveFragment).commitAllowingStateLoss();
        }
    }

    private void showMyFragment() {
        // ViewUtil.hideKeyBoard(etInput, baseContext);
        currentFragmentType = FragmentType.MYCENTER;
        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.out_alpha, 0);
        // 隐藏存在的fragment
		hideFragment(transaction);

        if (myFragment == null) {
            // 对象不存在
            myFragment = new MyFragment();
        }
        if (!myFragment.isAdded()) {
            // 没有被添加过
            transaction.add(R.id.fragment_content, myFragment)
                    .commitAllowingStateLoss();
        } else {
            // 被添加过
            transaction.show(myFragment).commitAllowingStateLoss();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_activity_tab_communicate:
                /**
                 * 聊天主界面
                 */
                MainFragment();
                break;

            case R.id.main_activity_tab_achievement:
                /**
                 * 成就
                 */
                AchieveFragment();
                break;
            case R.id.main_activity_tab_home_iv:
                /**
                 * 聊天主界面
                 */
                MainFragment();
                break;

            case R.id.main_activity_tab_find_iv:
                /**
                 * 成就
                 */
                AchieveFragment();
                break;
            case R.id.main_activity_tab_my:
                /**
                 * 个人中心页面
                 */
                MyFragment();
                break;
            case R.id.main_activity_tab_my_iv:
                /**
                 * 个人中心页面
                 */
                MyFragment();
                break;

        }
    }

    private void MyFragment() {
        if (!(FragmentType.MYCENTER == currentFragmentType)) {
            currentFragmentType = FragmentType.MYCENTER;
            initTab();
            ivMy.setImageResource(R.drawable.myclick);
            tvMy.setTextColor(getResources().getColor(
                    R.color.color_1AAD19));
            showMyFragment();
        }
    }

    private void AchieveFragment() {
        if (!(FragmentType.ACHIEVE == currentFragmentType)) {
            currentFragmentType = FragmentType.ACHIEVE;
            initTab();
            ivAchieve.setImageResource(R.drawable.achievemented);
            tvAchieve.setTextColor(getResources().getColor(
                    R.color.color_1AAD19));
showAchieveFragment();

        }
    }

    private void MainFragment() {
        if (!(FragmentType.IMfRAGMRNT == currentFragmentType)) {
            currentFragmentType = FragmentType.IMfRAGMRNT;
            initTab();
            ivHome.setImageResource(R.drawable.comniued);
            tvHome.setTextColor(getResources().getColor(
                    R.color.color_1AAD19));
showComunitionFragment();
        }
    }

    private void initTab() {
        ivHome.setImageResource(R.drawable.comniu);
        ivAchieve.setImageResource(R.drawable.achievement);
        ivMy.setImageResource(R.drawable.my);

        tvHome.setTextColor(getResources().getColor(R.color.color_8f8f8f));
        tvAchieve.setTextColor(getResources().getColor( R.color.color_8f8f8f));
        tvMy.setTextColor(getResources().getColor(R.color.color_8f8f8f));

    }
}
