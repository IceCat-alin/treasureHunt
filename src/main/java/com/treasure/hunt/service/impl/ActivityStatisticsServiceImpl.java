package com.treasure.hunt.service.impl;

import com.treasure.hunt.dao.ActivityStatisticsDao;
import com.treasure.hunt.entity.ActivityStatistics;
import com.treasure.hunt.service.ActivityStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/29 10:30
 * @Version 版本号：v1.0.0
 */
@Service("activityStatisticsService")
@Transactional(rollbackFor = Exception.class)
public class ActivityStatisticsServiceImpl implements ActivityStatisticsService {

    /**
     * 更新浏览数
     */
    private static final String TYPE_VIEW = "view";
    /**
     * 更新点赞数
     */
    private static final String TYPE_LIKE = "like";
    /**
     * 更新加入数
     */
    private static final String TYPE_JOIN = "join";
    /**
     * 更新评论数
     */
    private static final String TYPE_COMMENT = "comment";
    /**
     * 增加
     */
    private static final String STATUS_ADD = "add";

    @Autowired
    private ActivityStatisticsDao activityStatisticsDao;

    /**
     * 更新活动统计
     *
     * @param activityId
     * @param updateType
     * @param updateStatus
     */
    @Override
    public void updateStatistics(Long activityId, String updateType, String updateStatus) {
        ActivityStatistics statistics = activityStatisticsDao.findByActivityId(activityId);
        if (statistics != null) {
            if (TYPE_VIEW.equals(updateType)) {
                if (STATUS_ADD.equals(updateStatus)) {
                    statistics.setViewNum(statistics.getViewNum() + 1);
                } else {
                    statistics.setViewNum(statistics.getViewNum() - 1);
                }
            }
            if (TYPE_LIKE.equals(updateType)) {
                if (STATUS_ADD.equals(updateStatus)) {
                    statistics.setLikeNum(statistics.getLikeNum() + 1);
                } else {
                    statistics.setLikeNum(statistics.getLikeNum() - 1);
                }
            }
            if (TYPE_JOIN.equals(updateType)) {
                if (STATUS_ADD.equals(updateStatus)) {
                    statistics.setJoinNum(statistics.getJoinNum() + 1);
                } else {
                    statistics.setJoinNum(statistics.getJoinNum() - 1);
                }
            }
            if (TYPE_COMMENT.equals(updateType)) {
                if (STATUS_ADD.equals(updateStatus)) {
                    statistics.setCommentNum(statistics.getCommentNum() + 1);
                } else {
                    statistics.setCommentNum(statistics.getCommentNum() - 1);
                }
            }

        }
    }
}
