package com.example.tanganan.networkbroadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.example.tanganan.networkbroadcastreceiver.tools.NetStatusUtil;

/**
 * 实时监测网络状态的 广播
 * Created by TangAnna on 2018/4onReceive/16.
 */

public class NetworkReceiver extends BroadcastReceiver {

    public static final String TAG = "NetworkReceiver";
    private NetStatusInterface mNetStateInterface;


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            mNetStateInterface.netChangeListener(NetStatusUtil.getNetWorkState(context));
        }
    }

    public void setNetStateInterface(NetStatusInterface netStateInterface) {
        mNetStateInterface = netStateInterface;
    }

    public interface NetStatusInterface {
        void netChangeListener(int status);
    }
}
