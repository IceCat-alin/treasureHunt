package com.treasure.hunt.dao;

import com.treasure.hunt.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

    /**
     * 根据活动获取活动评论
     *
     * @param activityId
     * @return
     */
    List<Comment> findByActivityId(Long activityId);

    /**
     * 按活动统计点赞人数
     * @param activityIds
     * @return
     */
    @Query("select activityId, count(*) from Comment where activityId in :activityIds group by activityId")
    List<Object[]> groupByActivityId(@Param("activityIds") List<Long> activityIds);
}
