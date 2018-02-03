package com.fjy.spring.untils;

import java.text.DecimalFormat;

public class FormatFileSizeUtil {
    public static String GetFileSize(long sizes){
        String size = "";
        if(sizes!=0){
            long fileS = sizes;
            DecimalFormat df = new DecimalFormat("#.00");
            if (fileS < 1024) {
                size = df.format((double) fileS) + "BT";
            } else if (fileS < 1048576) {
                size = df.format((double) fileS / 1024) + "KB";
            } else if (fileS < 1073741824) {
                size = df.format((double) fileS / 1048576) + "MB";
            } else {
                size = df.format((double) fileS / 1073741824) +"GB";
            }
        }else{
            size = "非法!";
        }
        return size;
    }
}
