package com.tofba.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class IpUtil {
    /**
     * 获取用户真实IP地址,不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,可是,如果通过了多级反向代理的话,X-Forwarded-For的值并不止一个,而是一串IP值,究竟哪个才是真正的用户端的真实IP呢?答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100 用户真实IP为： 192.168.1.110
     * 
     * @param request
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2018年4月8日]
     * @see [类、类#方法、类#成员]
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (StringUtils.isNotBlank(ip)) {
                if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
                    try {
                        ip = InetAddress.getLocalHost().getHostAddress();
                    } catch (Exception e) {
                        e.printStackTrace();
                        ip = "";
                    }
                }
            }
        }
        return ip;
    }
    
    /**
     * 单网卡IP
     * 
     * @return
     * @author Jeff(fba01)
     * @version [版本号, 2018年4月9日]
     * @see [类、类#方法、类#成员]
     */
    public static String getLocalIp() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
        return ip;
    }
    
    /**
     * 多网卡情况获取IP
     * 
     * @return
     * @author Jeff(fba01)
     * @version [版本号, 2018年4月9日]
     * @see [类、类#方法、类#成员]
     */
    public static List<String> getAllLocalhostIp() {
        List<String> ips = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (null != interfaces) {
                while (interfaces.hasMoreElements()) {
                    NetworkInterface ni = (NetworkInterface)interfaces.nextElement();
                    Enumeration<InetAddress> addresss = ni.getInetAddresses();
                    if (null != addresss) {
                        while (addresss.hasMoreElements()) {
                            InetAddress inetAddress = (InetAddress)addresss.nextElement();
                            String hostAddress = inetAddress.getHostAddress();
                            if (StringUtils.isNotBlank(hostAddress)) {
                                ips.add(hostAddress);
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            return ips;
        }
        return ips;
    }
    
    public static String getLocalHostName() {
        String hostName;
        try {
            InetAddress addr = InetAddress.getLocalHost();
            hostName = addr.getHostName();
        } catch (Exception ex) {
            hostName = "";
        }
        return hostName;
    }
}