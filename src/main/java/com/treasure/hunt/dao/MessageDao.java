package com.treasure.hunt.dao;

import com.treasure.hunt.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MessageDao extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message> {

    /**
     * 获取消息数
     * @param status
     * @return
     */
    Integer countByStatusAndToCustomerId(Byte status,Long toCustomerId);
}
