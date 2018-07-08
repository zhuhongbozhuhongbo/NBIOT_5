package teprinciple.yang.amapinforwindowdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;

import teprinciple.yang.amapinforwindowdemo.base.BaseApplication;
import teprinciple.yang.amapinforwindowdemo.R;


/**
 * Created by Teprinciple on 2016/8/23.
 * 地图上自定义的infowindow的适配器
 */

/**
 * Caused by: android.util.AndroidRuntimeException: Calling startActivity() from outside of an Activity context
 * requires the FLAG_ACTIVITY_NEW_TASK flag. Is this really what you want?
 Context中有一个startActivity方法，Activity继承自Context，重载了startActivity方法。如果使用Activity的startActivity
 方法，不会有任何限制，而如果使用Context的startActivity方法的话，就需要开启一个新的task，遇到上面那个异常的，都是因为
 使用了Context的startActivity方法。解决办法是，加一个flag。
 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 */
public class InfoWinAdapter implements AMap.InfoWindowAdapter, View.OnClickListener {
    private Context mContext = BaseApplication.getContext();
    private LatLng latLng;

    private String agentName;

    @Override
    public View getInfoWindow(Marker marker) {
        View view = null;
        initData(marker);
        if("P" == Matching.match(agentName)){//工程类型
          view  = initView_project();
        }else if("D" == Matching.match(agentName)){//设备类型
            view = initView_device();
        }
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    private void initData(Marker marker) {
        latLng = marker.getPosition();
        agentName = marker.getTitle();
    }

    @NonNull
    private View initView_device(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_project_window, null);
        Button mpj_wd_btn0 = (Button) view.findViewById(R.id.project_window_button0);
        TextView mpj_wd_txt = (TextView) view.findViewById(R.id.project_window_text);
        mpj_wd_txt.setText(agentName);
        mpj_wd_btn0.setOnClickListener(this);
        return view;
    }

    @NonNull
    private View initView_project() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_device_window, null);
        Button mdv_wd_btn0 = (Button) view.findViewById(R.id.device_window_button_0);
        Button mdv_wd_btn1 = (Button) view.findViewById(R.id.device_window_button_1);
        TextView mdv_wd_txt = (TextView) view.findViewById(R.id.device_window_text);
        mdv_wd_txt.setText(agentName);
        mdv_wd_btn0.setOnClickListener(this);
        mdv_wd_btn1.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.device_window_button_0){//设备
            Log.d("haha", "dev" + agentName);
            Intent intent = new Intent("com.example.nbiot_5.ACTION_START");
            intent.addCategory("com.example.nbiot_5.DEVICE_DETAILS_CATEGORY");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            switch (Matching.convert(agentName)){
                case 0:
                    Log.d("btn0", "0");
                    break;
                case 1:
                    Log.d("btn0", "1");
                    break;
                case 2:
                    Log.d("btn0", "2");
                    break;
                case 3:
                    Log.d("btn0", "3");
                    break;
                case 4:
                    Log.d("btn0", "4");
                    break;
                case 5:
                    Log.d("btn0", "5");
                    break;
                case 6:
                    Log.d("btn0", "6");
                    break;
                case 7:
                    Log.d("btn0", "7");
                    break;
            }
            mContext.startActivity(intent);
        }
        else if(id == R.id.device_window_button_1){
            Intent intent = new Intent("com.example.nbiot_5.ACTION_START");
            intent.addCategory("com.example.nbiot_5.DEVICE_FIND_CATEGORY");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.d("haha", "dev" + agentName);
            switch (Matching.convert(agentName)){
                case 0:
                    Log.d("btn1", "0");
                    break;
                case 1:
                    Log.d("btn1", "1");
                    break;
                case 2:
                    Log.d("btn1", "2");
                    break;
                case 3:
                    Log.d("btn1", "3");
                    break;
                case 4:
                    Log.d("btn1", "4");
                    break;
                case 5:
                    Log.d("btn1", "5");
                    break;
                case 6:
                    Log.d("btn1", "6");
                    break;
                case 7:
                    Log.d("btn1", "7");
                    break;
            }

            mContext.startActivity(intent);
        }
        else if(id == R.id.project_window_button0){
            Intent intent = new Intent("com.example.nbiot_5.ACTION_START");
            intent.addCategory("com.example.nbiot_5.PROJECT_CATEGORY");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            switch (Matching.convert(agentName)){
                case 0:
                    Log.d("pro", "0");
                    //intent.putExtra()
                    break;
                case 1:
                    Log.d("pro", "1");
                    //intent.putExtra()
                    break;
                case 2:
                    Log.d("pro", "2");
                    break;
                case 3:
                    Log.d("pro", "3");
                    break;
                case 4:
                    Log.d("pro", "4");
                    break;
                case 5:
                    Log.d("pro", "5");
                    break;
                case 6:
                    Log.d("pro", "6");
                    break;
                case 7:
                    Log.d("pro", "7");
                    break;
                    default:
                        break;

            }

            mContext.startActivity(intent);
        }
    }

}
