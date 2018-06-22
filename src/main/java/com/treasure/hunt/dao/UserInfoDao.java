package com.treasure.hunt.dao;

import com.treasure.hunt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserInfoDao extends JpaRepository<User, Long>,JpaSpecificationExecutor<User>{

    /**
     * 根据用户名查找数据
     * @param userName
     * @param password
     * @return
     */
    User findByUserNameAndPassword(String userName,String password);
}
