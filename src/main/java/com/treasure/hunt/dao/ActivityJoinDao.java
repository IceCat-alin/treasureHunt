package com.treasure.hunt.dao;

import com.treasure.hunt.entity.ActivityLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityLikeDao extends JpaRepository<ActivityLike, Long>, JpaSpecificationExecutor<ActivityLike> {

    /**
     * 根据用户和活动查找点赞
     * @param customerId 用户
     * @param activityId 活动
     * @return
     */
    ActivityLike findByCustomerIdAndActivityId(Long customerId, Long activityId);

    /**
     * 统计点赞人数
     * @param activityId
     * @return
     */
    Long countByActivityId(Long activityId);

    /**
     * 按活动统计点赞人数
     * @param activityIds
     * @return
     */
    @Query("select count(*) from ActivityLike l where l.activityId in :activityIds group by activityId")
    List<Long> countGroupByActivityId(@Param("activityIds") List<Long> activityIds);
}
