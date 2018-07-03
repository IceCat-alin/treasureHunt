package com.treasure.hunt.service;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.dto.CommentDto;
import com.treasure.hunt.entity.Comment;
import com.treasure.hunt.framework.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/22 16:31
 * @Version 版本号：v1.0.0
 */
public interface CommentService {

    Comment addComment(CommentDto commentDto) throws BusinessException;

    PageList<CommentDto> getCommentPage(Integer pageNo, Integer pageSize, Long activityId) throws BusinessException;

    void setBestComment(Long commentId) throws BusinessException;

    Map<Long, Long> groupByActivityId(List<Long> activityIds);

    void deleteComment(Long commentId) throws BusinessException;
}
