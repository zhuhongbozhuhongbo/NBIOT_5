package teprinciple.yang.amapinforwindowdemo;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by 朱宏博 on 2018/4/10.
 */

/**不同list存储不同省份
 * 相同list中依靠marker的Object属性进行工程、设备区别
 */
public class MarkersManager {
    /**
     * 定时从服务器动态获取marker点并更新（此时为删除，添加）
     * 考虑到节省内存，不应采用静态变量维护markers列表
     * 但考虑到后续Activity需要查询markers，在markers不多时 采取静态变量存储markers的方案优于用数据库存储markers
     * **/
    public static ArrayList<Marker> list1 = new ArrayList<>();
    public static ArrayList<Marker> list2 = new ArrayList<>();
    public static ArrayList<Marker> list3 = new ArrayList<>();
    public static ArrayList<Marker> list4 = new ArrayList<>();

    public static boolean flag1 = true;//存储地区一当前markers显示状态，true代表显示，false代表隐藏
    public static boolean flag2 = true;
    public static boolean flag3 = true;
    public static boolean flag4 = true;
    public static boolean flag5 = true;
    public static boolean flag6 = true;
    public static boolean flag7 = true;
    public static boolean flag8 = true;
    public static boolean flag9 = true;
    public static boolean flag10 = true;
    public static boolean flag11 = true;
    public static boolean flag12 = true;
    public static boolean flag13 = true;
    public static boolean flag14 = true;
    public static boolean flag15 = true;
    public static boolean flag16 = true;
    public static boolean flag17 = true;
    public static boolean flag18 = true;
    public static boolean flag19 = true;
    public static boolean flag20 = true;
    public static boolean flag21 = true;

    public static void addMarkerToMap(AMap aMap, LatLng latLng, String title, ArrayList<Marker> list) {
        Marker marker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(latLng)
                .title(title)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_normal))
        );
        list.add(marker);
    }

    public static void addTestMarkerToMap(AMap aMap, LatLng latLng, String title, ArrayList<Marker> list) {
        Marker marker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(latLng)
                .title(title)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_test))
        );
        list.add(marker);
    }

    public static void addMarker(AMap aMap, LatLng latLng, String title, ArrayList<Marker> list){
        MarkerOptions options = new MarkerOptions();
        options.title(title).position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_normal));
        Marker marker = aMap.addMarker(options);
//这行关键，标记Marker的类型xxx
        marker.setObject("device");
        list.add(marker);
        aMap.invalidate();
    }

    public static void clearMarkersByList(AMap aMap, ArrayList<Marker> list){//删除某个list的所有markers
        for (int i = 0; i < list.size(); i++) {
            Marker marker = list.get(i);
            marker.remove();//移除当前Marker
        }
        aMap.invalidate();//刷新地图
    }

    //删除指定Marker
    public static void clearMarkersByFlag(AMap aMap, ArrayList<Marker> list, String flag) {//此处flag是指设置marker时设置的:marker.setObject()
        /*获取地图上所有Marker
        ArrayList<Marker> mapScreenMarkers = aMap.getMapScreenMarkers();*/
        for (int i = 0; i < list.size(); i++) {
            Marker marker = list.get(i);
            if (marker.getObject().toString().equals(flag)) {
                marker.remove();//移除当前Marker
            }
        }
        aMap.invalidate();//刷新地图
    }

    public static void ShowHideMarkers(ArrayList<Marker> list, boolean flag){//show or hide the markers    true:show    false:hide
        for(int i = 0; i < list.size(); i++){
            list.get(i).setVisible(flag);
        }
    }

    public static void ShowMarkers(ArrayList<Marker> list){//show or hide the markers    true:show    false:hide
        for(int i = 0; i < list.size(); i++){
            list.get(i).setVisible(true);
        }
    }

    public static void HideMarkers(ArrayList<Marker> list){
        for(int i = 0; i < list.size(); i++){
            list.get(i).setVisible(false);
        }
    }

}
