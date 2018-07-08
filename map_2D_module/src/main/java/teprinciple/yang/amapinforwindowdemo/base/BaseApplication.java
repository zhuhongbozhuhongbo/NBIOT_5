package teprinciple.yang.amapinforwindowdemo.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import teprinciple.yang.amapinforwindowdemo.nettyclient.NettyClient;

/**
 * Created by Teprinciple on 2016/11/11.
 */
/*
public class BaseApplication extends Application {

    private static BaseApplication mApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

    }

    public static BaseApplication getIntance() {
        return mApplication;
    }

}
*/
public class BaseApplication extends Application {

    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Log.d("haha", "application");
        //NettyClient.getInstance().connect("10.10.10.132", 8080);//连接到服务器
        NettyClient.getInstance().connect("120.79.24.19", 8080);//连接到服务器
        //NettyClient.getInstance().connect("192.168.1.100", 8080);//连接到服务器
    }

    public static Context getContext() {
        return context;
    }

}