package com.example.nbiot_5.charts;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.nbiot_5.R;
import com.example.nbiot_5.picker.PickDatePopupWindow;
import com.example.nbiot_5.picker.PickTimePopupWindow;
import com.example.nbiot_5.picker.TimePickerActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import teprinciple.yang.amapinforwindowdemo.dom4j.ChartDatas;
import teprinciple.yang.amapinforwindowdemo.nettyclient.Constant;
import teprinciple.yang.amapinforwindowdemo.nettyclient.INettyClient;
import teprinciple.yang.amapinforwindowdemo.nettyclient.NettyClient;

/**
 * 通过netty的handler(xmlframedecoder)实现了粘包处理
 * 暂时不使用Handler的postDelayed，不开启定时刷新（手动向服务器查询数据）
 */

public class ChartActivity extends AppCompatActivity {

    private LineChart mASChart;
    private LineChart mACChart;
    private LineChart mRFChart;
    private LineChart mCSChart;
    private ArrayList<Entry> values;

    private TextView mtime_select;//启动时间选择器的按钮
    private TextView mdate_select;
    private int saved_year = 2018;
    private int saved_month = 4;
    private int saved_day = 1;
    private int saved_hour = 0;
    private int saved_minute = 0;
    private int saved_second = 0;

    private String dateText = "2018-4-1";
    private String timeText = "00:00:00";
    private String TAG = "ChartActivity";

    //private Handler mHandler = new Handler();//用于定时向服务器查询的handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        mASChart = (LineChart)findViewById(R.id.AngularSpeed_linechart);
        mACChart = (LineChart)findViewById(R.id.Acceleration_linechart);
        mRFChart = (LineChart)findViewById(R.id.RemainedFuel_linechart);
        mCSChart = (LineChart)findViewById(R.id.CurrentSpeed_linechart);
        mdate_select = (TextView)findViewById(R.id.date_select);
        mtime_select = (TextView)findViewById(R.id.time_select);


        initDate();
        initTime();
        initCharts();

        NettyClient.getInstance().addDataReceiveListener(new INettyClient.OnDataReceiveListener(){
            @Override
            public void onDataReceive(int mt,ChartDatas body){
                Log.d(TAG, "ChartActivity onDataReceive" + body);
             /*   String data[] = body.trim().split("#");
                Log.d("haha", "size:" + data.length);

                if(data.length != 1){//若data.length为1，代表只有一项，即服务器返回值为空

                    Log.d("haha", "flag为：" + data[0]);
                    if(data[0].equals("AngularSpeedApp"))//
                    {
                        Log.d("haha", "chart");
                        float buf_data;
                        values = new ArrayList<>();
                        int i = 1;
                        for(i = 1; i < data.length; i++){
                            buf_data = Float.parseFloat(data[i]);
                            values.add(new Entry(i-1, buf_data));
                        }

                        //设置一页最大显示个数为6，超出部分就滑动
                        float ratio = (float) i/(float) 6;//i表征了横轴点个数
                        //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
                        mASChart.zoom(ratio,1f,0,0);

                        LineDataSet dataSet = new LineDataSet(values, "Label");
                        LineData lineData = new LineData(dataSet);
                        mASChart.setData(lineData);
                        mASChart.invalidate();//refresh
                        //values.clear();
                    }
                    else if(data[0].equals("AccelerationApp")){
                        float buf_data;
                        values = new ArrayList<>();
                        int i = 1;
                        for(i = 1; i < data.length; i++){
                            buf_data = Float.parseFloat(data[i]);
                            values.add(new Entry(i-1, buf_data));
                        }

                        //设置一页最大显示个数为6，超出部分就滑动
                        float ratio = (float) i/(float) 6;//i表征了横轴点个数
                        //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
                        mACChart.zoom(ratio,1f,0,0);

                        LineDataSet dataSet = new LineDataSet(values, "Label");
                        LineData lineData = new LineData(dataSet);
                        mACChart.setData(lineData);
                        mACChart.invalidate();//refresh
                    }
                    else if(data[0].equals("RemainedFuelApp")){
                        float buf_data;
                        values = new ArrayList<>();
                        int i = 1;
                        for(i = 1; i < data.length; i++){
                            buf_data = Float.parseFloat(data[i]);
                            values.add(new Entry(i-1, buf_data));
                        }

                        //设置一页最大显示个数为6，超出部分就滑动
                        float ratio = (float) i/(float) 6;//i表征了横轴点个数
                        //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
                        mRFChart.zoom(ratio,1f,0,0);

                        LineDataSet dataSet = new LineDataSet(values, "Label");
                        LineData lineData = new LineData(dataSet);
                        mRFChart.setData(lineData);
                        mRFChart.invalidate();//refresh
                    }
                    else if(data[0].equals("CurrentSpeedApp")){
                        float buf_data;
                        values = new ArrayList<>();
                        int i = 1;
                        for(i = 1; i < data.length; i++){
                            buf_data = Float.parseFloat(data[i]);
                            values.add(new Entry(i-1, buf_data));
                        }

                        //设置一页最大显示个数为6，超出部分就滑动
                        float ratio = (float) i/(float) 6;//i表征了横轴点个数
                        //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
                        mCSChart.zoom(ratio,1f,0,0);

                        LineDataSet dataSet = new LineDataSet(values, "Label");
                        LineData lineData = new LineData(dataSet);
                        mCSChart.setData(lineData);
                        mCSChart.invalidate();//refresh
                    }
                    else if(data[0].equals("findLatestAngularSpeed")){
                        Log.d("haha", "latest:" + data[1]);
                    } else if(data[0].equals("findLatestAcceleration")){
                        Log.d("haha", "latest:" + data[1]);
                    } else if(data[0].equals("findLatestCurrentSpeed")){
                        Log.d("haha", "latest:" + data[1]);
                    } else if(data[0].equals("findLatestRemainedFuel")){
                        Log.d("haha", "latest:" + data[1]);
                    }
                }*/

            }
        });

