package com.app.http;

/**
 * Created by MaShiZhao on 2016/12/7.
 */
public interface InterfaceHttpResult {
    /**
     * http 回调
     *
     * @param requestCode http关联的请求码
     * @param baseBean    强转类型
     */
    //   public void getCallback(int requestCode, int jsonCode, String message, Object baseBean);
    public void getCallback(int requestCode, Object baseBean);
}
