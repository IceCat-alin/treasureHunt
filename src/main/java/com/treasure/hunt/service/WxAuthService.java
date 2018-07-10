package com.treasure.hunt.service;

import com.treasure.hunt.common.PageList;
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
    WxCustomer auth(String code, String encryptedData, String iv) throws BusinessException;

    String getQrCode(String path) throws Exception;

    WxCustomer getCustomerInfo(Long customerId) throws BusinessException;

    Integer getRank(Long customerId);

    PageList<WxCustomer> getCustomerPage(Integer pageNo, Integer pageSize, WxCustomer wxCustomer);
}
