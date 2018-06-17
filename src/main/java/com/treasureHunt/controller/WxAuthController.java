package com.treasureHunt.controller;

import com.treasureHunt.common.ResultInfo;
import com.treasureHunt.entity.WxCustomer;
import com.treasureHunt.framework.exception.BusinessException;
import com.treasureHunt.service.WxAuthService;
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
public class WxAuthController {

    @Autowired
    private WxAuthService wxAuthService;

    @RequestMapping("/app/auth")
    @ResponseBody
    public ResultInfo getUserPage(String code,  String encryptedData, String iv) throws BusinessException {

        WxCustomer wxCustomer = wxAuthService.auth(code,encryptedData,iv);

        return ResultInfo.success("授权登入成功", wxCustomer);
    }


}
