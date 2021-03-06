package com.treasure.hunt.service.impl;

import com.treasure.hunt.common.Constant;
import com.treasure.hunt.common.ListBeanUtil;
import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.PageSort;
import com.treasure.hunt.dao.ActivityDao;
import com.treasure.hunt.dao.ActivityJoinDao;
import com.treasure.hunt.dao.WxCustomerDao;
import com.treasure.hunt.dto.ActivityDto;
import com.treasure.hunt.dto.ActivityJoinDto;
import com.treasure.hunt.dto.MessageDto;
import com.treasure.hunt.entity.Activity;
import com.treasure.hunt.entity.ActivityJoin;
import com.treasure.hunt.entity.WxCustomer;
import com.treasure.hunt.framework.database.Criteria;
import com.treasure.hunt.framework.database.Restrictions;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ActivityJoinService;
import com.treasure.hunt.service.ActivityService;
import com.treasure.hunt.service.ActivityStatisticsService;
import com.treasure.hunt.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/20 14:49
 * @Version 版本号：v1.0.0
 */
@Service("activityJoinService")
@Transactional(rollbackFor = Exception.class)
public class ActivityJoinServiceImpl implements ActivityJoinService {

    @Autowired
    private ActivityJoinDao activityJoinDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private WxCustomerDao wxCustomerDao;

    @Autowired
    private ActivityStatisticsService activityStatisticsService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private MessageService messageService;

    @Override
    public ActivityJoin joinActivity(Long activityId, Long customerId) throws BusinessException {
        ActivityJoin activityJoin = activityJoinDao.findByCustomerIdAndActivityId(customerId, activityId);
        if (activityJoin != null) {
            activityJoinDao.delete(activityJoin);
            activityStatisticsService.updateStatistics(activityId, "join", "sub");
        } else {
            activityJoin = new ActivityJoin();
            activityJoin.setActivityId(activityId);
            activityJoin.setCustomerId(customerId);
            activityJoin.setCreateTime(new Date());
            activityJoin.setUpdateTime(new Date());
            activityJoinDao.save(activityJoin);
            activityStatisticsService.updateStatistics(activityId, "join", "add");
            messageService.addMessage(activityId, "您的藏宝有新人加入了", MessageDto.TYPE_JOIN);
        }
        return activityJoin;
    }

    @Override
    public PageList<ActivityJoinDto> getActivityJoinPage(Integer pageNo, Integer pageSize, Long activityId) throws BusinessException {
        int currentPage = pageNo != null && pageNo > 0 ? pageNo - 1 : Constant.DEFAULT_PAGE;
        int currentSize = pageSize != null && pageSize > 0 ? pageSize : Constant.DEFAULT_SIZE;
        PageRequest pageable = PageRequest.of(currentPage, currentSize, PageSort.getSort("DESC", "createTime"));

        Criteria<ActivityJoin> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("activityId", activityId, true));

        Page<ActivityJoin> page = activityJoinDao.findAll(criteria, pageable);

        List<ActivityJoin> domainList = page.getContent();
        List<ActivityJoinDto> activityLikeDtos = ListBeanUtil.listCopy(domainList, ActivityJoinDto.class);

        List<Long> customerIds = ListBeanUtil.toList(activityLikeDtos, "customerId");
        List<WxCustomer> wxCustomerList = wxCustomerDao.findByCustomerIdIn(customerIds);
        Map<Long, WxCustomer> wxCustomerMap = ListBeanUtil.toMap(wxCustomerList, "customerId");

        for (ActivityJoinDto activityJoinDto : activityLikeDtos) {
            WxCustomer wxCustomer = wxCustomerMap.get(activityJoinDto.getCustomerId());
            if (wxCustomer != null) {
                activityJoinDto.setCustomerImg(wxCustomer.getAvatarUrl());
                activityJoinDto.setCustomerName(wxCustomer.getNickName());
            }
        }

        return new PageList(activityLikeDtos, page.getTotalElements(), page.getTotalPages());
    }

    /**
     * 获取我的加入
     *
     * @param pageNo
     * @param pageSize
     * @param customerId
     * @return
     * @throws BusinessException
     */
    @Override
    public PageList<ActivityDto> getMyJoin(Integer pageNo, Integer pageSize, Long customerId) throws BusinessException {
        int currentPage = pageNo != null && pageNo > 0 ? pageNo - 1 : Constant.DEFAULT_PAGE;
        int currentSize = pageSize != null && pageSize > 0 ? pageSize : Constant.DEFAULT_SIZE;
        PageRequest pageable = PageRequest.of(currentPage, currentSize, PageSort.getSort("DESC", "createTime"));

        Criteria<ActivityJoin> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("customerId", customerId, true));

        Page<ActivityJoin> page = activityJoinDao.findAll(criteria, pageable);
        List<ActivityJoin> domainList = page.getContent();
        Map<Long, ActivityJoin> joinMap = ListBeanUtil.toMap(domainList, "activityId");

        List<Long> activityIds = ListBeanUtil.toList(domainList, "activityId");
        List<Activity> wxCustomerList = activityDao.findByIdIn(activityIds);
        List<ActivityDto> activityDtos = activityService.packData(wxCustomerList);
        // 加入时间
        for (ActivityDto activityDto : activityDtos) {
            ActivityJoin activityJoin = joinMap.get(activityDto.getId());
            if (activityJoin != null) {
                activityDto.setJoinTime(activityJoin.getCreateTime());
            }
        }

        return new PageList(activityDtos, page.getTotalElements(), page.getTotalPages());
    }

}
