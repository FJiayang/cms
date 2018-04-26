package com.fjy.spring.untils;

import javax.servlet.http.HttpServletRequest;

public class GetIPAddrUtil {
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" Proxy-Client-IP ");
            //System.out.println("【Proxy-Client-IP】");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" WL-Proxy-Client-IP ");
            //System.out.println("【WL-Proxy-Client-IP】");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            //System.out.println("【unknown】");
        }
        return ip;
    }
}
