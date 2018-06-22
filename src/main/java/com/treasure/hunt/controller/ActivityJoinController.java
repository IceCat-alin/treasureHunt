package com.treasure.hunt.controller;

import com.treasure.hunt.common.ResultInfo;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ActivityLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/20 14:59
 * @Version 版本号：v1.0.0
 */
@Controller
@RequestMapping("/activity/like")
public class ActivityLikeController {

    @Autowired
    private ActivityLikeService activityLikeService;


    /**
     * 点赞/取消点赞
     * @param activityId
     * @param customerId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/likeActivity")
    @ResponseBody
    public ResultInfo likeActivity(Long activityId, Long customerId) throws BusinessException {
        activityLikeService.likeActivity(activityId, customerId);
        return ResultInfo.success("审核通过", null);
    }
}
