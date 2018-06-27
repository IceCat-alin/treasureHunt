package com.treasure.hunt.controller;

import com.treasure.hunt.common.ResultInfo;
import com.treasure.hunt.entity.User;
import com.treasure.hunt.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/5/21 10:54
 * @Version 版本号：v1.0.0
 */
@Controller

public class IndexController {

    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "hello word";
    }

    /**
     * 登入
     *
     * @param user 用戶信息
     * @return 登入信息
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultInfo login(@RequestBody User user) {
        user = userInfoService.findByUserNameAndPassword(user);
        if (user != null) {
            return ResultInfo.resultInfo(ResultInfo.CODE_SUCCESS, "登入成功", null);
        } else {
            return ResultInfo.resultInfo(ResultInfo.CODE_FAILURE, "用户名密码错误", null);
        }

    }

    /**
     * @return 未登入信息
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public ResultInfo unauth() {
        return ResultInfo.resultInfo(ResultInfo.CODE_REFUSE, "未登录", null);
    }
}
