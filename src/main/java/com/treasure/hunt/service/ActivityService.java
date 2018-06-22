package com.treasure.hunt.service;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.dto.ActivityDto;
import com.treasure.hunt.framework.exception.BusinessException;

public interface ActivityService {

    void addActivity(ActivityDto activityDto) throws BusinessException;

    void checkActivity(Long activityId,Byte status) throws BusinessException;

    PageList<ActivityDto> getActivityPage(Integer pageNo, Integer pageSize, String sort, String sortField, ActivityDto activityDto) throws BusinessException;


}
