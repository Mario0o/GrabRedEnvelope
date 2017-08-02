//package com.example.qianghongbao;
//
//import android.app.Notification;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.util.Log;
//import android.view.accessibility.AccessibilityEvent;
//import android.view.accessibility.AccessibilityNodeInfo;
//
//import java.util.List;
//import java.util.Random;
//
///**
// * Created by 小不点 on 2016/2/7.
// */
//public class QQ {
//
//    private Context context;
//    private AccessibilityEvent event;
//
//    private static final String TAG="QiangHongBaoService";
//    private static final String TAG_SHUA="ShuaYiSHua";
//
//    private static final String PACKAGE_QQ = "com.tencent.mobileqq";//QQ包名
//    private static final String CLASS_QQ_LIST="com.tencent.mobileqq.activity.SplashActivity";//QQ聊天列表页
//    private final static String QQ_NOTIFICATION_TIP = "[QQ红包]";
//    private final static String QQ_DEFAULT_CLICK_OPEN = "点击拆开";
//    private final static String QQ_HONG_BAO_PASSWORD = "口令红包";
//    private final static String QQ_CLICK_TO_PASTE_PASSWORD = "点击输入口令";
//    private final static String QQ_HONG_BAO_SEND="发送";
//
//    private AccessibilityNodeInfo nodeInfo;
//
//    private String packageName;
//    private String className;
//    private int eventType;
//
//    private boolean isHasReceived;//通知或聊天页面收到红包
//    private boolean isHasClicked;//点击红包
//    private boolean isHasInput;//输入红包口令
//    private boolean isHasOpened;//发送红包口令
//    private boolean isHasReceivedList;//从聊天页面收到后点击红包
//
//    public QQ(Context context,AccessibilityEvent event){
//        this.context=context;
//        this.event=event;
//
//        packageName=event.getPackageName().toString();
//        className=event.getClassName().toString();
//        eventType=event.getEventType();
//        Log.i(TAG, "event------->" + event);
//
//        if(packageName.equals(PACKAGE_QQ)){
////            Log.d(TAG,"检测到QQ服务");
////            Log.i(TAG,"event------->"+event);
//            switch (eventType){
//                case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
//                    Log.d(TAG, "检测到QQ通知");
//                    List<CharSequence> texts=event.getText();
//                    Log.i(TAG, "检测到QQ通知，文本为------------>" + texts);
//                    if(!texts.isEmpty()){
//                        String text=texts.toString();
//                        if(text.contains(QQ_NOTIFICATION_TIP)){
//                            Log.d(TAG, "准备打开通知栏");
//                            WakeupTools.wakeUpAndUnlock(context);
////                            getApplicationContext()
//                            isHasReceived=true;
//                            //以下是精华，将微信的通知栏消息打开
//                            Notification notification= (Notification) event.getParcelableData();
//                            PendingIntent pendingIntent=notification.contentIntent;
//                            try {
//                                Log.d(TAG,"准备打开通知栏");
//                                pendingIntent.send();
//                            } catch (PendingIntent.CanceledException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                    break;
//                case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
//                    Log.d(TAG, "检测到QQ界面改变");
//                    if(className.equals(CLASS_QQ_LIST)){
//                        grabHongBao();
//                    }
//                    break;
//                case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
//                    Log.d(TAG, "检测到QQ内容改变");
//                    AccessibilityNodeInfo nodeInfo=getRootInActiveWindow();
////                    Log.d(TAG,"nodeInfo--------->"+nodeInfo);
////                    if(nodeInfo==null){
////                        Log.d(TAG,"rootWindow为空");
////                        return;
////                    }
//                    grabHongBao();
//
////                    if(nodeInfo==null){
////                        Log.d(TAG,"rootWindow为空");
////                        return;
////                    }
////                    nodeInfo.findAccessibilityNodeInfosByViewId("fdf");
//                    //输入红包口令
//                    List<AccessibilityNodeInfo> node_input=nodeInfo.findAccessibilityNodeInfosByText(QQ_CLICK_TO_PASTE_PASSWORD);
//                    Log.d(TAG, "点击输入口令个数" + node_input.size());
//                    for(int i=node_input.size()-1;i>=0;i--){
//                        AccessibilityNodeInfo parent=node_input.get(i).getParent();
//                        if(isHasClicked){
//                            isHasClicked=false;
//                            isHasInput=true;
////                            Log.d(TAG, "点击输入红包口令");
//                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                        }
//                    }
//
//                    //发送红包口令
//                    List<AccessibilityNodeInfo> node_send=nodeInfo.findAccessibilityNodeInfosByText(QQ_HONG_BAO_SEND);
//                    Log.d(TAG, "点击发送输入的口令个数" + node_send.size());
//                    for(int i=node_send.size()-1;i>=0;i--){
//                        AccessibilityNodeInfo parent=node_send.get(i);
////                        Log.d(TAG, "子节点：" + node_send.get(i));
////                        Log.d(TAG, "子节点：" + node_send.get(i).getParent());
//                        if(isHasInput){
//                            isHasInput=false;
//                            isHasOpened=true;
//                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
////                            Log.d(TAG, "点击发送输入的红包口令");
//                        }
//                    }
//
//                    //聊天页面出现红包
//                    List<AccessibilityNodeInfo> node_hongbao=nodeInfo.findAccessibilityNodeInfosByText(QQ_NOTIFICATION_TIP);
//                    Log.d(TAG, "聊天页面出现的红包" + node_hongbao.size());
//                    for(int i=node_hongbao.size()-1;i>=0;i--){
//                        AccessibilityNodeInfo parent=node_hongbao.get(i);
//                        Log.d(TAG, "子节点：" + node_hongbao.get(i));
//                        isHasReceived=true;
//                        isHasReceivedList=true;
//                        parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                        Log.d(TAG, "点击聊天页面中的红包");
////                        if(isHasInput){
////                            isHasInput=false;
////                            isHasSent=true;
////                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
////                            Log.d(TAG, "点击发送输入的红包口令");
////                        }
//                    }
//
////                    grabHongBao();
//
////                    if(isHasReceivedList){
////                        isHasReceivedList=false;
////                        grabHongBao();
////                    }
//                    int length=nodeInfo.getChildCount();
//                    nodeInfo=event.getSource();
//                    Log.d(TAG, "子节点个数" + length);
//                    for(int i=0;i<length;i++){
////                        AccessibilityNodeInfo node=nodeInfo.getChild(i);
////                        Log.d(TAG, "子节点："+node);
////                        //已经拆开红包，关闭中。
////                        if(node!=null&&isHasOpened&&node.getClassName().equals("android.widget.ImageButton")){
////                            try {
////                                Thread.sleep(1*1000);//延迟一秒关闭
////                            }catch (Exception e){
////                                e.printStackTrace();
////                            }
////                            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
////                        }
////                        if(node.getText().toString().contains(QQ_NOTIFICATION_TIP)){
////                            Log.d(TAG,"--------->聊天中出现红包");
////                            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
////                        }
////                        if (node.getClassName().equals("android.widget.EditText")){
////                            node.setText("kkk");
////                            node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT);
////                        }
//
////                        if(node.getText().toString().equals(QQ_CLICK_TO_PASTE_PASSWORD)){
////                            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
////                        }
//                        //返回列表
////                        if(i==0){
////                            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
////                        }
//                    }
////                     /* 戳开红包，红包还没抢完，遍历节点匹配“拆红包” */
////                    AccessibilityNodeInfo node = (nodeInfo.getChildCount() > 3) ? nodeInfo.getChild(3) : null;
////                    System.out.println("---------"+node);
////                    if (node != null && "android.widget.Button".equals(node.getClassName())) {
////                        node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
////                    }
//
//                    shuaYiShua(event);
//
//                    break;
//            }
//
//        }
//
//    }
//
//
//    private void grabHongBao(){
//        WakeupTools.wakeUpAndUnlock(context);//解锁
//        nodeInfo=getRootInActiveWindow();
//        if(nodeInfo==null){
//            Log.d(TAG,"rootWindow为空");
//            return;
//        }
//
//        //设置0.3秒内随机抢
//        Random random=new Random();
//        int time=random.nextInt(300);
//        System.out.println(time);
//        try {
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //普通红包
//        List<AccessibilityNodeInfo> list=nodeInfo.findAccessibilityNodeInfosByText(QQ_DEFAULT_CLICK_OPEN);
//        Log.i(TAG,"普通红包的个数为："+list.size());
//        //最新的红包领起
//        for(int i=list.size()-1;i>=0;i--){
//            AccessibilityNodeInfo parent=list.get(i).getParent();
//            Log.i(TAG, "----------------->普通红包：" + parent);
//            if(parent!=null){
//                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                isHasClicked=true;
//                isHasOpened=true;
////                Log.d(TAG,"点击领红包");
////                if(isHasReceived){
////                    parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//////                    isHasReceived=false;
////                    isHasClicked=true;
////                    Log.d(TAG,"点击领红包");
////                }
//                break;
//            }
//        }
//        //口令红包
//        list=nodeInfo.findAccessibilityNodeInfosByText(QQ_HONG_BAO_PASSWORD);
//        Log.i(TAG,"口令红包的个数为："+list.size());
//        //最新的红包领起
//        for(int i=list.size()-1;i>=0;i--){
//            AccessibilityNodeInfo parent=list.get(i).getParent();
//            Log.i(TAG, "----------------->口令红包：" + parent);
//            if(parent!=null&&parent.getClassName().equals("android.widget.TextView")){
//                isHasClicked=true;
//                Log.d(TAG, "点击领红包");
//                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
////                if(isHasReceived){
////                    isHasReceived=false;
////                    isHasClicked=true;
////                    Log.d(TAG, "点击领红包");
////                    parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
////                }
//                break;
//            }
//        }
//    }
//}