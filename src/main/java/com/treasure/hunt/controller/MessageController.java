package com.treasure.hunt.controller;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.ResultInfo;
import com.treasure.hunt.dto.MessageDto;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/25 10:17
 * @Version 版本号：v1.0.0
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 获取消息列表
     *
     * @param pageNo
     * @param pageSize
     * @param customerId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getMessagePage")
    @ResponseBody
    public ResultInfo getMessagePage(Integer pageNo, Integer pageSize, Long customerId) throws BusinessException {
        PageList<MessageDto> messagePage = messageService.getMessagePage(pageNo, pageSize, customerId);
        return ResultInfo.success("分页查询消息成功", messagePage);
    }


    /**
     * 设置消息为已读
     *
     * @param messageId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/setMessageRead")
    @ResponseBody
    public ResultInfo setMessageRead(Long messageId) throws BusinessException {
        messageService.setMessageRead(messageId);
        return ResultInfo.success("设置已读成功", null);
    }

    /**
     * 删除消息
     *
     * @param messageId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/deleteMessage")
    @ResponseBody
    public ResultInfo deleteMessage(Long messageId) throws BusinessException {
        messageService.deleteMessage(messageId);
        return ResultInfo.success("删除消息成功", null);
    }

    /**
     * 获取未读消息数
     *
     * @return
     */
    @RequestMapping("/getUnReadNum")
    @ResponseBody
    public ResultInfo getUnReadNum() {
        return ResultInfo.success("获取未读消息数成功", messageService.getUnReadNum());
    }
}