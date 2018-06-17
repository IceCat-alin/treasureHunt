package com.treasureHunt.common;

/**
 * @description 类描述：常量
 * @author 创建人：linying
 * @date 创建时间：2018/4/12 16:09:29
 * @version 1.0.0
 */
public class Constant {
    /**
     * 默认时间格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 默认字符格式
     */
    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 系统异常的后缀标识
     */
    public static final String MSG_SYSTEM_EXCEPTION = "系统可能存在网络中断、数据库无法连接，内存不足等系统性问题，请与管理员联系。";

    /**
     * 分页默认页数
     */
    public static final int DEFAULT_PAGE = 0;

    /**
     * 分页默认数据数量
     */
    public static final int DEFAULT_SIZE = 20;

    /**
     * 必填字段的默认值
     */
    public static final Long DEFAULT_LONG = 0L;

    /**
     * 必填字段的默认值
     */
    public static final Integer DEFAULT_INT = 0;

    /**
     * 必填字段的默认值
     */
    public static final Byte DEFAULT_BYTE = 0;

    /**
     * 初始化密码
     */
    public static final String DEFAULT_PWD = "888888";

    /**
     * 微信小程序appid
     */
    public static final String APP_ID = "wxf7608f7eadab06bc";
    /**
     * 微信小程序app secret
     */
    public static final String APP_SECRET = "8b4827e1ba33f9602b86823f4104e5fa";
    /**
     * 微信小程序获取session的Url
     */
    public static final String WX_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
    /**
     * 微信小程序获取access_token Url
     */
    public static final String WX_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";


}
