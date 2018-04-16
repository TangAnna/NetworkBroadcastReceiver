# NetworkBroadcastReceiver
监测网络状态变化，广播的简单使用

学习BroadcastReceiver写的网络状态变化的例子

BroadcastReceiver广播，Android四大组件之一，它使用的是设计者模式中的观察者模式，基于消息的发布 / 订阅事件模型

广播的使用需要注册，注册方式有两种，动态注册和静态注册：

静态注册：

在manifest中使用<receiver/>标签，如：

       <receiver android:name=".receiver.NetworkReceiver">
             <intent-filter>
                 <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
             </intent-filter>
       </receiver>

动态注册，在使用的地方使用registerReceiver()和unregisterReceiver()方法进行注册和注销，注册时建议在onResume()方法中进行，注销在onPause()方法中进行，
在Activity的生命周期中这两个方法是一定会调用的，避免了内存泄漏的危险，代码如下：

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

两种注册方式的不同：
1，静态注册：全局的，只要程序启动就开始启动，不受任何生命周期的约束，就算程序关闭依然会接收到广播，所以比较耗电占用内存；
2，动态注册，灵活，跟随组件的生命周期而变化，需要特定时刻监听广播；

