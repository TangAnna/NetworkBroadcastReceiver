package com.example.tanganan.networkbroadcastreceiver;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanganan.networkbroadcastreceiver.dialog.NetWorkDialog;
import com.example.tanganan.networkbroadcastreceiver.receiver.NetworkReceiver;
import com.example.tanganan.networkbroadcastreceiver.tools.NetStatusUtil;

public class MainActivity extends AppCompatActivity implements NetworkReceiver.NetStatusInterface {

    public static final String TAG = "MainActivity";
    private TextView mTextView;
    private NetworkReceiver mMyBroadcastReceiver;
    private NetWorkDialog mNetWorkDialog;
    private Handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.tv_main_netstatus);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerMyReceiver();

    }
    /**
     * 注册
     */
    public void registerMyReceiver() {
        if (mMyBroadcastReceiver == null) {
            mMyBroadcastReceiver = new NetworkReceiver();
        }
        mMyBroadcastReceiver.setNetStateInterface(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mMyBroadcastReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unRegisterMyReceiver();
    }

    /**
     * 注销
     */
    public void unRegisterMyReceiver() {
        if (mMyBroadcastReceiver != null) {
            unregisterReceiver(mMyBroadcastReceiver);
        }
    }

    @Override
    public void netChangeListener(int status) {
        switch (status) {
            case NetStatusUtil.NETWORK_WIFI:
                //弹出提示框
                mNetWorkDialog = new NetWorkDialog();
                mNetWorkDialog.show(getSupportFragmentManager(), TAG);
                Toast.makeText(this, "当前网络类型是WIFi", Toast.LENGTH_SHORT).show();
                break;
            case NetStatusUtil.NETWORK_MOBILE:
                Toast.makeText(this, "当前网络类型是移动网络", Toast.LENGTH_SHORT).show();
                break;
            case NetStatusUtil.NETWORK_NONE:
                if (mNetWorkDialog != null && mNetWorkDialog.getDialog() != null
                        && mNetWorkDialog.getDialog().isShowing()) {
                    mNetWorkDialog.dismiss();
                }
                Toast.makeText(this, "当前没有网络", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
