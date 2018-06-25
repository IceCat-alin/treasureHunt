package com.treasure.hunt.service;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.dto.ActivityDto;
import com.treasure.hunt.dto.ActivityJoinDto;
import com.treasure.hunt.dto.ActivityLikeDto;
import com.treasure.hunt.framework.exception.BusinessException;

import java.util.List;
import java.util.Map;

public interface ActivityJoinService {

    void joinActivity(Long activityId, Long customerId);

    PageList<ActivityJoinDto> getActivityJoinPage(Integer pageNo, Integer pageSize, Long activityId) throws BusinessException;
}
