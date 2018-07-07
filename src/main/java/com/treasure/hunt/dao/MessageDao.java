package com.treasure.hunt.dao;

import com.treasure.hunt.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MessageDao extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message> {

}
