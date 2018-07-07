package com.treasure.hunt.service;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.entity.Reply;
import com.treasure.hunt.framework.exception.BusinessException;

public interface ReplyService {

    PageList<Reply> getReplyPage(Integer pageNo, Integer pageSize, Long commentId) throws BusinessException;

    Reply addReply(Long commentId, Long customerId, String content) throws BusinessException;

    void deleteReply(Long replyId) throws BusinessException;
}
