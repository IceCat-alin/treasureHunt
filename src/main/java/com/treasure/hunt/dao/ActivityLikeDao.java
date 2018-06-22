package com.treasure.hunt.dao;

import com.treasure.hunt.entity.ActivityLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActivityLikeDao extends JpaRepository<ActivityLike, Long>, JpaSpecificationExecutor<ActivityLike> {

    /**
     * 根据用户和活动查找点赞
     * @param customerId 用户
     * @param activityId 活动
     * @return
     */
    ActivityLike findByCustomerIdAndActivityId(Long customerId, Long activityId);
}
