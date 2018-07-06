package com.treasure.hunt.controller;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.ResultInfo;
import com.treasure.hunt.dto.ActivityDto;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ActivityService;
import com.treasure.hunt.service.ActivityStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

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

    @Autowired
    private ActivityStatisticsService activityStatisticsService;

    /**
     * 发起活动
     *
     * @param activityDto 活动信息
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/addActivity")
    @ResponseBody
    public ResultInfo addActivity(ActivityDto activityDto) throws BusinessException {
        activityService.addActivity(activityDto);
        return ResultInfo.success("发起活动成功，请等待审核", null);
    }

    /**
     * 修改活动
     *
     * @param activityDto
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/updateActivity")
    @ResponseBody
    public ResultInfo updateActivity(ActivityDto activityDto) throws BusinessException {
        activityService.updateActivity(activityDto);
        return ResultInfo.success("修改活动成功，请等待审核", null);
    }

    /**
     * 审核活动
     *
     * @param activityId 活动Id
     * @param status     状态
     * @return 审核结果
     * @throws BusinessException
     */
    @RequestMapping("/setActivityStatus")
    @ResponseBody
    public ResultInfo setActivityStatus(Long activityId, Byte status) throws BusinessException {
        activityService.setActivityStatus(activityId, status);
        String msg;
        if (ActivityDto.STATUS_START.equals(status)) {
            msg = "审核通过";
        } else if (ActivityDto.STATUS_END.equals(status)) {
            msg = "活动已结束";
        } else {
            msg = "修改状态成功";
        }
        return ResultInfo.success(msg, null);
    }

    /**
     * 设置置顶
     * @param activityId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/setTopActivity")
    @ResponseBody
    public ResultInfo setTopActivity(Long activityId) throws BusinessException {
        activityService.setTopActivity(activityId);
        return ResultInfo.success("设置成功", null);
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
    @RequestMapping(value = "/getActivityPage")
    @ResponseBody
    public ResultInfo getActivityPage(Integer pageNo, Integer pageSize, String sort, String sortField, ActivityDto activityDto) throws BusinessException {
        PageList<ActivityDto> activityPageList = activityService.getActivityPage(pageNo, pageSize, sort, sortField, activityDto);
        return ResultInfo.success("分页查询活动成功", activityPageList);
    }


    /**
     * 获取活动信息
     *
     * @param activityId 活动
     * @return 活动信息
     * @throws BusinessException
     */
    @RequestMapping("/getActivity")
    @ResponseBody
    public ResultInfo getActivity(Long activityId) throws BusinessException {
        return ResultInfo.success("获取活动成功", activityService.getActivity(activityId));
    }

    /**
     * 删除活动
     *
     * @param activityId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/deleteActivity")
    @ResponseBody
    public ResultInfo deleteActivity(Long activityId) throws BusinessException {
        activityService.deleteActivity(activityId);
        return ResultInfo.success("删除活动成功", null);
    }

    /**
     * 增加浏览量
     *
     * @param activityId
     * @return
     */
    @RequestMapping("/addActivityView")
    @ResponseBody
    public ResultInfo addActivityView(Long activityId) {
        activityStatisticsService.updateStatistics(activityId, "view", "add");
        return ResultInfo.success("增加浏览量成功", null);
    }

    /**
     * 获取用户在该活动的点赞和加入状态
     *
     * @param activityId
     * @param customerId
     * @return
     */
    @RequestMapping("/getLikeAndJoinStatus")
    @ResponseBody
    public ResultInfo getLikeAndJoinStatus(Long activityId, Long customerId) {
        Map<String, Boolean> map = activityService.getLikeAndJoinStatus(activityId, customerId);
        return ResultInfo.success("获取状态成功", map);
    }

    /**
     * 获取置顶活动
     *
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getTopActivity")
    @ResponseBody
    public ResultInfo getTopActivity() throws BusinessException {
        return ResultInfo.success("获取置顶活动成功", activityService.getTopActivity());
    }

}
