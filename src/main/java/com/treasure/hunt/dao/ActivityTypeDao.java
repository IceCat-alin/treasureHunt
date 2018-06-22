package com.treasure.hunt.dao;

import com.treasure.hunt.entity.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActivityTypeDao extends JpaRepository<ActivityType, Long>, JpaSpecificationExecutor<ActivityType> {

}
