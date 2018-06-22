package com.treasure.hunt.service.impl;

import com.treasure.hunt.common.Constant;
import com.treasure.hunt.common.ListBeanUtil;
import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.PageSort;
import com.treasure.hunt.dao.ActivityDao;
import com.treasure.hunt.dao.ActivityImageDao;
import com.treasure.hunt.dto.ActivityDto;
import com.treasure.hunt.entity.Activity;
import com.treasure.hunt.framework.database.Criteria;
import com.treasure.hunt.framework.database.Restrictions;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ActivityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/19 15:27
 * @Version 版本号：v1.0.0
 */
@Service("activityService")
@Transactional(rollbackFor = Exception.class)
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private ActivityImageDao activityImageDao;

    /**
     * 新增寻宝活动
     *
     * @param activityDto 活动信息
     * @throws BusinessException 业务异常
     */
    @Override
    public void addActivity(ActivityDto activityDto) throws BusinessException {
        Activity activity = new Activity();
        ListBeanUtil.copyProperties(activityDto, activity);
        if (activityDto.getImageList() != null && !activityDto.getImageList().isEmpty()) {
            activityImageDao.saveAll(activityDto.getImageList());
        }
        activityDao.save(activity);
    }

    /**
     * 审核活动
     *
     * @param activityId 活动Id
     * @param status     活动状态
     * @throws BusinessException 业务异常
     */
    @Override
    public void checkActivity(Long activityId, Byte status) throws BusinessException {
        Optional<Activity> activity = activityDao.findById(activityId);
        if (!activity.isPresent()) {
            throw new BusinessException("找不到id：" + activityId + "的活动");
        }
        activity.get().setStatus(status);
        activity.get().setUpdateTime(new Date());
        activityDao.save(activity.get());
    }

    /**
     * 分页获取活动
     *
     * @param pageNo      页码
     * @param pageSize    条数
     * @param sort        排序
     * @param sortField   排序字段
     * @param activityDto 查询条件
     * @return 分页数据
     */
    @Override
    public PageList<ActivityDto> getActivityPage(Integer pageNo, Integer pageSize, String sort, String sortField, ActivityDto activityDto) throws BusinessException {
        int currentPage = pageNo != null && pageNo > 0 ? pageNo - 1 : Constant.DEFAULT_PAGE;
        int currentSize = pageSize != null && pageSize > 0 ? pageSize : Constant.DEFAULT_SIZE;
        PageRequest pageable = PageRequest.of(currentPage, currentSize, PageSort.getSort(sort, sortField));

        Criteria<Activity> criteria = queryCondition(activityDto);

        Page<Activity> page = activityDao.findAll(criteria, pageable);

        List<Activity> domainList = page.getContent();
        List<ActivityDto> activityDtos = ListBeanUtil.listCopy(domainList, ActivityDto.class);

        return new PageList(activityDtos, page.getTotalElements(), page.getTotalPages());
    }

    /**
     * @param activityDto
     * @return
     * @Description 分页查询条件
     * @Date 2018/4/17 9:39
     **/
    private Criteria<Activity> queryCondition(ActivityDto activityDto) {
        Criteria<Activity> criteria = new Criteria<Activity>();
        if (activityDto.getTypeId() != null) {
            criteria.add(Restrictions.eq("typeId", activityDto.getTypeId(), true));
        }
        if (StringUtils.isNotBlank(activityDto.getTitle())) {
            criteria.add(Restrictions.like("title", "%" + activityDto.getTitle() + "%", true));
        }
        return criteria;
    }
}
