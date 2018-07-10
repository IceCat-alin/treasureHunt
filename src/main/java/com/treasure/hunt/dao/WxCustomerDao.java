package com.treasure.hunt.dao;

import com.treasure.hunt.entity.WxCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface WxCustomerDao extends JpaRepository<WxCustomer, Long>, JpaSpecificationExecutor<WxCustomer> {

    /**
     * 根据openId查找微信用户
     *
     * @param openId
     * @return
     */
    WxCustomer findByOpenId(String openId);

    /**
     * 根据用户Id集合查找用户
     *
     * @param customerIds
     * @return
     */
    List<WxCustomer> findByCustomerIdIn(List<Long> customerIds);

    @Query(value = "SELECT * FROM (" +
            "SELECT a.customer_id,(@rowno \\:=@rowno+1) AS rowno FROM wx_customer a,(" +
            "SELECT (@rowno \\:=0)) b ORDER BY a.integral DESC,create_time ASC) c WHERE c.customer_id=?1", nativeQuery = true)
    Map<String, Object> getRowNumByCustomerId(Long customerId);
}
