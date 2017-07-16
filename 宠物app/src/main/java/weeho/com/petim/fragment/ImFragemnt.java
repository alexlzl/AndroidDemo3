
package weeho.com.petim.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.easeui.widget.EaseConversationList;
import com.hyphenate.util.DateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import weeho.com.petim.R;
import weeho.com.petim.base.BaseFragment;
import weeho.com.petim.hxim.ChatActivity;
import weeho.com.petim.hxim.Constant;

/**
 * Created by wangkui on 2017/4/18.
 */


public class ImFragemnt extends BaseFragment {


    private LinearLayout rc_msg;
    private TextView tv_msg1;
    private TextView tv_time1;
    private final static int MSG_REFRESH = 2;
    private final static int MSG_NEW = 3;
    @Override
    protected boolean isShowLeftIcon() {
        return false;
    }
    private String userId;
    protected List<EMConversation> conversationList = new ArrayList<EMConversation>();
    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.imfragment, container,
                true);
        initView(rootView);
        initData();
        return rootView;
    }
    /**
     * refresh ui
     */
    public void refresh() {
        if(!handler.hasMessages(MSG_REFRESH)){
            handler.sendEmptyMessage(MSG_REFRESH);
        }
    }

    protected Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
//                    onConnectionDisconnected();conversationList.addAll(loadConversationList())
                    break;
                case 1:
//                    onConnectionConnected();
                    break;

                case MSG_REFRESH:
                {
                    conversationList.clear();
                    conversationList.addAll(loadConversationList());
                    setView(conversationList);
//                    conversationListView.refresh();

                    break;

                }
                case MSG_NEW:
                        //更新界面

                    break;
                default:
                    break;
            }
        }
    };
    private EaseConversationList.EaseConversationListHelper cvsListHelper;

    public void setCvsListHelper(EaseConversationList.EaseConversationListHelper cvsListHelper){
        this.cvsListHelper = cvsListHelper;
    }
    //
    private void setView(List<EMConversation> conversationList) {
       for(EMConversation conversation:conversationList){
           if(conversation.getType() == EMConversation.EMConversationType.Chat){
               if (conversation.getAllMsgCount() != 0) {
                   // show the content of latest message
                   EMMessage lastMessage = conversation.getLastMessage();
                   String content = null;
                   if(cvsListHelper != null){
                       content = cvsListHelper.onSetItemSecondaryText(lastMessage);
                   }
                   tv_msg1.setText(EaseSmileUtils.getSmiledText(getContext(), EaseCommonUtils.getMessageDigest(lastMessage, (this.getContext()))),TextView.BufferType.SPANNABLE);
//                   if(content != null){
//                       tv_msg1.setText(content);
//                   }
                   tv_time1.setText(DateUtils.getTimestampString(new Date(lastMessage.getMsgTime())));
//                   if (lastMessage.direct() == EMMessage.Direct.SEND && lastMessage.status() == EMMessage.Status.FAIL) {
//                       holder.msgState.setVisibility(View.VISIBLE);
//                   } else {
//                       holder.msgState.setVisibility(View.GONE);
//                   }
               }
//               tv_msg1.setText(conversion.g);
           }
       }

    }


    /**
     * load conversation list
     *
     * @return
    +    */
    protected List<EMConversation> loadConversationList(){
        // get all conversations
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        List<Pair<Long, EMConversation>> sortList = new ArrayList<Pair<Long, EMConversation>>();
        /**
         * lastMsgTime will change if there is new message during sorting
         * so use synchronized to make sure timestamp of last message won't change.
         */
        synchronized (conversations) {
            for (EMConversation conversation : conversations.values()) {
                if (conversation.getAllMessages().size() != 0) {
                    sortList.add(new Pair<Long, EMConversation>(conversation.getLastMessage().getMsgTime(), conversation));
                }
            }
        }
        try {
            // Internal is TimSort algorithm, has bug
            sortConversationByLastChatTime(sortList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<EMConversation> list = new ArrayList<EMConversation>();
        for (Pair<Long, EMConversation> sortItem : sortList) {
            list.add(sortItem.second);
        }
        return list;
    }
    /**
     * sort conversations according time stamp of last message
     *
     * @param conversationList
     */
    private void sortConversationByLastChatTime(List<Pair<Long, EMConversation>> conversationList) {
        Collections.sort(conversationList, new Comparator<Pair<Long, EMConversation>>() {
            @Override
            public int compare(final Pair<Long, EMConversation> con1, final Pair<Long, EMConversation> con2) {

                if (con1.first.equals(con2.first)) {
                    return 0;
                } else if (con2.first.longValue() > con1.first.longValue()) {
                    return 1;
                } else {
                    return -1;
                }
            }

        });
    }
    private void initData() {

//        EMMessageListener msgListener = new EMMessageListener() {
//
//            @Override
//            public void onMessageReceived(List<EMMessage> messages) {
//                //收到消息
//                for (EMMessage message : messages) {
//                    String username = null;
//                    // group message
//                    if (message.getChatType() == EMMessage.ChatType.GroupChat || message.getChatType() == EMMessage.ChatType.ChatRoom) {
//                        username = message.getTo();
//                    } else {
//                        // single chat message
//                        username = message.getFrom();
//                    }
//                    userId = username;
//                    if(message.getType().equals(EMMessage.Type.TXT))
////                    tv_msg1.setText(message.getBody().toString());
//                    // if the message is for current conversation
////                    if (username.equals(toChatUsername) || message.getTo().equals(toChatUsername)) {
//////                        messageList.refreshSelectLast();
////                        EaseUI.getInstance().getNotifier().vibrateAndPlayTone(message);
////                        conversation.markMessageAsRead(message.getMsgId());
////                    } else {
////                        EaseUI.getInstance().getNotifier().onNewMsg(message);
////                    }
//                }
//            }
//
//            @Override
//            public void onCmdMessageReceived(List<EMMessage> messages) {
//                //收到透传消息
//            }
//
//            @Override
//            public void onMessageRead(List<EMMessage> messages) {
//                //收到已读回执
//            }
//
//            @Override
//            public void onMessageDelivered(List<EMMessage> message) {
//                //收到已送达回执
//            }
//
//            @Override
//            public void onMessageChanged(EMMessage message, Object change) {
//                //消息状态变动
//            }
//        };
//        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    private void initView(View rootView){
        rc_msg = (LinearLayout) rootView.findViewById(R.id.rc_msg);
        tv_msg1 = (TextView) rootView.findViewById(R.id.tv_msg1);
        tv_time1 = (TextView) rootView.findViewById(R.id.tv_time1);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.rc_msg:
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("userId", "wangku");
                intent.putExtra("chatType", Constant.CHATTYPE_SINGLE);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void getNetData() {

    }

    @Override
    protected void setViewListener() {
        rc_msg.setOnClickListener(this);
    }

    @Override
    protected boolean hasTitle() {
        return true;
    }

    @Override
    protected boolean hasTitleIcon() {
        return false;
    }

    @Override
    protected boolean hasDownIcon() {
        return false;
    }

    @Override
    protected void reloadCallback() {

    }

    @Override
    protected String setTitleName() {
        return "交流";
    }

    @Override
    protected String setRightText() {
        return null;
    }

    @Override
    protected int setLeftImageResource() {
        return 0;
    }

    @Override
    protected int setMiddleImageResource() {
        return 0;
    }

    @Override
    protected int setRightImageResource() {
        return 0;
    }
}
