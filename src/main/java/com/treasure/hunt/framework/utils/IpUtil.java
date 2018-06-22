package com.treasure.hunt.framework.utils;/**
 * @Description
 * @Author linying
 * @Date 2018-04-13 17:02:07
 * @Version v1.0.0
 * @Modified By
 */

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/4/13 17:02
 * @Version 版本号：v1.0.0
 */
public class IpUtil {

    /**
     * 获取登录用户IP地址
     *
     * @param request HttpServletRequest
     * @return ip
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "本地";
        }
        return ip;
    }
}
