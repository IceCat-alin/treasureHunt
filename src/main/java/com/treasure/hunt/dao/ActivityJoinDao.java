package com.treasure.hunt.dao;

import com.treasure.hunt.entity.ActivityJoin;
import com.treasure.hunt.entity.ActivityLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ActivityJoinDao extends JpaRepository<ActivityJoin, Long>, JpaSpecificationExecutor<ActivityJoin> {

    /**
     * 根据用户和活动查找加入信息
     * @param customerId 用户
     * @param activityId 活动
     * @return
     */
    ActivityJoin findByCustomerIdAndActivityId(Long customerId, Long activityId);

    /**
     * 统计加入人数
     * @param activityId
     * @return
     */
    Long countByActivityId(Long activityId);

    /**
     * 按活动统计加入人数
     * @param activityIds
     * @return
     */
    @Query("select activityId, count(*) from ActivityJoin where activityId in :activityIds group by activityId")
    List<Map<String,String>> groupByActivityId(@Param("activityIds") List<Long> activityIds);
}
