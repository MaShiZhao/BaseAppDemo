package com.app.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaShiZhao on 2016/12/6.
 */
public class Utils {

    public static void testString(String string) {
        Log.d("msz", string);
    }


    public static String getParamsUrl(String... urls) {

        if (urls == null) {
            LogWriter.e(" 请求参数 urls 不能为null ");
            return "";
        }
        if (urls.length == 1) {
            LogWriter.d("get url : " + urls[0]);
            return urls[0];
        } else {
            List<String> urlParam = new ArrayList<String>();
            for (String url : urls) {
                urlParam.add(url);
            }
            urlParam.remove(0);
            return  Utils.GetURLParams(urls[0], urlParam);
        }

    }

    /**
     * 网络请求分割
     *
     * @param url
     * @param urlParam
     * @return
     */
    public static String GetURLParams(final String url, String... urlParam) {
        if (urlParam != null && urlParam.length > 0) {
            final StringBuffer sBuffer = new StringBuffer();
            final StringBuffer urlBuffer = new StringBuffer(url);
            for (int i = 0; i < urlParam.length; i++) {
                sBuffer.append("{" + i + "}");
                final int start = urlBuffer.indexOf(sBuffer.toString());
                if (start == -1) {
                    sBuffer.delete(0, sBuffer.toString().length());
                    continue;
                }
                final int len = sBuffer.length();
                final String s = urlParam[i];
                String encode = null;
                try {
                    encode = URLEncoder.encode(s, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (encode != null) {
                    urlBuffer.replace(start, start + len, encode);
                    sBuffer.delete(0, sBuffer.toString().length());
                }
            }
            LogWriter.debugInfo("URL Param 处理之后:" + urlBuffer.toString());
            return urlBuffer.toString();
        }
        return null;
    }

    // -------------------------------------------------------------------------------------------------------------------
    public static String GetURLParams(final String url,
                                      final List<String> urlParam) {
        if (urlParam != null && urlParam.size() > 0) {
            // 处理url参数
            if (urlParam.size() > 0) {
                final StringBuffer sBuffer = new StringBuffer();
                final StringBuffer urlBuffer = new StringBuffer(url);
                for (int i = 0; i < urlParam.size(); i++) {
                    sBuffer.append("{" + i + "}");
                    final int start = urlBuffer.indexOf(sBuffer.toString());
                    if (start == -1) {
                        sBuffer.delete(0, sBuffer.toString().length());
                        continue;
                    }
                    final int len = sBuffer.length();
                    final String s = urlParam.get(i).toString();
                    urlBuffer.replace(start, start + len, s);
                    sBuffer.delete(0, sBuffer.toString().length());
                }
                LogWriter.debugInfo("URL Param 处理之后:" + urlBuffer.toString());
                return urlBuffer.toString();
            }
        }
        return null;
    }

}
