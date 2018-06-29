package com.treasure.hunt.service;

import com.treasure.hunt.entity.WxCustomer;
import com.treasure.hunt.framework.exception.BusinessException;

public interface WxAuthService {

    /**
     * @param code          code
     * @param encryptedData encryptedData
     * @param iv            iv
     * @return 用户信息
     * @throws BusinessException 业务异常
     */
    public WxCustomer auth(String code, String encryptedData, String iv) throws BusinessException;

    String getQrCode(String path) throws Exception;
}
