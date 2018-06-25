package com.treasure.hunt.dao;

import com.treasure.hunt.entity.WxCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface WxCustomerDao extends JpaRepository<WxCustomer, Long>,JpaSpecificationExecutor<WxCustomer>{

    /**
     * 根据openId查找微信用户
     * @param openId
     * @return
     */
    WxCustomer findByOpenId(String openId);

    /**
     * 根据用户Id集合查找用户
     * @param customerIds
     * @return
     */
    List<WxCustomer> findByCustomerIdIn(List<Long> customerIds);
}
