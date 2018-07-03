package com.treasure.hunt.dao;

import com.treasure.hunt.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/19 11:36
 * @Version 版本号：v1.0.0
 */
public interface ActivityDao extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {

    /**
     * 根据活动Id集合查找活动
     *
     * @param ids
     * @return
     */
    List<Activity> findByIdIn(List<Long> ids);

    /**
     * 查找置顶的活动
     * @return
     */
    List<Activity> findByIsTop(Byte isTop);
}
