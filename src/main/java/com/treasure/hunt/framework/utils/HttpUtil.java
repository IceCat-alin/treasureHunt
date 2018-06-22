package com.treasure.hunt.framework.utils;

import com.treasure.hunt.common.Constant;
import com.treasure.hunt.framework.exception.BusinessException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author 创建人：林宏
 * @author 修改人：林宏
 * @version 1.0.0
 * @description 类描述：HTTP工具类
 * @date 修改时间：2016年5月4日 上午10:59:55
 * @description 修改备注：
 */
public class HttpUtil {
    private static final Logger LOG = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 请求配置
     */
    private static RequestConfig CONFIG;

    /**
     * 默认超时时间
     */
    private static final int MAX_TIMEOUT = 30000;

    static {
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        CONFIG = configBuilder.build();
    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url    发送请求的 URL
     * @param params 请求参数
     * @return 所代表远程资源的响应结果
     * @throws BusinessException 异常
     */
    public static String get(String url, Map<String, Object> params) throws BusinessException {
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (Entry<String, Object> entry : params.entrySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(entry.getKey()).append("=").append(entry.getValue());
            i++;
        }
        try {
            HttpGet get = new HttpGet(url + param);
            CloseableHttpResponse response = HttpClients.createDefault().execute(get);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, Constant.DEFAULT_ENCODING);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException("发送GET请求出现异常：" + e.getMessage(), e);
        }
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url    发送请求的 URL
     * @param params 请求参数
     * @return 所代表远程资源的响应结果
     * @throws BusinessException 异常
     */
    public static String post(String url, Map<String, String> params) throws BusinessException {
        CloseableHttpResponse response = null;
        try {
            HttpPost post = new HttpPost(url);
            post.setConfig(CONFIG);
            List<NameValuePair> pairs = new ArrayList<>(params.size());
            for (Entry<String, String> entry : params.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            post.setEntity(new UrlEncodedFormEntity(pairs, Charset.forName(Constant.DEFAULT_ENCODING)));
            response = HttpClients.createDefault().execute(post);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, Constant.DEFAULT_ENCODING);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException("发送POST请求出现异常：" + e.getMessage(), e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * @param url url
     * @param xml xml请求参数
     * @return 所代表远程资源的响应结果
     * @throws Exception exception
     * @description post请求，参数为 xml
     */
    public static String post(String url, String xml) throws Exception {
        HttpResponse response = null;
        try {
            HttpPost post = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(xml, "UTF-8");// 如果xml里有带中文参数必须加上编码"UTF-8"
            post.setEntity(stringEntity);
            post.setHeader("Content-Type", "text/xml");
            response = HttpClients.createDefault().execute(post);
            HttpEntity httpEntity = response.getEntity();
            return EntityUtils.toString(httpEntity, Constant.DEFAULT_ENCODING);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new Exception("发送POST请求出现异常：" + e.getMessage(), e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * @param url  url
     * @param json json请求参数
     * @return String
     * @throws Exception 异常
     * @description post请求 json
     */
    public static String postJson(String url, String json) throws Exception {
        HttpResponse response = null;
        try {
            HttpPost post = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            post.setEntity(stringEntity);
            post.setHeader("Content-Type", "application/json");
            response = HttpClients.createDefault().execute(post);
            HttpEntity httpEntity = response.getEntity();
            return EntityUtils.toString(httpEntity, Constant.DEFAULT_ENCODING);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new Exception("发送POST请求出现异常：" + e.getMessage(), e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

}
