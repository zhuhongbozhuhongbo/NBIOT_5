package teprinciple.yang.amapinforwindowdemo.functest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import teprinciple.yang.amapinforwindowdemo.R;
import teprinciple.yang.amapinforwindowdemo.dom4j.ChartDatas;
import teprinciple.yang.amapinforwindowdemo.nettyclient.Constant;
import teprinciple.yang.amapinforwindowdemo.nettyclient.INettyClient;
import teprinciple.yang.amapinforwindowdemo.nettyclient.NettyClient;

public class FuncActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mbtn0;
    private Button mbtn1;
    private Button mbtn2;
    private Button mbtn3;
    private Button mbtn4;
    private TextView mtv0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func);
        initView();

        NettyClient.getInstance().addDataReceiveListener(new INettyClient.OnDataReceiveListener() {
            @Override
            public void onDataReceive(int mt, ChartDatas body) {
                Log.e("haha", "FuncActivity收到:");
                String result = "querymode:" + body.getQuerymode() + " type:" + body.getType() + " field:" +
                        body.getField() + " p0:" + body.getP0() + " " + " p1:" + body.getP1() + " "+ " p2:" + body.getP2() + " "+ " p3:" + body.getP3() + " "
                        + " p4:" + body.getP4() + " " + " p5:" + body.getP5() + " "+ " p6:" + body.getP6() + " "+ " p7:" + body.getP7() + " "+ " p8:" + body.getP8() + " "
                        + " p9:" + body.getP9() + " "+ " p10:" + body.getP10();
                mtv0.setText(result);
                Log.d("haha", "内容为：" + result);
            }
        });


    }

    private void initView(){
        mbtn0 = findViewById(R.id.button0);
        mbtn1 = findViewById(R.id.button1);
        mbtn2 = findViewById(R.id.button2);
        mbtn3 = findViewById(R.id.button3);
        mbtn4 = findViewById(R.id.button4);
        mtv0 = findViewById(R.id.tv0);

        mbtn0.setOnClickListener(this);
        mbtn1.setOnClickListener(this);
        mbtn2.setOnClickListener(this);
        mbtn3.setOnClickListener(this);
        mbtn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.button0){
            //设备动态信息
            NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<query><userid>001</userid><passwd>aaa</passwd><field>GPSInformation</field><type>DDI</type>" +
                    "<querymode>findByIdAndHour</querymode><p0>6</p0><p1>2018-06-3</p1><p2>empty</p2><p3>10:00:00</p3><p4>10:30:00</p4><p5>null</p5></query>", 0);
            Log.d("haha", "xm");
        }else if(v.getId() == R.id.button1){
            //设备静态信息
            NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<query><userid>001</userid><passwd>aaa</passwd><field>all</field><type>DSI</type>" +
                    "<querymode>findByIotDeviceId</querymode><p0>empty</p0><p1>6</p1><p2>empty</p2><p3>empty</p3><p4>empty</p4><p5>empty</p5></query>", 0);
            Log.d("haha", "jt");
        }else if(v.getId() == R.id.button2){
            //项目部
            NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<query><userid>001</userid><passwd>aaa</passwd><field>all</field><type>PDI</type>" +
                    "<querymode>findByProjectProvince</querymode><p0>安徽省</p0><p1>empty</p1><p2>empty</p2><p3>null</p3><p4>null</p4><p5>null</p5></query>", 0);
            Log.d("haha", "t");

        }else if(v.getId() == R.id.button3){
            //统计信息
            NettyClient.getInstance().sendMessage(Constant.MSG_TYPE,"<query><userid>001</userid><passwd>aaa</passwd><field>all</field><type>SI</type><querymode>findById" +
                    "</querymode><p0>6</p0><p1>empty</p1><p2> null</p2><p3> null</p3><p4> null</p4><p5> null</p5></query>", 0);
            Log.d("haha", "tj");
        }else if(v.getId() == R.id.button4){
            //用户信息
            NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<query><userid>001</userid><passwd>aaa</passwd><field>all</field><type>User</type><querymode>findAll" +
                    "</querymode><p0>null</p0><p1>null</p1><p2>null</p2><p3>null</p3><p4>null</p4><p5>null</p5></query>", 0);
            Log.d("haha", "yh");
        }
    }
}
