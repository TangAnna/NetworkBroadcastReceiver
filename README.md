# NetworkBroadcastReceiver
监测网络状态变化，广播的简单使用

学习BroadcastReceiver写的网络状态变化的例子

主要的思路就是自定义一个广播接受者，当网络有变化时去获取一下当前的网络类型，通过接口回调的方式将网络类型发送到注册广播的位置，根据网络类型做相应的业务判断

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


系统广播（System Broadcast）

Android系统广播action如下：（https://www.jianshu.com/p/ca3d87a4cdf3）


监听网络变化	android.net.conn.CONNECTIVITY_CHANGE

关闭或打开飞行模式	Intent.ACTION_AIRPLANE_MODE_CHANGED

充电时或电量发生变化	Intent.ACTION_BATTERY_CHANGED

电池电量低	Intent.ACTION_BATTERY_LOW

电池电量充足（即从电量低变化到饱满时会发出广播	Intent.ACTION_BATTERY_OKAY

系统启动完成后(仅广播一次)	Intent.ACTION_BOOT_COMPLETED

按下照相时的拍照按键(硬件按键)时	Intent.ACTION_CAMERA_BUTTON

屏幕锁屏	Intent.ACTION_CLOSE_SYSTEM_DIALOGS

设备当前设置被改变时(界面语言、设备方向等)	Intent.ACTION_CONFIGURATION_CHANGED

插入耳机时	Intent.ACTION_HEADSET_PLUG

未正确移除SD卡但已取出来时(正确移除方法:设置--SD卡和设备内存--卸载SD卡)	Intent.ACTION_MEDIA_BAD_REMOVAL

插入外部储存装置（如SD卡）	Intent.ACTION_MEDIA_CHECKING

成功安装APK	Intent.ACTION_PACKAGE_ADDED

成功删除APK	Intent.ACTION_PACKAGE_REMOVED

重启设备	Intent.ACTION_REBOOT

屏幕被关闭	Intent.ACTION_SCREEN_OFF

屏幕被打开	Intent.ACTION_SCREEN_ON

关闭系统时	Intent.ACTION_SHUTDOWN

重启设备	Intent.ACTION_REBOOT


