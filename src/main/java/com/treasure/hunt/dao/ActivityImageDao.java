package com.treasure.hunt.dao;

import com.treasure.hunt.entity.ActivityImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ActivityImageDao extends JpaRepository<ActivityImage, Long>,JpaSpecificationExecutor<ActivityImage> {

    /**
     * 根据活动获取活动图片
     * @param activityId
     * @return
     */
    List<ActivityImage> findByActivityId(Long activityId);

    /**
     * 根据活动获取活动图片
     * @param activityIds
     * @return
     */
    List<ActivityImage> findByActivityIdIn(List<Long> activityIds);

    /**
     * 根据活动删除活动图片
     * @param activityId
     */
    void deleteByActivityId(Long activityId);
}
