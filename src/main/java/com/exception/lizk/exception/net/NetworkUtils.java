package com.exception.lizk.exception.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 前端获取网络信息工具类
 * @author <a href="mailto:sunmch@163.com">smc</a>
 * @date 2019-07-01 17:24
 * @since 1.0.0
 **/
public final class NetworkUtils {

    private static Logger logger = LoggerFactory.getLogger(NetworkUtils.class);

    private static final String UNKNOWN_CLINET="unknown";

    private static final String IP_DELIMITER=",";

    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
     */
    public final static String getIpAddress(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN_CLINET.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_CLINET.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_CLINET.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_CLINET.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_CLINET.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String[] ips = ip.split(IP_DELIMITER);

        return ips.length ==1?ip:ips[0];
    }


}
