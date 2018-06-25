package com.treasure.hunt.service.impl;

import com.treasure.hunt.common.Constant;
import com.treasure.hunt.common.ListBeanUtil;
import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.PageSort;
import com.treasure.hunt.dao.ActivityJoinDao;
import com.treasure.hunt.dao.WxCustomerDao;
import com.treasure.hunt.dto.ActivityJoinDto;
import com.treasure.hunt.entity.ActivityJoin;
import com.treasure.hunt.entity.WxCustomer;
import com.treasure.hunt.framework.database.Criteria;
import com.treasure.hunt.framework.database.Restrictions;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ActivityJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
public class ActivityJoinServiceImpl implements ActivityJoinService {

    @Autowired
    private ActivityJoinDao activityJoinDao;

    @Autowired
    private WxCustomerDao wxCustomerDao;

    @Override
    public void joinActivity(Long activityId, Long customerId) {
        ActivityJoin activityJoin = activityJoinDao.findByCustomerIdAndActivityId(customerId, activityId);
        if (activityJoin != null) {
            activityJoinDao.delete(activityJoin);
        } else {
            activityJoin = new ActivityJoin();
            activityJoin.setActivityId(activityId);
            activityJoin.setCustomerId(customerId);
            activityJoin.setCreateTime(new Date());
            activityJoin.setUpdateTime(new Date());
            activityJoinDao.save(activityJoin);
        }
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
}
