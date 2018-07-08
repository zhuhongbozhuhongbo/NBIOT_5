package teprinciple.yang.amapinforwindowdemo.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.zyyoona7.lib.EasyPopup;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

import teprinciple.yang.amapinforwindowdemo.MarkersManager;
import teprinciple.yang.amapinforwindowdemo.R;
import teprinciple.yang.amapinforwindowdemo.adapter.InfoWinAdapter;
import teprinciple.yang.amapinforwindowdemo.dom4j.ChartDatas;
import teprinciple.yang.amapinforwindowdemo.nettyclient.Constant;
import teprinciple.yang.amapinforwindowdemo.nettyclient.INettyClient;
import teprinciple.yang.amapinforwindowdemo.nettyclient.NettyClient;
import teprinciple.yang.amapinforwindowdemo.utils.CheckPermissionsActivity;
import teprinciple.yang.amapinforwindowdemo.utils.GlobalStateManager;
import teprinciple.yang.amapinforwindowdemo.utils.Gps;
import teprinciple.yang.amapinforwindowdemo.utils.PositionUtil;


/**
 * 包含定位的版本见D:\PHOTO\AMapInfoWindowDemo-master
 * 本活动使用Handler的postDelayed实现定时刷新（向服务器查询数据）
 */

public class MapActivity extends CheckPermissionsActivity implements AMap.OnMapClickListener, AMap.OnMarkerClickListener, View.OnClickListener {

    private MapView mapView;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient;
    //声明AMapLocationClientOption对象
    private AMapLocationClientOption mLocationOption;
    private AMap aMap;
    private CameraUpdate cameraUpdate;
    private UiSettings uiSettings;
    private InfoWinAdapter adapter;
    private Marker oldMarker;
    private LatLng myLatLng;
    private EasyPopup mAbovePop;
    private LinearLayout mabovePopWakeUp;
    private LinearLayout mselect_projectdevice;
    private LinearLayout mhome_page_4;
    private LinearLayout mhome_page_5;
    private TextView msearch_tv;
    private String TAG = "MapAcitivity";

    private Handler mHandler = new Handler();

    /**marker
     * 管理，拟每次开启从服务器获取marker位置，本地不做缓存
     * @param savedInstanceState
     */




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initView();

        //在执行onCreateView时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mapView.onCreate(savedInstanceState);
        initMap();
        initMarkers();

        initAbovePop();//初始化popupWindow相关，注意其中有setOnClickListener()和findViewById()

        NettyClient.getInstance().addDataReceiveListener(new INettyClient.OnDataReceiveListener() {
            @Override
            public void onDataReceive(int mt, ChartDatas body) {
                Log.e("haha", "MapActivity收到：" + body.getType() + ";" );
                /*Log.d("haha", "MapActivity onDataReceive" + body);
                String data[] = body.getType();

                if (data.length != 1) {//若data.length为1，代表只有一项，即服务器返回值为空

                    if (data[0].equals("findLatestBeiDou"))//data[0]为标志量  data[1]、data[2]为经纬度、data[3]为marker的title
                    {
                        MarkersManager.clearMarkersByList(aMap, MarkersManager.list4);
                        Gps convertPsn = PositionUtil.gps84_To_Gcj02(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
                        LatLng latLng = new LatLng(convertPsn.getWgLat(), convertPsn.getWgLon());
                        MarkersManager.addMarker(aMap, latLng, "title", MarkersManager.list4);//list1 list2 list3用于存储测试用marker

                        Log.d("haha", "body:" + data[2] + "," + data[1]);

                    }
                }*/
            }
        });

