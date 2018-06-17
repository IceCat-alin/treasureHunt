package com.treasureHunt.service;

import com.treasureHunt.entity.WxCustomer;
import com.treasureHunt.framework.exception.BusinessException;

public interface WxAuthService {

    /**
     * @param code code
     * @param encryptedData encryptedData
     * @param iv iv
     * @return 用户信息
     * @throws BusinessException 业务异常
     */
    public WxCustomer auth(String code, String encryptedData, String iv) throws BusinessException;
}
