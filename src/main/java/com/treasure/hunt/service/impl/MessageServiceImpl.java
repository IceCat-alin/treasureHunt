package com.treasure.hunt.service.impl;

import com.treasure.hunt.common.Constant;
import com.treasure.hunt.common.ListBeanUtil;
import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.PageSort;
import com.treasure.hunt.dao.ActivityDao;
import com.treasure.hunt.dao.MessageDao;
import com.treasure.hunt.dao.WxCustomerDao;
import com.treasure.hunt.dto.MessageDto;
import com.treasure.hunt.entity.Activity;
import com.treasure.hunt.entity.Message;
import com.treasure.hunt.framework.database.Criteria;
import com.treasure.hunt.framework.database.Restrictions;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/7/7 13:59
 * @Version 版本号：v1.0.0
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private WxCustomerDao wxCustomerDao;

    @Autowired
    private ActivityDao activityDao;

    /**
     * 分页查找消息
     *
     * @param pageNo
     * @param pageSize
     * @param customerId
     * @return
     * @throws BusinessException
     */
    @Override
    public PageList<MessageDto> getMessagePage(Integer pageNo, Integer pageSize, Long customerId) throws BusinessException {
        int currentPage = pageNo != null && pageNo > 0 ? pageNo - 1 : Constant.DEFAULT_PAGE;
        int currentSize = pageSize != null && pageSize > 0 ? pageSize : Constant.DEFAULT_SIZE;
        PageRequest pageable = PageRequest.of(currentPage, currentSize, PageSort.getSort("ASC", "status").and(PageSort.getSort("DESC", "createTime")));

        Criteria<Message> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("toCustomerId", customerId, true));

        Page<Message> page = messageDao.findAll(criteria, pageable);

        List<Message> domainList = page.getContent();
        List<MessageDto> messageDtos = ListBeanUtil.listCopy(domainList, MessageDto.class);

        return new PageList(messageDtos, page.getTotalElements(), page.getTotalPages());
    }

    /**
     * 新增消息
     *
     * @param activityId
     * @param content
     * @param type
     * @throws BusinessException
     */
    @Override
    public void addMessage(Long activityId, String content, Byte type) throws BusinessException {
        Message message = new Message();
        message.setActivityId(activityId);
        message.setCustomerId(0L);
        Optional<Activity> activity = activityDao.findById(activityId);
        if (!activity.isPresent()) {
            message.setToCustomerId(0L);
        } else {
            message.setToCustomerId(activity.get().getCustomerId());
        }
        message.setContent(content);
        message.setType(type);
        message.setStatus(MessageDto.READ_FALSE);
        message.setUpdateTime(new Date());
        message.setCreateTime(new Date());
        messageDao.save(message);
    }

    /**
     * 标记为已读
     *
     * @param messageId
     * @throws BusinessException
     */
    @Override
    public void setMessageRead(Long messageId) throws BusinessException {
        Optional<Message> message = messageDao.findById(messageId);
        if (!message.isPresent()) {
            throw new BusinessException("找不到id：" + messageId + "的消息");
        }
        message.get().setStatus(MessageDto.READ_TRUE);
        messageDao.save(message.get());
    }
}
