package com.treasure.hunt.controller;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.ResultInfo;
import com.treasure.hunt.dto.ActivityDto;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 类描述：活动
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/19 11:21
 * @Version 版本号：v1.0.0
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 发起活动
     *
     * @param activityDto 活动信息
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/addActivity")
    @ResponseBody
    public ResultInfo addActivity(@RequestBody ActivityDto activityDto) throws BusinessException {
        activityService.addActivity(activityDto);
        return ResultInfo.success("发起活动成功，请等待审核", null);
    }

    /**
     * 审核活动
     *
     * @param activityId 活动Id
     * @param status     状态
     * @return 审核结果
     * @throws BusinessException
     */
    @RequestMapping("/checkActivity")
    @ResponseBody
    public ResultInfo checkActivity(Long activityId, Byte status) throws BusinessException {
        activityService.checkActivity(activityId, status);
        return ResultInfo.success("审核通过", null);
    }

    /**
     * 分页查询活动
     *
     * @param pageNo
     * @param pageSize
     * @param sort
     * @param sortField
     * @param activityDto
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getActivityPage")
    @ResponseBody
    public ResultInfo getActivityPage(Integer pageNo, Integer pageSize, String sort, String sortField, ActivityDto activityDto) throws BusinessException {
        PageList<ActivityDto> activityPageList = activityService.getActivityPage(pageNo, pageSize, sort, sortField, activityDto);
        return ResultInfo.success("分页查询活动成功", activityPageList);
    }


    /**
     * 获取活动信息
     *
     * @param activityId 活动
     * @param customerId 用户
     * @return 活动信息
     * @throws BusinessException
     */
    @RequestMapping("/getActivity")
    @ResponseBody
    public ResultInfo getActivity(Long activityId, Long customerId) throws BusinessException {
        return ResultInfo.success("获取活动成功", activityService.getActivity(activityId, customerId));
    }

}
