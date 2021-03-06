package teprinciple.yang.amapinforwindowdemo.homepage;

import com.amap.api.maps2d.model.LatLng;

/**
 * Created by 朱宏博 on 2018/5/17.
 */

public class CommonPosition {
    public static LatLng match(String title){

        LatLng result;
        switch (title) {
            case "BeiJing":
            result = new LatLng(39.91667, 116.41667);
            break;

            case "ShangHai":
            result = new LatLng(34.50000, 121.43333);
            break;

            case "TianJin":
             result = new LatLng(39.13333, 117.20000);
            break;

            case "XiangGang":
             result = new LatLng(22.20000, 114.10000);
            break;

            case "GuangZhou":
             result = new LatLng(23.16667, 113.23333);
            break;

            case "HangZhou":
                result = new LatLng(30.26667,120.20000);
                break;

            case "ChongQing":
                result = new LatLng(29.56667,106.45000);
                break;

            case "FuZhou":
                result= new LatLng(26.08333,119.30000);
                break;

            case "LanZhou":
                result= new LatLng(36.03333,103.73333);
                break;

            case "GuiYang":
                result= new LatLng(26.56667,106.71667);
                break;

            case "ChangSha":
                result= new LatLng(28.21667,113.00000);
                break;

            case "NanJing":
                result= new LatLng(32.05000,118.78333);
                break;

            case "NanChang":
                result= new LatLng(28.68333,115.90000);
                break;

            case "ShiJiaZhuang":
                result = new LatLng(38.03333,114.48333);
                break;

            default:
                result = new LatLng(39.91667,116.41667);


        }

        return result;

        /*LatLng BeiJing = new LatLng(116.41667, 39.91667);
            LatLng ShangHai = new LatLng(121.43333, 34.50000);
            LatLng TianJin = new LatLng(117.20000, 39.13333);
            LatLng XiangGang = new LatLng(114.10000, 22.20000);
            LatLng GuangZhou = new LatLng(113.23333, 23.16667);
            LatLng Hangzhou = new LatLng(120.20000, 30.26667);
            LatLng ChongQing = new LatLng(106.45000, 29.56667);
            LatLng FuZhou = new LatLng(119.30000, 26.08333);
            LatLng LanZhou = new LatLng(103.73333, 36.03333);
            LatLng GuiYang = new LatLng(106.71667, 26.56667);
            LatLng ChangSha = new LatLng(113.00000, 28.21667);
            LatLng NanJing = new LatLng(118.78333, 32.05000);
            LatLng NanChang = new LatLng(115.90000, 28.68333);
            */
/*
        天津市区经纬度:(117.20000,39.13333)

                香港经纬度:(114.10000,22.20000)
                广州经纬度:(113.23333,23.16667)
                杭州经纬度:(120.20000,30.26667)
                重庆市区经纬度:(106.45000,  29.56667)
                福州经纬度:(119.30000,26.08333)
                兰州经纬度:(103.73333,36.03333)
                贵阳经纬度:(106.71667,26.56667)
                长沙经纬度:(113.00000,28.21667)
                南京经纬度:(118.78333,32.05000)
                南昌经纬度:(115.90000,28.68333)
                沈阳经纬度:(123.38333,41.80000)
                太原经纬度:(112.53333,37.86667)
                成都经纬度:(104.06667,30.66667)
                拉萨经纬度:(91.00000,29.60000)
                乌鲁木齐经纬度:(87.68333,43.76667)
                昆明经纬度:(102.73333,25.05000)
                西安经纬度:(108.95000,34.26667)
                西宁经纬度:(101.75000,36.56667)
                银川经纬度:(106.26667,38.46667)
                哈尔滨经纬度:(126.63333,45.75000)
                长春经纬度:(125.35000,43.88333)
                武汉经纬度:(114.31667,30.51667)
                郑州经纬度:(113.65000,34.76667)
                石家庄经纬度:(114.48333,38.03333)
                海口经纬度:(110.35000,20.01667)
                澳门经纬度:(113.50000,22.20000)
                安徽省 合肥 北纬31.52 东经117.17
                澳门   澳门市 北纬21.33 东经115.07
                北京市 北京市 北纬39.55 东经116.24
                福建省 福州 北纬26.05 东经119.18
                甘肃省 兰州 北纬36.04 东经103.51
                广东省 广州 北纬23.08 东经113.14
                广西自治区 南宁 北纬22.48 东经108.19
                贵州省 贵阳 北纬26.35 东经106.42
                海南省 海口 北纬20.02 东经110.20
                河北省 石家庄 北纬38.02 东经114.30
                河北省 蒿城 北纬38.02 东经114.50
                河南省 郑州 北纬34.46 东经11340
                黑龙江省 哈尔滨 北纬45.44 东经126.36
                湖北省 武汉 北纬30.35 东经114.17
                湖南省 长沙 北纬28.12 东经112.59
                吉林省 长春 北纬43.54 东经125.19
                江苏省 南京 北纬32.03 东经118.46
                江苏省 徐州 北纬34.15 东经117.11
                江西省 南昌 北纬28.40 东经115.55
                辽宁省 沈阳 北纬41.48 东经123.25
                内自治区 呼和浩特 北纬40.48 东经111.41
                宁夏自治区 银川 北纬38.27 东经106.16
                青海省 西宁 北纬36.38 东经101.48
                山东省 济南 北纬36.40 东经117.00
                山西省 太原 北纬37.54 东经112.33
                山西省 孝义 北纬37.08 东经111.48
                陕西省 西安 北纬34.17 东经108.57
                上海市 上海市 北纬31.14 东经121.29
                四川省 成都 北纬30.40 东经104.04
                台湾省 台北市 北纬25.03 东经121.30
                天津市 天津市 北纬39.02 东经117.12
                西藏自治区 拉萨 北纬29.39 东经91.08
                香港 香港市 北纬21.23 东经115.12
                新疆自治区 乌鲁木齐 北纬43.45 东经87.36
                云南省 昆明 北纬25.04 东经102.42
                浙江省 杭州 北纬30.16 东经120.10
                重庆市 重庆市 北纬29.35 东经106.33*/
        }

}

