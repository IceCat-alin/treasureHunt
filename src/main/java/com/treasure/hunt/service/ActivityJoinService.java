package com.treasure.hunt.service;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.dto.ActivityDto;
import com.treasure.hunt.dto.ActivityJoinDto;
import com.treasure.hunt.entity.ActivityJoin;
import com.treasure.hunt.framework.exception.BusinessException;

public interface ActivityJoinService {

    ActivityJoin joinActivity(Long activityId, Long customerId) throws BusinessException;

    PageList<ActivityJoinDto> getActivityJoinPage(Integer pageNo, Integer pageSize, Long activityId) throws BusinessException;

    PageList<ActivityDto> getMyJoin(Integer pageNo, Integer pageSize, Long customerId) throws BusinessException;
}
