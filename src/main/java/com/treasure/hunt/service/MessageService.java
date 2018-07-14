package com.treasure.hunt.service;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.dto.MessageDto;
import com.treasure.hunt.framework.exception.BusinessException;

public interface MessageService {

    PageList<MessageDto> getMessagePage(Integer pageNo, Integer pageSize, Long customerId) throws BusinessException;

    void addMessage(Long activityId, String content, Byte type) throws BusinessException;

    void setMessageRead(Long messageId) throws BusinessException;

    void deleteMessage(Long messageId) throws BusinessException;

    Integer getUnReadNum(Long customerId);
}
