package com.treasure.hunt.service.impl;

import com.treasure.hunt.common.Constant;
import com.treasure.hunt.common.ListBeanUtil;
import com.treasure.hunt.common.PageList;
import com.treasure.hunt.dao.CommentDao;
import com.treasure.hunt.dao.ReplyDao;
import com.treasure.hunt.dao.WxCustomerDao;
import com.treasure.hunt.dto.CommentDto;
import com.treasure.hunt.dto.MessageDto;
import com.treasure.hunt.entity.Comment;
import com.treasure.hunt.entity.Reply;
import com.treasure.hunt.entity.WxCustomer;
import com.treasure.hunt.framework.database.Criteria;
import com.treasure.hunt.framework.database.Restrictions;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ActivityStatisticsService;
import com.treasure.hunt.service.CommentService;
import com.treasure.hunt.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/25 9:40
 * @Version 版本号：v1.0.0
 */
@Service("commentService")
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private WxCustomerDao wxCustomerDao;

    @Autowired
    private ActivityStatisticsService activityStatisticsService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ReplyDao replyDao;

    /**
     * 新增评论
     *
     * @param commentDto
     * @throws BusinessException
     */
    @Override
    public Comment addComment(CommentDto commentDto) throws BusinessException {
        Comment comment = new Comment();
        ListBeanUtil.copyProperties(commentDto, comment);
        comment.setUpdateTime(new Date());
        comment.setCreateTime(new Date());
        comment.setIsBest(CommentDto.BEST_FALSE);
        comment = commentDao.save(comment);
        activityStatisticsService.updateStatistics(commentDto.getActivityId(), "comment", "add");
        String msg;
        if (CommentDto.TYPE_TOPIC.equals(comment.getType())) {
            msg = "您的帖子有新回帖了";
        } else {
            msg = "您的藏宝有新评论了";
        }
        messageService.addMessage(comment.getActivityId(), msg, MessageDto.TYPE_COMMENT);
        return comment;
    }

    /**
     * 获取评论列表
     *
     * @param pageNo
     * @param pageSize
     * @param activityId
     * @return
     * @throws BusinessException
     */
    @Override
    public PageList<CommentDto> getCommentPage(Integer pageNo, Integer pageSize, Long activityId, Byte type, Byte isBest) throws BusinessException {
        int currentPage = pageNo != null && pageNo > 0 ? pageNo - 1 : Constant.DEFAULT_PAGE;
        int currentSize = pageSize != null && pageSize > 0 ? pageSize : Constant.DEFAULT_SIZE;
        PageRequest pageable = PageRequest.of(currentPage, currentSize, new Sort(Sort.Direction.ASC, "createTime"));

        Criteria<Comment> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("activityId", activityId, true));
        if (type != null) {
            criteria.add(Restrictions.eq("type", type, true));
        }
        if (isBest != null) {
            criteria.add(Restrictions.eq("isBest", isBest, true));
        }

        Page<Comment> page = commentDao.findAll(criteria, pageable);

        List<Comment> domainList = page.getContent();
        List<CommentDto> commentDtos = ListBeanUtil.listCopy(domainList, CommentDto.class);

        if (commentDtos != null && !commentDtos.isEmpty()) {
            List<Long> commentIds = ListBeanUtil.toList(commentDtos, "id");
            List<Long> customerIds = ListBeanUtil.toList(commentDtos, "customerId");

            List<WxCustomer> wxCustomerList = wxCustomerDao.findByCustomerIdIn(customerIds);
            Map<Long, WxCustomer> wxCustomerMap = ListBeanUtil.toMap(wxCustomerList, "customerId");

            List<Reply> replyList = replyDao.findByCommentIdsGroupByCommentId(commentIds);
            Map<Long, Reply> replyMap = ListBeanUtil.toMap(replyList, "commentId");

            for (CommentDto commentDto : commentDtos) {
                WxCustomer wxCustomer = wxCustomerMap.get(commentDto.getCustomerId());
                if (wxCustomer != null) {
                    commentDto.setCustomerImg(wxCustomer.getAvatarUrl());
                    commentDto.setCustomerName(wxCustomer.getNickName());
                }
                Reply reply = replyMap.get(commentDto.getId());
                if (reply != null) {
                    commentDto.setReply(reply);
                }
            }
        }

        return new PageList(commentDtos, page.getTotalElements(), page.getTotalPages());
    }

    /**
     * 设置最佳答案
     *
     * @param commentId
     * @throws BusinessException
     */
    @Override
    public void setBestComment(Long commentId) throws BusinessException {
        Optional<Comment> comment = commentDao.findById(commentId);
        if (!comment.isPresent()) {
            throw new BusinessException("找不到id：" + commentId + "的评论");
        }
        // 用户积分+1
        Optional<WxCustomer> wxCustomer = wxCustomerDao.findById(comment.get().getCustomerId());
        if (!wxCustomer.isPresent()) {
            throw new BusinessException("找不到id：" + comment.get().getCustomerId() + "的用户");
        }
        if (CommentDto.BEST_TRUE.equals(comment.get().getIsBest())) {
            comment.get().setIsBest(CommentDto.BEST_FALSE);
            wxCustomer.get().setIntegral(wxCustomer.get().getIntegral() - 1);
        } else {
            comment.get().setIsBest(CommentDto.BEST_TRUE);
            wxCustomer.get().setIntegral(wxCustomer.get().getIntegral() + 1);
        }
        commentDao.save(comment.get());
        wxCustomerDao.save(wxCustomer.get());
    }

    /**
     * 分组统计每个活动评论数
     *
     * @param activityIds
     * @return
     */
    @Override
    public Map<Long, Long> groupByActivityId(List<Long> activityIds) {
        List<Object[]> list = commentDao.groupByActivityId(activityIds);
        Map<Long, Long> map = new HashMap<>(16);

        for (Object[] objects : list) {
            map.put(Long.parseLong(objects[0].toString()), Long.parseLong(objects[1].toString()));
        }
        return map;
    }

    /**
     * 删除评论
     *
     * @param commentId
     * @throws BusinessException
     */
    @Override
    public void deleteComment(Long commentId) throws BusinessException {
        Optional<Comment> comment = commentDao.findById(commentId);
        if (!comment.isPresent()) {
            throw new BusinessException("找不到id：" + commentId + "的评论");
        }
        if (CommentDto.TYPE_ANSWER.equals(comment.get().getType())) {
            throw new BusinessException("该评论是回答，无法删除");
        }
        commentDao.deleteById(commentId);
        activityStatisticsService.updateStatistics(comment.get().getActivityId(), "comment", "sub");
    }
}
