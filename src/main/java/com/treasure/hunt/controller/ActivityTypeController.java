package com.treasure.hunt.controller;

import com.treasure.hunt.common.ResultInfo;
import com.treasure.hunt.service.ActivityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/25 9:34
 * @Version 版本号：v1.0.0
 */
@Controller
@RequestMapping("/activity/type")
public class ActivityTypeController {

    @Autowired
    private ActivityTypeService activityTypeService;

    /**
     * 获取活动类型列表
     * @return
     */
    @RequestMapping("/getTypeList")
    @ResponseBody
    public ResultInfo getTypeList() {
        return ResultInfo.success("获取类型列表成功成功", activityTypeService.getTypeList());
    }
}
