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
   