                //从服务器取出marker相关信息
//        NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<command><user_id>001</user_id><passwd>aaa</passwd><content>findLatestBeiDou" +
//                "</content><iot_Device_id>00001</iot_Device_id><startStamp>empty</startStamp><stopStamp>empty</stopStamp><someDay>empty</someDay></command>", 0);
        mHandler.postDelayed(new QueryRunnable(), 10);
    }

    class QueryRunnable implements Runnable{

        @Override
        public void run() {
            NettyClient.getInstance().sendMessage(Constant.MSG_TYPE, "<query><userid>001</userid><passwd>aaa</passwd><field>GPSInformation</field><type>DDI</type>" +
                    "<querymode>findByIdAndHour</querymode><p0>6</p0><p1>2018-06-3</p1><p2>empty</p2><p3>10:00:00</p3><p4>10:30:00</p4><p5>null</p5></query>", 0);
            //每隔1s执行一次Run方法
            mHandler.postDelayed(this, 3000);
            Log.d("haha", "MapActivity刷新");
        }
    }

    private void initView() {
        mapView = (MapView) initV(R.id.home_page_mapview_0);//地图
        msearch_tv = findViewById(R.id.search_tv);
        mabovePopWakeUp = (LinearLayout)findViewById(R.id.abovePopWakeUp);//唤醒popupWindow的按键
        mabovePopWakeUp.setOnClickListener(this);
        mselect_projectdevice = (LinearLayout)findViewById(R.id.select_projectdevice);
        mselect_projectdevice.setOnClickListener(this);
        mhome_page_4 = (LinearLayout)findViewById(R.id.home_page_4);
        mhome_page_5 = (LinearLayout)findViewById(R.id.home_page_5);
        mhome_page_4.setOnClickListener(this);
        mhome_page_5.setOnClickListener(this);
        msearch_tv.setText("请选择项目地区");

    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.abovePopWakeUp){
            showAbovePop(v);
            /*
            打算此处后期改为使用jdaddressselector项目的地址选择
            BottomDialog dialog = new BottomDialog(MainActivity.this);
            dialog.setOnAddressSelectedListener(MainActivity.this);
            dialog.show();*/
        }else if(v.getId() == R.id.abovePopRl){
            mAbovePop.dismiss();
        }else if(v.getId() == R.id.abovePopText1){
            Log.d("haha", "text1");
            MarkersManager.flag1 = !MarkersManager.flag1;
            MarkersManager.ShowHideMarkers(MarkersManager.list1, MarkersManager.flag1);
            //改变可视区域为指定位置
            //CameraPosition4个参数分别为位置，缩放级别，目标可视区域倾斜度，可视区域指向方向（正北逆时针算起，0-360）
            cameraUpdate= CameraUpdateFactory.newCameraPosition(new CameraPosition(CommonPosition.match("BeiJing"),8,0,30));
            aMap.moveCamera(cameraUpdate);//地图移向指定区域
            mAbovePop.dismiss();
        }else if(v.getId() == R.id.abovePopText2){
            Log.d("haha", "text2");
            MarkersManager.flag2 = !MarkersManager.flag2;
            MarkersManager.ShowHideMarkers(MarkersManager.list2, MarkersManager.flag2);
             cameraUpdate= CameraUpdateFactory.newCameraPosition(new CameraPosition(CommonPosition.match("TianJin"),8,0,30));
            aMap.moveCamera(cameraUpdate);//地图移向指定区域
            mAbovePop.dismiss();
        }else if(v.getId() == R.id.abovePopText3){
            Log.d("haha", "text3");
            MarkersManager.flag3 = !MarkersManager.flag3;
            MarkersManager.ShowHideMarkers(MarkersManager.list3, MarkersManager.flag3);
            cameraUpdate= CameraUpdateFactory.newCameraPosition(new CameraPosition(CommonPosition.match("ShiJiaZhuang"),8,0,30));
            aMap.moveCamera(cameraUpdate);//地图移向指定区域
            mAbovePop.dismiss();
        }else if(v.getId() == R.id.abovePopText4){
            Log.d("haha", "text4");
            mAbovePop.dismiss();
        }else if(v.getId() == R.id.abovePopText5){
            Log.d("haha", "text5");
            mAbovePop.dismiss();
        }else if(v.getId() == R.id.abovePopText6){
            Log.d("haha", "text6");
            mAbovePop.dismiss();
        }else if(v.getId() == R.id.abovePopText7){
            Log.d("haha", "text7");
            mAbovePop.dismiss();
        }else if(v.getId() == R.id.select_projectdevice){
            if(GlobalStateManager.projectdevice == true){//此时主页为“项目管理”
                Intent intent = new Intent("com.example.nbiot_5.ACTION_START");
                intent.addCategory("com.example.nbiot_5.LOCATION_SELECT_CATEGORY");
                startActivity(intent);
            }else if(GlobalStateManager.projectdevice == false){//此时主页为“设备管理
                Intent intent = new Intent("com.example.nbiot_5.ACTION_START");
                intent.addCategory("com.example.nbiot_5.DEVICE_FIND_CATEGORY");
                startActivity(intent);
            }

        }

        else if(v.getId() == R.id.home_page_4){//代表点击了界面上的“项目管理”，可以考虑 在按下之后应重新从服务器获取markers信息，不过目前是APP定时查询，所以不需要给按键加入这个功能
            mhome_page_5.setBackground(getResources().getDrawable(R.drawable.tab_unchecked));
            mhome_page_4.setBackground(getResources().getDrawable(R.drawable.tab_checked));
            msearch_tv.setText("请选择项目地区");
            GlobalStateManager.projectdevice = true;//true代表project
        }else if(v.getId() == R.id.home_page_5){
            mhome_page_5.setBackground(getResources().getDrawable(R.drawable.tab_checked));
            mhome_page_4.setBackground(getResources().getDrawable(R.drawable.tab_unchecked));
            msearch_tv.setText("请输入关键字进行搜索");
            GlobalStateManager.projectdevice = false;//false代表project
        }
    }


    /**
     * 初始化地图
     */
    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            uiSettings = aMap.getUiSettings();
            aMap.setOnMapClickListener(this);
        }
        uiSettings.setZoomControlsEnabled(false); //隐藏缩放控件
        //自定义InfoWindow
        aMap.setOnMarkerClickListener(this);
        adapter = new InfoWinAdapter();
        aMap.setInfoWindowAdapter(adapter);



    }

    private void initMarkers(){
        MarkersManager.addTestMarkerToMap(aMap, Constant.ID000,"太湖0", MarkersManager.list1);
        MarkersManager.addTestMarkerToMap(aMap, Constant.ID001,"太湖1", MarkersManager.list1);
        MarkersManager.addTestMarkerToMap(aMap, Constant.ID002,"太湖2", MarkersManager.list1);
        MarkersManager.addTestMarkerToMap(aMap, Constant.ID003,"太湖3", MarkersManager.list2);
        MarkersManager.addTestMarkerToMap(aMap, Constant.ID004,"太湖4", MarkersManager.list2);
        MarkersManager.addTestMarkerToMap(aMap, Constant.ID005,"太湖5", MarkersManager.list2);
        MarkersManager.addTestMarkerToMap(aMap, Constant.ID006,"太湖6", MarkersManager.list2);
        MarkersManager.addTestMarkerToMap(aMap, Constant.ID007,"太湖7", MarkersManager.list2);
        MarkersManager.addTestMarkerToMap(aMap, Constant.ID008,"太湖8", MarkersManager.list3);
        //MarkersManager.addTestMarkerToMap(aMap, Constant.ID009,"太湖9", MarkersManager.list3);
        MarkersManager.addTestMarkerToMap(aMap, Constant.ID010,"太湖10", MarkersManager.list3);
    }




    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume(); //管理地图的生命周期
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause(); //管理地图的生命周期
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy(); //管理地图的生命周期
    }

    //地图的点击事件
    @Override
    public void onMapClick(LatLng latLng) {
        //点击地图上没marker 的地方，隐藏inforwindow
        if (oldMarker != null) {
            oldMarker.hideInfoWindow();
            oldMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_normal));
        }
    }

    //maker的点击事件
    @Override
    public boolean onMarkerClick(Marker marker) {

        if (!marker.getPosition().equals(myLatLng)){ //点击的marker不是自己位置的那个marker
            if (oldMarker != null) {
                oldMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_normal));
            }
            oldMarker = marker;
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_selected));
        }

        return false; //返回 “false”，除定义的操作之外，默认操作也将会被执行
    }



    private void initAbovePop() {
        mAbovePop = new EasyPopup(this)
                .setContentView(R.layout.window_popup)
                .setFocusAndOutsideEnable(true)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.e("haha", "onDismiss: mAbovePop");
                    }
                })
                .createPopup();
        RelativeLayout Rl0 = mAbovePop.getView(R.id.abovePopRl);
        TextView tv1 = mAbovePop.getView(R.id.abovePopText1);
        TextView tv2 = mAbovePop.getView(R.id.abovePopText2);
        TextView tv3 = mAbovePop.getView(R.id.abovePopText3);
        TextView tv4 = mAbovePop.getView(R.id.abovePopText4);
        TextView tv5 = mAbovePop.getView(R.id.abovePopText5);
        TextView tv6 = mAbovePop.getView(R.id.abovePopText6);
        TextView tv7 = mAbovePop.getView(R.id.abovePopText7);

        Rl0.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
    }

    private void showAbovePop(View view) {
        mAbovePop.showAtAnchorView(view, VerticalGravity.ABOVE, HorizontalGravity.CENTER);
    }




}
