package com.treasure.hunt.dao;

import com.treasure.hunt.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/19 11:36
 * @Version 版本号：v1.0.0
 */
public interface ActivityDao extends JpaRepository<Activity, Long>,JpaSpecificationExecutor<Activity> {
}
