package com.treasure.hunt.controller;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.ResultInfo;
import com.treasure.hunt.dto.ActivityDto;
import com.treasure.hunt.dto.ActivityJoinDto;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ActivityJoinService;
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
@RequestMapping("/activity/join")
public class ActivityJoinController {

    @Autowired
    private ActivityJoinService activityJoinService;


    /**
     * 加入活动
     *
     * @param activityId
     * @param customerId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/joinActivity")
    @ResponseBody
    public ResultInfo joinActivity(Long activityId, Long customerId) throws BusinessException {
        activityJoinService.joinActivity(activityId, customerId);
        return ResultInfo.success("加入成功", null);
    }

    /**
     * 获取加入列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getActivityJoinPage")
    @ResponseBody
    public ResultInfo getActivityLikePage(Integer pageNo, Integer pageSize, Long activityId) throws BusinessException {
        PageList<ActivityJoinDto> activityPageList = activityJoinService.getActivityJoinPage(pageNo, pageSize, activityId);
        return ResultInfo.success("分页查询加入活动用户成功", activityPageList);
    }

    /**
     * 获取我的加入
     *
     * @param pageNo
     * @param pageSize
     * @param customerId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getMyJoin")
    @ResponseBody
    public ResultInfo getMyJoin(Integer pageNo, Integer pageSize, Long customerId) throws BusinessException {
        PageList<ActivityDto> activityPageList = activityJoinService.getMyJoin(pageNo, pageSize, customerId);
        return ResultInfo.success("分页查询我加入的活动成功", activityPageList);
    }
}
