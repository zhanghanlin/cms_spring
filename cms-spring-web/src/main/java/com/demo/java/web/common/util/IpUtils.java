package com.demo.java.web.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public abstract class IpUtils {

    /**
     * 根据request返回ip地址.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    public static String getRequestHeaderIpAddr(HttpServletRequest request) {
        if (null == request) {
            return "unkown ip";
        }
        String ip = request.getHeader("x-forwarded-for");
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return getLegitimateIP(ip.split(","));
    }

    private final static Pattern IP_PATTERN = Pattern
            .compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");

    /**
     * 得到合法ip地址
     * 
     * @param ips
     * @return
     */
    private static String getLegitimateIP(String... ips) {
        Matcher matcher; // 以验证127.400.600.2为例
        if ((null != ips) && (ips.length > 0)) {
            for (String ip : ips) {
                matcher = IP_PATTERN.matcher(ip);
                if (matcher.matches()) {
                    return ip;
                }
            }
        }
        return null;
    }

    /**
     * 整数转成ip地址.
     * 
     * @param ipLong
     * @return
     */
    public static String long2Ip(long ipLong) {
        // long ipLong = 1037591503;
        long mask[] = { 0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000 };
        long num = 0;
        StringBuffer ipInfo = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            num = (ipLong & mask[i]) >> (i * 8);
            if (i > 0) {
                ipInfo.insert(0, ".");
            }
            ipInfo.insert(0, Long.toString(num, 10));
        }
        return ipInfo.toString();
    }

    /**
     * ip地址转成整数.
     * 
     * @param ip
     * @return
     */
    public static long ip2Long(String ip) {
        String[] ips = ip.split("[.]");
        long num = (16777216L * Long.parseLong(ips[0])) + (65536L * Long.parseLong(ips[1])) + (256 * Long.parseLong(ips[2])) + Long.parseLong(ips[3]);
        return num;
    }
}
