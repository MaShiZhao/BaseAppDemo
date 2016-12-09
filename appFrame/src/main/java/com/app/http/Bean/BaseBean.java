package com.app.http.Bean;

/**
 * 服务器返回结果解析对象
 *
 */
public class BaseBean
{
    private int code;
    private String msg;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
