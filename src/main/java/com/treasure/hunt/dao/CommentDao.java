package com.treasure.hunt.dao;

import com.treasure.hunt.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

    /**
     * 根据活动获取活动评论
     *
     * @param activityId
     * @return
     */
    List<Comment> findByActivityId(Long activityId);
}
