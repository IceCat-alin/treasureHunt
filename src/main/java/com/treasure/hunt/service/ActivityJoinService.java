package com.treasure.hunt.service;

import java.util.List;
import java.util.Map;

public interface ActivityLikeService {

    void setLike(Long activityId, Long customerId);

    Map<Long, Long> groupByActivityId(List<Long> activityIds);
}
