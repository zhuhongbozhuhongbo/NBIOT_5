package teprinciple.yang.amapinforwindowdemo.adapter;

/**
 * Created by 朱宏博 on 2018/4/2.
 */

/**
 * P代表工程物联网下位机，D代表设备物联网下位机，两种类型下位机ID分别独立从0开始递增
 */
public class Matching {
    public static String match(String title){
        switch (title){
            case "太湖0":
                return "P";
            case "太湖1":
                return "P";
            case "太湖2":
                return "P";
            case "太湖3":
                return "P";
            case "太湖4":
                return "P";
            case "太湖5":
                return "P";
            case "太湖6":
                return "P";
            case "太湖7":
                return "D";
            case "太湖8":
                return "D";
            case "太湖9":
                return "D";
            case "太湖10":
                return "D";
            case "太湖11":
                return "D";
            case "太湖12":
                return "D";
            case "太湖13":
                return "D";
            case "太湖14":
                return "D";
            case "太湖15":
                return "D";
            case "灰罐车":
                return "D";
            default:
                return "EMPTY";
        }
    }

    public static int convert(String title){
        switch(title){
            case "太湖0":
                return 0;
            case "太湖1":
                return 1;
            case "太湖2":
                return 2;
            case "太湖3":
                return 3;
            case "太湖4":
                return 4;
            case "太湖5":
                return 5;
            case "太湖6":
                return 6;
            case "太湖7":
                return 7;
            case "太湖8":
                return 8;
            case "太湖9":
                return 9;
            case "太湖10":
                return 10;
            case "太湖11":
                return 11;
            case "太湖12":
                return 12;
            case "太湖13":
                return 13;
            case "太湖14":
                return 14;
            case "太湖15":
                return 15;
            default:
                return 999;
        }
    }
}
