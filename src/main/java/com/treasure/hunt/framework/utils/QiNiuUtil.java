package com.treasure.hunt.framework.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.treasure.hunt.common.Constant;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/29 13:55
 * @Version 版本号：v1.0.0
 */
public class QiNiuUtil {

    /**
     * 七牛上传byte
     *
     * @param bytes
     * @return
     */
    public static String uploadByByte(byte[] bytes) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());

        UploadManager uploadManager = new UploadManager(cfg);

        Auth auth = Auth.create(Constant.QN_ACCESS_KEY, Constant.QN_SECRET_KEY);
        String upToken = auth.uploadToken(Constant.QN_BUCKET);

        try {
            Response response = uploadManager.put(bytes, null, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return "https://img.snjxb.com/" + putRet.hash;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            return null;
        }
    }
}
