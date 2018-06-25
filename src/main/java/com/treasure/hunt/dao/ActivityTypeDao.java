package com.treasure.hunt.dao;

import com.treasure.hunt.entity.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityTypeDao extends JpaRepository<ActivityType, Long>, JpaSpecificationExecutor<ActivityType> {

    /**
     * 根据用户Id集合查找用户
     *
     * @param ids
     * @return
     */
    List<ActivityType> findByIdIn(List<Long> ids);

}
