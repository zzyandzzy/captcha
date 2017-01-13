package com.zzy.captcha.xposed.hook;

import android.content.Context;
import android.net.TrafficStats;
import android.widget.TextView;

import com.zzy.captcha.xposed.utils.PreferencesUtils;
import com.zzy.captcha.xposed.utils.XposedUtils;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by zzyandzzy on 2017/1/12.
 */

public class SystemUIHook {
    private static final int TRAFFICUPDATE = 1;
    private static String time;
    private static TextView trafficTextView;
//    private static Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            XposedBridge.log("流量测试:handler:"+msg.what);
//            switch (msg.what){
//                case TRAFFICUPDATE:
//                    String traffic;
////                    long mobileRxBytes = getMobileRxBytes();
////                    long mobileTxBytes = getMobileTxBytes();
//                    long totalRxBytes = getTotalRxBytes();
//                    long totalTxBytes = getTotalTxBytes();
//                    long totalRxBytesRunnable = msg.getData().getLong("totalRxBytes");
//                    long totalTxBytesRunnable = msg.getData().getLong("totalTxBytes");
//                    long change = (totalRxBytes - totalRxBytesRunnable) + (totalTxBytes - totalTxBytesRunnable);
//                    if (change <= 1024)
//                        traffic = change + "B/s";
//                    else if(change > 1024 && change < 1024 * 1024)
//                        traffic = change / 1024 + "KB/s";
//                    else if(change > 1024 * 1024 && change < 1024 * 1024 * 1024)
//                        traffic = change / 1024 / 1024 + "MB/s";
//                    else
//                        traffic = "0B/s";
//                    XposedBridge.log("流量测试:change:"+change + traffic);
//                    trafficTextView.setText(time + PreferencesUtils.getString("systemuitext"," i GDQ") + traffic);
//                    break;
//            }
//        }
//    };
//    private static Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            Message message = handler.obtainMessage();
//            message.what = TRAFFICUPDATE;
//            Bundle bundle = new Bundle();
////            bundle.putLong("mobileRxBytes",getMobileRxBytes());
////            bundle.putLong("mobileTxBytes",getMobileTxBytes());
//            bundle.putLong("totalRxBytes",getTotalRxBytes());
//            bundle.putLong("totalTxBytes",getTotalTxBytes());
//            message.obj = bundle;
//            XposedBridge.log("流量测试:runnable:"+getTotalRxBytes());
//            handler.sendMessage(message);
//            handler.postDelayed(runnable,1000);
//        }
//    };

    public static void hookSystemUI(ClassLoader classLoader){
       XposedHelpers.findAndHookMethod(XposedUtils.SYSTEMUI_STATUSBAR_POLICY_CLOCK, classLoader, "updateClock", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                TextView textView = (TextView) param.thisObject;
                Context context = textView.getContext();
                String str = textView.getText().toString();
                trafficManager(textView,str);
                //textView.setText(str + PreferencesUtils.getString("systemuitext"," i GDQ"));
            }
        });
    }

    private static void trafficManager(final TextView textView,String str) {
//        if (handler != null && runnable != null)
//            handler.removeCallbacks(runnable);
        trafficTextView = textView;
        trafficTextView.setTextSize(12);
        time = str;
        trafficTextView.setText(str + PreferencesUtils.getString("systemuitext"," i GDQ"));
        //handler.postDelayed(runnable,0);
//        final long totalRxBytes = getTotalRxBytes();
//        final long totalTxBytes = getTotalTxBytes();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                traffic(textView,totalRxBytes,totalTxBytes);
//            }
//        },1000);
    }

//    private static void traffic(TextView trafficTextView, long totalRxBytesRunnable, long totalTxBytesRunnable) {
//        String traffic;
//        long totalRxBytes = getTotalRxBytes();
//        long totalTxBytes = getTotalTxBytes();
//        long change = (totalRxBytes - totalRxBytesRunnable) + (totalTxBytes - totalTxBytesRunnable);
//        if (change <= 1024)
//            traffic = change + "B/s";
//        else if(change > 1024 && change < 1024 * 1024)
//            traffic = change / 1024 + "KB/s";
//        else if(change > 1024 * 1024 && change < 1024 * 1024 * 1024)
//            traffic = change / 1024 / 1024 + "MB/s";
//        else
//            traffic = "0B/s";
//        trafficTextView.setText(PreferencesUtils.getString("systemuitext"," i GDQ")+ traffic);
//        trafficManager(trafficTextView);
//    }

    /**
     * 自从设备启动以来，使用的移动网络接收的流量字节，不含wifi
     */
    private static long getMobileRxBytes(){
        return TrafficStats.getMobileRxBytes() == TrafficStats.UNSUPPORTED ? -1
                : (TrafficStats.getMobileRxBytes());
    }
    /**
     * 自从设备启动以来，使用的移动网络发送的流量字节，不含wifi
     */
    private static long getMobileTxBytes(){
        return TrafficStats.getMobileTxBytes() == TrafficStats.UNSUPPORTED ? -1
                : (TrafficStats.getMobileTxBytes());
    }
    /**
     * 自从设备启动以来，使用的网络接收的流量字节
     */
    private static long getTotalRxBytes(){
        return TrafficStats.getTotalRxBytes() == TrafficStats.UNSUPPORTED ? -1
                : (TrafficStats.getTotalRxBytes());
    }
    /**
     * 自从设备启动以来，使用的网络发送的流量字节
     */
    private static long getTotalTxBytes(){
        return TrafficStats.getTotalTxBytes() == TrafficStats.UNSUPPORTED ? -1
                : (TrafficStats.getTotalTxBytes());
    }

}
