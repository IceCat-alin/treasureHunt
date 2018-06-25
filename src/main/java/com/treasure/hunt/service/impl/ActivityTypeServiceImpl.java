package com.treasure.hunt.service.impl;

import com.treasure.hunt.dao.ActivityTypeDao;
import com.treasure.hunt.entity.ActivityType;
import com.treasure.hunt.service.ActivityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/25 9:33
 * @Version 版本号：v1.0.0
 */
@Service("activityTypeService")
@Transactional(rollbackFor = Exception.class)
public class ActivityTypeServiceImpl implements ActivityTypeService {

    @Autowired
    private ActivityTypeDao activityTypeDao;

    @Override
    public List<ActivityType> getTypeList() {
        return activityTypeDao.findAll();
    }
}
