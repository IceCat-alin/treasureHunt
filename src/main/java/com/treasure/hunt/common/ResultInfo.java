package com.treasure.hunt.common;

import java.io.Serializable;

/**
 * @author linying
 * @version v1.0.0
 * @description 返回结果
 * @date 2018-04-12 14:44:02
 * @modified By
 */
public class ResultInfo implements Serializable {
    /**
     * 成功的消息代码
     */
    public static final int CODE_SUCCESS = 10000;

    /**
     * 失败的消息代码
     */
    public static final int CODE_FAILURE = 10001;

    /**
     * 系统异常代码
     */
    public static final int CODE_EXCEPTION = 10002;

    /**
     * 找不到相关数据代码
     */
    public static final int CODE_DATA_NOT_FOUND = 10003;

    /**
     * 请求被拒绝代码
     */
    public static final int CODE_REFUSE = 10004;

    /**
     * 结果代码
     */
    private int code;

    /**
     * 结果消息
     */
    private String msg;

    /**
     * 返回结果携带的数据
     */
    private Object data;

    /**
     * @param msg
     * @return
     * @description 成功
     * @author linying
     * @date 2018-04-12 14:52:25
     */
    public static ResultInfo success(String msg) {
        return success(msg, null);
    }

    /**
     * @param msg  返回消息
     * @param data 返回数据
     * @return
     * @description 成功
     * @author linying
     * @date 2018-04-12 14:52:42
     */
    public static ResultInfo success(String msg, Object data) {
        return resultInfo(CODE_SUCCESS, msg, data);
    }

    /**
     * @param msg 失败返回消息
     * @return
     * @description 失败
     * @author linying
     * @date 2018-04-12 14:53:31
     */
    public static ResultInfo failure(String msg) {
        return resultInfo(CODE_FAILURE, msg, null);
    }

    /**
     * @param code
     * @param msg
     * @param data
     * @return
     * @description 自定义返回结果
     * @author linying
     * @date 2018-04-12 14:53:54
     */
    public static ResultInfo resultInfo(int code, String msg, Object data) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(msg);
        resultInfo.setData(data);
        return resultInfo;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
