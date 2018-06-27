package com.treasure.hunt.controller;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.ResultInfo;
import com.treasure.hunt.dto.ActivityLikeDto;
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
     *
     * @param activityId
     * @param customerId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/likeActivity")
    @ResponseBody
    public ResultInfo likeActivity(Long activityId, Long customerId) throws BusinessException {
        activityLikeService.likeActivity(activityId, customerId);
        return ResultInfo.success("点赞/取消点赞成功", null);
    }

    /**
     * 获取点赞列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getActivityLikePage")
    @ResponseBody
    public ResultInfo getActivityLikePage(Integer pageNo, Integer pageSize, Long activityId) throws BusinessException {
        PageList<ActivityLikeDto> activityLikePage = activityLikeService.getActivityLikePage(pageNo, pageSize, activityId);
        return ResultInfo.success("分页查询活动点赞成功", activityLikePage);
    }
}
