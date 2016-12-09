package com.example.baseapp.http;

/**
 * 网络请求URL
 *
 * 请求的参数跟请求地址用;分开
 *
 */
public class HttpUrl {
    String url = "http://192.168.3.6:88/Mobile/Index/loginAction?deviceId=ll&debug=1;account;password;isCloudLogin";

    /**
     * 请求地址的域名
     */
    public static final String BASEURL = "http://192.168.3.6:88/";
    /**
     * 用户登录
     */
    public static final String LOGIN = BASEURL + "Mobile/Index/loginAction?deviceId=ll&debug=1;account;password;isCloudLogin";



}
