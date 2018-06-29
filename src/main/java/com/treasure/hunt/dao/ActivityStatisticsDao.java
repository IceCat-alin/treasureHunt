package com.treasure.hunt.dao;

import com.treasure.hunt.entity.ActivityStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/19 11:36
 * @Version 版本号：v1.0.0
 */
public interface ActivityStatisticsDao extends JpaRepository<ActivityStatistics, Long>, JpaSpecificationExecutor<ActivityStatistics> {

    /**
     * 根据活动Id查找活动统计
     *
     * @param activityId
     * @return
     */
    ActivityStatistics findByActivityId(Long activityId);

    /**
     * 根据活动Id查找活动统计
     *
     * @param activityIds
     * @return
     */
    List<ActivityStatistics> findByActivityIdIn(List<Long> activityIds);
}
