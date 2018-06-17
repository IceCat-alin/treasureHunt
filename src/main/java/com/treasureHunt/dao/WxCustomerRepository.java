package com.treasureHunt.dao;

import com.treasureHunt.entity.WxCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxCustomerRepository extends JpaRepository<WxCustomer, Long>,JpaSpecificationExecutor<WxCustomer>{

    /**
     * 根据unionId查找微信用户
     * @param unionId
     * @return
     */
    WxCustomer findByUnionId(String unionId);
}
