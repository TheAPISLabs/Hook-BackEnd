package com.yike.apis.utils;


import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;


public class IpHelper {

    /**
     * <p>
     *  The method of obtaining the client's IP address is request.getremoteaddr (), which works in most cases.
     * * However, if you pass Apache,Squid and other reverse proxy software, you cannot obtain the real IP address of the client. If you pass multi-level reverse proxy,
     * * X-Forwarded-For is not a single IP address. It is a string of IP addresses. Which is the true IP address of the client?
     * * The answer is to take the first non-unknown valid IP string from x-Forwarded-For.
     * * For example, x-Forwarded-For: 192.168.1.110, 192.168.1.120,
     * * 192.168.1.130, 192.168.1.100 The real IP address of the user is 192.168.1.110
     *  </p>
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String fromSource = "X-Real-IP";
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
            fromSource = "X-Forwarded-For";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            fromSource = "Proxy-Client-IP";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            fromSource = "WL-Proxy-Client-IP";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            fromSource = "request.getRemoteAddr";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                /** Obtain the IP address configured on the host based on the network adapter */
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ip = inet.getHostAddress();
                } catch (UnknownHostException e) {

                }
            }
        }
        /**
         * In the case of through multiple agents, the first real IP IP for the client, multiple IP in accordance with the ', 'division "* * *. * * *. * * *. * * *". The length () =
         * 15
         */
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}

