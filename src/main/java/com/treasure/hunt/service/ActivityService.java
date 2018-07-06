package com.treasure.hunt.service;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.dto.ActivityDto;
import com.treasure.hunt.entity.Activity;
import com.treasure.hunt.framework.exception.BusinessException;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    void addActivity(ActivityDto activityDto) throws BusinessException;

    void setActivityStatus(Long activityId, Byte status) throws BusinessException;

    List<ActivityDto> getTopActivity() throws BusinessException;

    PageList<ActivityDto> getActivityPage(Integer pageNo, Integer pageSize, String sort, String sortField, ActivityDto activityDto) throws BusinessException;

    List<ActivityDto> packData(List<Activity> domainList) throws BusinessException;

    ActivityDto getActivity(Long activityId) throws BusinessException;

    void deleteActivity(Long activityId) throws BusinessException;

    Map<String, Boolean> getLikeAndJoinStatus(Long activityId, Long customerId);

    void updateActivity(ActivityDto activityDto) throws BusinessException;

    void setTopActivity(Long activityId) throws BusinessException;
}
