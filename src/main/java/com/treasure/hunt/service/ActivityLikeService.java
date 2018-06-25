package com.treasure.hunt.service;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.dto.ActivityLikeDto;
import com.treasure.hunt.framework.exception.BusinessException;

import java.util.List;
import java.util.Map;

public interface ActivityLikeService {

    void likeActivity(Long activityId, Long customerId);

    Map<Long, Long> groupByActivityId(List<Long> activityIds);

    PageList<ActivityLikeDto> getActivityLikePage(Integer pageNo, Integer pageSize, Long activityId) throws BusinessException;
}
