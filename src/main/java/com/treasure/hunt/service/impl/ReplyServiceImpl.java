package com.treasure.hunt.service.impl;

import com.treasure.hunt.common.Constant;
import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.PageSort;
import com.treasure.hunt.dao.ReplyDao;
import com.treasure.hunt.dao.WxCustomerDao;
import com.treasure.hunt.entity.Reply;
import com.treasure.hunt.entity.WxCustomer;
import com.treasure.hunt.framework.database.Criteria;
import com.treasure.hunt.framework.database.Restrictions;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/7/7 16:37
 * @Version 版本号：v1.0.0
 */
@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private WxCustomerDao wxCustomerDao;

    /**
     * 分页获取回复
     *
     * @param pageNo
     * @param pageSize
     * @param commentId
     * @return
     * @throws BusinessException
     */
    @Override
    public PageList<Reply> getReplyPage(Integer pageNo, Integer pageSize, Long commentId) throws BusinessException {
        int currentPage = pageNo != null && pageNo > 0 ? pageNo - 1 : Constant.DEFAULT_PAGE;
        int currentSize = pageSize != null && pageSize > 0 ? pageSize : Constant.DEFAULT_SIZE;
        PageRequest pageable = PageRequest.of(currentPage, currentSize, PageSort.getSort("ASC", "createTime"));

        Criteria<Reply> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("commentId", commentId, true));

        Page<Reply> page = replyDao.findAll(criteria, pageable);

        return new PageList(page.getContent(), page.getTotalElements(), page.getTotalPages());
    }

    /**
     * 新增回复
     *
     * @param commentId
     * @param customerId
     * @param content
     * @throws BusinessException
     */
    @Override
    public Reply addReply(Long commentId, Long customerId, String content) throws BusinessException {
        Optional<WxCustomer> customer = wxCustomerDao.findById(customerId);
        if (!customer.isPresent()) {
            throw new BusinessException("找不到id：" + customerId + "的用户");
        }
        Reply reply = new Reply();
        reply.setCommentId(commentId);
        reply.setContent(content);
        reply.setCustomerId(customerId);
        reply.setCustomerName(customer.get().getNickName());
        reply.setCreateTime(new Date());
        reply.setUpdateTime(new Date());
        reply = replyDao.save(reply);
        return reply;
    }

    /**
     * 删除回复
     *
     * @param replyId
     * @throws BusinessException
     */
    @Override
    public void deleteReply(Long replyId) throws BusinessException {
        Optional<Reply> reply = replyDao.findById(replyId);
        if (!reply.isPresent()) {
            throw new BusinessException("找不到id：" + replyId + "的回复");
        }
        replyDao.deleteById(replyId);
    }
}