        //mHandler.postDelayed(new QueryRunnable(), 1000);//查询最新的，定时刷新

    }

    /**
     * MPAndroidChart属性介绍：
     * https://blog.csdn.net/u014769864/article/details/71479591
     */
    private void initCharts(){
        mASChart.getDescription().setEnabled(false);
        mASChart.setNoDataText("选择时间/日期以获取角速度图表");
        Description descriptionAS = new Description();
        descriptionAS.setText("单位：弧度/秒");
        //设置描述信息
        mASChart.setDescription(descriptionAS);
        mASChart.getLegend().setEnabled(false);

        mACChart.getDescription().setEnabled(false);
        mACChart.setNoDataText("选择时间/日期以获取加速度图表");
        Description descriptionAC = new Description();
        descriptionAC.setText("单位：米/二次方秒");
        //设置描述信息
        mACChart.setDescription(descriptionAC);
        mACChart.getLegend().setEnabled(false);

        mRFChart.getDescription().setEnabled(false);
        mRFChart.setNoDataText("选择时间/日期以获取剩余油量图表");
        Description descriptionRF = new Description();
        descriptionRF.setText("单位：升");
        //设置描述信息
        mRFChart.setDescription(descriptionRF);
        mRFChart.getLegend().setEnabled(false);

        mCSChart.getDescription().setEnabled(false);
        mCSChart.setNoDataText("选择时间/日期以获取当前线速度图表");
        Description descriptionCS = new Description();
        descriptionCS.setText("单位：米/秒");
        //设置描述信息
        mCSChart.setDescription(descriptionCS);
        mCSChart.getLegend().setEnabled(false);


  /*      Legend legend = mASChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        //设置所有图例位置排序方向
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //设置图例的形状 有圆形、正方形、线
        legend.setForm(Legend.LegendForm.CIRCLE);*/



    }




















 /*   查询最新的，定时刷新
      class QueryRunnable implements Runnable{

        @Override
        public void run() {
            NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<command><user_id>001</user_id><passwd>aaa</passwd><content>findLatestAngularSpeed</content><iot_Device_id>00001</iot_Device_id><startStamp>empty</startStamp><stopStamp>empty</stopStamp><someDay>2018-3-16</someDay></command>", 0);
            NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<command><user_id>001</user_id><passwd>aaa</passwd><content>findLatestAcceleration</content><iot_Device_id>00001</iot_Device_id><startStamp>empty</startStamp><stopStamp>empty</stopStamp><someDay>2000-1-1</someDay></command>", 0);
            NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<command><user_id>001</user_id><passwd>aaa</passwd><content>findLatestCurrentSpeed</content><iot_Device_id>00001</iot_Device_id><startStamp>empty</startStamp><stopStamp>empty</stopStamp><someDay>2000-1-1</someDay></command>", 0);
            NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<command><user_id>001</user_id><passwd>aaa</passwd><content>findLatestRemainedFuel</content><iot_Device_id>00001</iot_Device_id><startStamp>empty</startStamp><stopStamp>empty</stopStamp><someDay>2000-1-1</someDay></command>", 0);
            //每隔1s执行一次Run方法
            mHandler.postDelayed(this, 1000);
            Log.d("haha", "刷新");
        }
    }*/

    private void initDate() {//dialogpicker
        mdate_select = (TextView) findViewById(R.id.date_select);
        mdate_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //给出一个默认值或者是回去mTimeContent中的String格式化

                PickDatePopupWindow pickDatePopupWindow = new PickDatePopupWindow(ChartActivity.this, saved_year, saved_month, saved_day);
                pickDatePopupWindow.setDatePickListener(new PickDatePopupWindow.DatePickListener() {
                    @Override
                    public void onPickDate(Integer year, Integer month, Integer day) {
                        dateText = String.format("%d-%02d-%02d", year, month, day);
                        saved_day = day;
                        saved_month = month;
                        saved_year = year;
                        Log.d("haha", dateText);
                        mdate_select.setText(dateText);
                        NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<command><user_id>001</user_id><passwd>aaa</passwd><content>AngularSpeedApp</content><iot_Device_id>00001</iot_Device_id><startStamp>empty</startStamp><stopStamp>empty</stopStamp><someDay>2018-4-11</someDay></command>", 0);
                        NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<command><user_id>001</user_id><passwd>aaa</passwd><content>AccelerationApp</content><iot_Device_id>00001</iot_Device_id><startStamp>empty</startStamp><stopStamp>empty</stopStamp><someDay>2018-4-11</someDay></command>", 0);
                        NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<command><user_id>001</user_id><passwd>aaa</passwd><content>CurrentSpeedApp</content><iot_Device_id>00001</iot_Device_id><startStamp>empty</startStamp><stopStamp>empty</stopStamp><someDay>2018-4-11</someDay></command>", 0);
                        NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<command><user_id>001</user_id><passwd>aaa</passwd><content>RemainedFuelApp</content><iot_Device_id>00001</iot_Device_id><startStamp>empty</startStamp><stopStamp>empty</stopStamp><someDay>2018-4-11</someDay></command>", 0);

                    }
                });
                pickDatePopupWindow.showAtLocation(mdate_select, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    private void initTime(){
        mtime_select = (TextView) findViewById(R.id.time_select);
        mtime_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //给出一个默认值或者是回去mTimeContent中的String格式化

                PickTimePopupWindow pickTimePopupWindow = new PickTimePopupWindow(ChartActivity.this, saved_hour, saved_minute, saved_second);
                pickTimePopupWindow.setDatePickListener(new PickTimePopupWindow.DatePickListener() {
                    @Override
                    public void onPickDate(Integer hour, Integer minute, Integer second) {
                        timeText = String.format("%d-%02d-%02d", hour, minute, second);
                        saved_second = second;
                        saved_minute = minute;
                        saved_hour = hour;
                        Log.d("haha", timeText);
                        mtime_select.setText(timeText);
                        NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<command><user_id>001</user_id><passwd>aaa</passwd><content>AngularSpeedApp</content><iot_Device_id>0</iot_Device_id><startStamp>empty</startStamp><stopStamp>empty</stopStamp><someDay>2000-1-1</someDay></command>", 0);
                        NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<command><user_id>001</user_id><passwd>aaa</passwd><content>AccelerationApp</content><iot_Device_id>0</iot_Device_id><startStamp>empty</startStamp><stopStamp>empty</stopStamp><someDay>2000-1-1</someDay></command>", 0);
                        NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<command><user_id>001</user_id><passwd>aaa</passwd><content>CurrentSpeedApp</content><iot_Device_id>0</iot_Device_id><startStamp>empty</startStamp><stopStamp>empty</stopStamp><someDay>2000-1-1</someDay></command>", 0);
                        NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<command><user_id>001</user_id><passwd>aaa</passwd><content>RemainedFuelApp</content><iot_Device_id>0</iot_Device_id><startStamp>empty</startStamp><stopStamp>empty</stopStamp><someDay>2000-1-1</someDay></command>", 0);

                    }
                });
                pickTimePopupWindow.showAtLocation(mdate_select, Gravity.BOTTOM, 0, 0);
            }
        });
    }
}
