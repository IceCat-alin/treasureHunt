package com.treasure.hunt.dao;

import com.treasure.hunt.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyDao extends JpaRepository<Reply, Long>, JpaSpecificationExecutor<Reply> {


    /**
     * 查询每个评论的一条回复
     *
     * @param commentIds
     * @return
     */
    @Query("from Reply where commentId in :commentIds group by commentId")
    List<Reply> findByCommentIdsGroupByCommentId(@Param("commentIds") List<Long> commentIds);

}
