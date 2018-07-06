package com.treasure.hunt.controller;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.ResultInfo;
import com.treasure.hunt.dto.ActivityDto;
import com.treasure.hunt.entity.WxCustomer;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.WxAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/14 14:52
 * @Version 版本号：v1.0.0
 */
@Controller
@RequestMapping("/app")
public class WxAuthController {

    @Autowired
    private WxAuthService wxAuthService;

    /**
     * 授权登入
     *
     * @param code
     * @param encryptedData
     * @param iv
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/auth")
    @ResponseBody
    public ResultInfo auth(String code, String encryptedData, String iv) throws BusinessException {

        WxCustomer wxCustomer = wxAuthService.auth(code, encryptedData, iv);

        return ResultInfo.success("授权登入成功", wxCustomer);
    }

    /**
     * 获取用户信息成功
     *
     * @param customerId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getCustomerInfo")
    @ResponseBody
    public ResultInfo getCustomerInfo(Long customerId) throws BusinessException {
        WxCustomer wxCustomer = wxAuthService.getCustomerInfo(customerId);
        wxCustomer.setOpenId(null);
        return ResultInfo.success("获取用户信息成功", wxCustomer);
    }

    /**
     * 分页查询用户
     *
     * @param pageNo
     * @param pageSize
     * @param wxCustomer
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getCustomerPage")
    @ResponseBody
    public ResultInfo getCustomerPage(Integer pageNo, Integer pageSize, WxCustomer wxCustomer) throws BusinessException {
        PageList<WxCustomer> customerPageList = wxAuthService.getCustomerPage(pageNo, pageSize, wxCustomer);
        return ResultInfo.success("分页查询活动成功", customerPageList);
    }


}
