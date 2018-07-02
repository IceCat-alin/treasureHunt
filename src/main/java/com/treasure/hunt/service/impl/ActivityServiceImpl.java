package com.treasure.hunt.service.impl;

import com.treasure.hunt.common.Constant;
import com.treasure.hunt.common.ListBeanUtil;
import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.PageSort;
import com.treasure.hunt.dao.*;
import com.treasure.hunt.dto.ActivityDto;
import com.treasure.hunt.entity.*;
import com.treasure.hunt.framework.database.Criteria;
import com.treasure.hunt.framework.database.Restrictions;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ActivityService;
import com.treasure.hunt.service.WxAuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private ActivityLikeDao activityLikeDao;

    @Autowired
    private ActivityJoinDao activityJoinDao;

    @Autowired
    private ActivityTypeDao activityTypeDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ActivityStatisticsDao activityStatisticsDao;

    @Autowired
    private WxCustomerDao wxCustomerDao;

    @Autowired
    private WxAuthService wxAuthService;

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
        activity.setStatus(ActivityDto.STATUS_AUDIT);
        activity.setUpdateTime(new Date());
        activity.setCreateTime(new Date());
        try {
            activity.setQrCode(wxAuthService.getQrCode("pages/detail/detail?activityId=" + activity.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        activity = activityDao.save(activity);

        ActivityStatistics activityStatistics = new ActivityStatistics();
        activityStatistics.setActivityId(activity.getId());
        activityStatistics.setCreateTime(new Date());
        activityStatistics.setUpdateTime(new Date());
        activityStatisticsDao.save(activityStatistics);

        addImage(activityDto.getImages(), activity.getId());
    }

    private void addImage(String[] images, Long activityId) {
        if (images != null && images.length > 0) {
            List<ActivityImage> imageList = new ArrayList<>();
            for (int i = 0; i < images.length; i++) {
                ActivityImage activityImage = new ActivityImage();
                activityImage.setUpdateTime(new Date());
                activityImage.setCreateTime(new Date());
                activityImage.setActivityId(activityId);
                activityImage.setImageUrl(images[i]);
                imageList.add(activityImage);
            }
            activityImageDao.saveAll(imageList);
        }
    }


    /**
     * 修改活动
     *
     * @param activityDto
     * @throws BusinessException
     */
    @Override
    public void updateActivity(ActivityDto activityDto) throws BusinessException {
        Optional<Activity> activityOptional = activityDao.findById(activityDto.getId());
        if (!activityOptional.isPresent()) {
            throw new BusinessException("找不到id：" + activityDto.getId() + "的活动");
        }
        Activity activity = activityOptional.get();
        ListBeanUtil.copyProperties(activityDto, activity, "status", "qrCode", "createTime");
        activity.setUpdateTime(new Date());
        activityDao.save(activity);
        // 重新添加图片
        activityImageDao.deleteByActivityId(activity.getId());
        addImage(activityDto.getImages(), activity.getId());

    }

    /**
     * 审核活动
     *
     * @param activityId 活动Id
     * @param status     活动状态
     * @throws BusinessException 业务异常
     */
    @Override
    public void setActivityStatus(Long activityId, Byte status) throws BusinessException {
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

        List<ActivityDto> activityDtos = packData(page.getContent());


        return new PageList(activityDtos, page.getTotalElements(), page.getTotalPages());
    }

    /**
     * 打包数据
     *
     * @param domainList
     * @return
     * @throws BusinessException
     */
    @Override
    public List<ActivityDto> packData(List<Activity> domainList) throws BusinessException {
        List<ActivityDto> activityDtos = ListBeanUtil.listCopy(domainList, ActivityDto.class);
        if (activityDtos != null && !activityDtos.isEmpty()) {
            //  评论数，点赞数
            List<Long> activityIds = ListBeanUtil.toList(activityDtos, "id");
            List<ActivityStatistics> statisticsList = activityStatisticsDao.findByActivityIdIn(activityIds);
            Map<Long, ActivityStatistics> statisticsMap = ListBeanUtil.toMap(statisticsList, "activityId");

            List<Long> typeIds = ListBeanUtil.toList(activityDtos, "typeId");
            List<ActivityType> activityTypes = activityTypeDao.findByIdIn(typeIds);
            Map<Long, ActivityType> activityTypeMap = ListBeanUtil.toMap(activityTypes, "id");

            List<ActivityImage> activityImages = activityImageDao.findByActivityIdIn(activityIds);
            Map<Long, List<ActivityImage>> imageMap = ListBeanUtil.toMapList(activityImages, activityIds, "activityId");

            for (ActivityDto activityDto1 : activityDtos) {
                ActivityStatistics statistics = statisticsMap.get(activityDto1.getId());
                if (statistics != null) {
                    ListBeanUtil.copyProperties(statistics, activityDto1, "id", "createTime", "updateTime");
                }

                ActivityType activityType = activityTypeMap.get(activityDto1.getTypeId());
                if (activityType != null) {
                    activityDto1.setTypeName(activityType.getName());
                }
                List<ActivityImage> activityImageList = imageMap.get(activityDto1.getId());
                if (activityImageList != null && !activityImageList.isEmpty()) {
                    activityDto1.setImageList(activityImageList);
                }
            }
        }
        return activityDtos;
    }

    /**
     * 获取活动信息
     *
     * @param activityId 活动Id
     * @return 活动信息
     * @throws BusinessException
     */
    @Override
    public ActivityDto getActivity(Long activityId) throws BusinessException {
        Optional<Activity> activity = activityDao.findById(activityId);
        if (!activity.isPresent()) {
            throw new BusinessException("找不到id：" + activityId + "的活动");
        }
        ActivityDto activityDto = new ActivityDto();
        ListBeanUtil.copyProperties(activity.get(), activityDto);
        Optional<ActivityType> activityType = activityTypeDao.findById(activityDto.getTypeId());
        activityType.ifPresent(activityType1 -> activityDto.setTypeName(activityType1.getName()));

        // 获取活动图片
        List<ActivityImage> imageList = activityImageDao.findByActivityId(activityId);
        activityDto.setImageList(imageList);
        // 获取活动统计
        ActivityStatistics activityStatistics = activityStatisticsDao.findByActivityId(activityId);
        if (activityStatistics != null) {
            ListBeanUtil.copyProperties(activityStatistics, activityDto, "id", "createTime", "updateTime");
        }
        // 获取发起人信息
        Optional<WxCustomer> wxCustomer = wxCustomerDao.findById(activityDto.getCustomerId());
        if (!wxCustomer.isPresent()) {
            throw new BusinessException("找不到id：" + activityDto.getCustomerId() + "的活动发起人");
        }
        activityDto.setCustomerName(wxCustomer.get().getNickName());
        activityDto.setCustomerImg(wxCustomer.get().getAvatarUrl());

        return activityDto;
    }

    /**
     * 删除活动
     *
     * @param activityId
     * @throws BusinessException
     */
    @Override
    public void deleteActivity(Long activityId) throws BusinessException {
        Optional<Activity> activity = activityDao.findById(activityId);
        if (!activity.isPresent()) {
            throw new BusinessException("找不到id：" + activityId + "的活动");
        }
        activityDao.deleteById(activityId);

        // 删除活动统计
        ActivityStatistics activityStatistics = activityStatisticsDao.findByActivityId(activityId);
        if (activityStatistics != null) {
            activityStatisticsDao.delete(activityStatistics);
        }
        // 删除活动图片
        List<ActivityImage> imageList = activityImageDao.findByActivityId(activityId);
        if (imageList != null && !imageList.isEmpty()) {
            activityImageDao.deleteAll(imageList);
        }
        // 删除活动评论
        List<Comment> commentList = commentDao.findByActivityId(activityId);
        if (commentList != null && !commentList.isEmpty()) {
            commentDao.deleteAll(commentList);
        }
        // 删除活动点赞
        List<ActivityJoin> joinList = activityJoinDao.findByActivityId(activityId);
        if (joinList != null && !joinList.isEmpty()) {
            activityJoinDao.deleteAll(joinList);
        }
        // 删除活动加入
        List<ActivityLike> likeList = activityLikeDao.findByActivityId(activityId);
        if (likeList != null && !likeList.isEmpty()) {
            activityLikeDao.deleteAll(likeList);
        }

    }

    /**
     * 获取用户在该活动的点赞和加入状态
     *
     * @param activityId
     * @param customerId
     * @return
     */
    @Override
    public Map<String, Boolean> getLikeAndJoinStatus(Long activityId, Long customerId) {
        Map<String, Boolean> map = new HashMap<>(16);
        // 获取是否点赞
        ActivityLike activityLike = activityLikeDao.findByCustomerIdAndActivityId(customerId, activityId);
        if (activityLike != null) {
            map.put("isLike", true);
        } else {
            map.put("isLike", false);
        }
        // 获取是否加入
        ActivityJoin activityJoin = activityJoinDao.findByCustomerIdAndActivityId(customerId, activityId);
        if (activityJoin != null) {
            map.put("isJoin", true);
        } else {
            map.put("isJoin", false);
        }
        return map;
    }

    /**
     * @param activityDto
     * @return
     * @Description 分页查询条件
     * @Date 2018/4/17 9:39
     **/
    private Criteria<Activity> queryCondition(ActivityDto activityDto) {
        Criteria<Activity> criteria = new Criteria<>();
        if (activityDto.getTypeId() != null) {
            criteria.add(Restrictions.eq("typeId", activityDto.getTypeId(), true));
        }
        if (StringUtils.isNotBlank(activityDto.getTitle())) {
            criteria.add(Restrictions.like("title", "%" + activityDto.getTitle() + "%", true));
        }
        if (activityDto.getCustomerId() != null) {
            criteria.add(Restrictions.eq("customerId", activityDto.getCustomerId(), true));
        }
        if (activityDto.getStatus() != null) {
            criteria.add(Restrictions.eq("status", activityDto.getStatus(), true));
        }
        return criteria;
    }
}
