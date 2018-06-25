package com.treasure.hunt.service.impl;

import com.alibaba.fastjson.JSON;
import com.treasure.hunt.common.Constant;
import com.treasure.hunt.dao.WxCustomerDao;
import com.treasure.hunt.entity.WxCustomer;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.framework.utils.AES128Util;
import com.treasure.hunt.framework.utils.HttpUtil;
import com.treasure.hunt.service.WxAuthService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/14 14:58
 * @Version 版本号：v1.0.0
 */
@Service("wxAuthService")
@Transactional(rollbackFor = Exception.class)
public class WxAuthServiceImpl implements WxAuthService {

    @Autowired
    private WxCustomerDao wxCustomerDao;

    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(WxAuthServiceImpl.class);

    /**
     * @param code          code
     * @param encryptedData encryptedData
     * @param iv            iv
     * @return 用户信息
     * @throws BusinessException 业务异常
     */
    @Override
    public WxCustomer auth(String code, String encryptedData, String iv) throws BusinessException {
        // 获取微信session_key Url
        String sessionUrl = getWXSessionUrl(code, Constant.APP_ID, Constant.APP_SECRET);
        // 获取session_key
        String result = null;
        try {
            LOG.info("获取session_key");
            Map<String, String> map = new HashMap<>(16);
            map.put("appid", Constant.APP_ID);
            map.put("secret", Constant.APP_SECRET);
            map.put("js_code", code);
            map.put("grant_type", "authorization_code");
            result = HttpUtil.post("https://api.weixin.qq.com/sns/jscode2session", map);
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        Map<String, String> map = JSON.parseObject(result, Map.class);
        LOG.info(map.toString());
        if (StringUtils.isBlank(map.get("session_key"))) {
            throw new BusinessException("授权登入失败，失败原因：" + result);
        }
        String customerStr = AES128Util.wxDecrypt(encryptedData, map.get("session_key"), iv);
        LOG.info(customerStr);
        if (StringUtils.isBlank(customerStr)) {
            throw new BusinessException("授权登入失败，失败原因：解密失败");
        }
        WxCustomer wxCustomer = JSON.parseObject(customerStr, WxCustomer.class);
        if (StringUtils.isBlank(wxCustomer.getOpenId())) {
            throw new BusinessException("授权登入失败，失败原因：获取openId失败");
        }
        WxCustomer customer = addOrUpdateCustomer(wxCustomer);
        if (customer == null) {
            throw new BusinessException("授权登入失败，失败原因：插入用户失败");
        }
        return customer;
    }

    public WxCustomer addOrUpdateCustomer(WxCustomer wxCustomer) {
        // 先用Open去获取用户
        if (StringUtils.isNotBlank(wxCustomer.getOpenId())) {
            WxCustomer customer = wxCustomerDao.findByOpenId(wxCustomer.getOpenId());

            // 如果该用户不存在，新增用户
            if (customer == null) {
                wxCustomer.setCreateTime(new Date());
                wxCustomer.setUpdateTime(new Date());
                wxCustomer = wxCustomerDao.save(wxCustomer);
                return wxCustomer;
            }
            // 存在就更新用户信息
            else {
                customer.setNickName(wxCustomer.getNickName());
                customer.setAvatarUrl(wxCustomer.getAvatarUrl());
                customer.setGender(wxCustomer.getGender());
                customer.setUpdateTime(new Date());
                customer = wxCustomerDao.save(customer);
                return customer;
            }
        }
        return null;
    }

    private static String getWXSessionUrl(String code, String appId, String secret) {
        return String.format(Constant.WX_SESSION_URL, appId, secret, code);
    }
}
