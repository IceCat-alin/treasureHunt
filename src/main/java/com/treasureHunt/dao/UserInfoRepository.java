package com.treasureHunt.dao;

import com.treasureHunt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserInfoRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User>{

    /**
     * 根据用户名查找数据
     * @param userName
     * @param password
     * @return
     */
    User findByUserNameAndPassword(String userName,String password);
}
