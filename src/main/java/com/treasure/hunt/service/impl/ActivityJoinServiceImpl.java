package com.treasure.hunt.service.impl;

import com.treasure.hunt.dao.ActivityLikeDao;
import com.treasure.hunt.entity.ActivityLike;
import com.treasure.hunt.service.ActivityLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/20 14:49
 * @Version 版本号：v1.0.0
 */
@Service("activityLikeService")
public class ActivityLikeServiceImpl implements ActivityLikeService {

    @Autowired
    private ActivityLikeDao activityLikeDao;

    @Override
    public void likeActivity(Long activityId, Long customerId) {
        ActivityLike activityLike = activityLikeDao.findByCustomerIdAndActivityId(customerId, activityId);
        if (activityLike != null) {
            activityLikeDao.delete(activityLike);
        } else {
            activityLike = new ActivityLike();
            activityLike.setActivityId(activityId);
            activityLike.setCustomerId(customerId);
            activityLike.setCreateTime(new Date());
            activityLike.setUpdateTime(new Date());
            activityLikeDao.save(activityLike);
        }
    }

    /**
     * 分组统计每个活动点赞数
     *
     * @param activityIds
     * @return
     */
    @Override
    public Map<Long, Long> groupByActivityId(List<Long> activityIds) {
        List<Object[]> list = activityLikeDao.groupByActivityId(activityIds);
        Map<Long, Long> map = new HashMap<>(16);

        for (Object[] objects : list) {
            System.out.println(objects[0]);
            map.put(Long.parseLong(objects[0].toString()), Long.parseLong(objects[1].toString()));
        }
        return map;
    }
}
