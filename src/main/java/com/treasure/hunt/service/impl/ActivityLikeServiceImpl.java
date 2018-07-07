package com.treasure.hunt.service.impl;

import com.treasure.hunt.common.Constant;
import com.treasure.hunt.common.ListBeanUtil;
import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.PageSort;
import com.treasure.hunt.dao.ActivityLikeDao;
import com.treasure.hunt.dao.WxCustomerDao;
import com.treasure.hunt.dto.ActivityLikeDto;
import com.treasure.hunt.dto.MessageDto;
import com.treasure.hunt.entity.ActivityLike;
import com.treasure.hunt.entity.WxCustomer;
import com.treasure.hunt.framework.database.Criteria;
import com.treasure.hunt.framework.database.Restrictions;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ActivityLikeService;
import com.treasure.hunt.service.ActivityStatisticsService;
import com.treasure.hunt.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(rollbackFor = Exception.class)
public class ActivityLikeServiceImpl implements ActivityLikeService {

    @Autowired
    private ActivityLikeDao activityLikeDao;

    @Autowired
    private WxCustomerDao wxCustomerDao;

    @Autowired
    private ActivityStatisticsService activityStatisticsService;

    @Autowired
    private MessageService messageService;

    @Override
    public void likeActivity(Long activityId, Long customerId) throws BusinessException {
        ActivityLike activityLike = activityLikeDao.findByCustomerIdAndActivityId(customerId, activityId);
        if (activityLike != null) {
            activityLikeDao.delete(activityLike);
            activityStatisticsService.updateStatistics(activityId, "like", "sub");
        } else {
            activityLike = new ActivityLike();
            activityLike.setActivityId(activityId);
            activityLike.setCustomerId(customerId);
            activityLike.setCreateTime(new Date());
            activityLike.setUpdateTime(new Date());
            activityLikeDao.save(activityLike);
            activityStatisticsService.updateStatistics(activityId, "like", "add");
            messageService.addMessage(activityId, "您的藏宝有新点赞了", MessageDto.TYPE_LIKE);
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

    @Override
    public PageList<ActivityLikeDto> getActivityLikePage(Integer pageNo, Integer pageSize, Long activityId) throws BusinessException {
        int currentPage = pageNo != null && pageNo > 0 ? pageNo - 1 : Constant.DEFAULT_PAGE;
        int currentSize = pageSize != null && pageSize > 0 ? pageSize : Constant.DEFAULT_SIZE;
        PageRequest pageable = PageRequest.of(currentPage, currentSize, PageSort.getSort("DESC", "createTime"));

        Criteria<ActivityLike> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("activityId", activityId, true));

        Page<ActivityLike> page = activityLikeDao.findAll(criteria, pageable);

        List<ActivityLike> domainList = page.getContent();
        List<ActivityLikeDto> activityLikeDtos = ListBeanUtil.listCopy(domainList, ActivityLikeDto.class);

        List<Long> customerIds = ListBeanUtil.toList(activityLikeDtos, "customerId");
        List<WxCustomer> wxCustomerList = wxCustomerDao.findByCustomerIdIn(customerIds);
        Map<Long, WxCustomer> wxCustomerMap = ListBeanUtil.toMap(wxCustomerList, "customerId");

        for (ActivityLikeDto activityLikeDto : activityLikeDtos) {
            WxCustomer wxCustomer = wxCustomerMap.get(activityLikeDto.getCustomerId());
            if (wxCustomer != null) {
                activityLikeDto.setCustomerImg(wxCustomer.getAvatarUrl());
                activityLikeDto.setCustomerName(wxCustomer.getNickName());
            }
        }

        return new PageList(activityLikeDtos, page.getTotalElements(), page.getTotalPages());
    }
}
