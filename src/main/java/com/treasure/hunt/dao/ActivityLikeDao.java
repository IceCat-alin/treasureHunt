package com.treasure.hunt.dao;

import com.treasure.hunt.entity.ActivityImage;
import com.treasure.hunt.entity.ActivityLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

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
    @Query("select activityId, count(*) from ActivityLike where activityId in :activityIds group by activityId")
    List<Object[]> groupByActivityId(@Param("activityIds") List<Long> activityIds);

    /**
     * 根据活动获取点赞
     * @param activityId
     * @return
     */
    List<ActivityLike> findByActivityId(Long activityId);

}
