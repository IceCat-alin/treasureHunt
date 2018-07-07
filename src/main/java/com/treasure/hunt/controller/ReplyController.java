package com.treasure.hunt.controller;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.ResultInfo;
import com.treasure.hunt.entity.Reply;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ReplyService;
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
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    /**
     * 获取回复列表
     *
     * @param pageNo
     * @param pageSize
     * @param commentId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getReplyPage")
    @ResponseBody
    public ResultInfo getReplyPage(Integer pageNo, Integer pageSize, Long commentId) throws BusinessException {
        PageList<Reply> replyPageList = replyService.getReplyPage(pageNo, pageSize, commentId);
        return ResultInfo.success("分页查询回复成功", replyPageList);
    }

    /**
     * 新增回复
     *
     * @param commentId
     * @param customerId
     * @param content
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/addReply")
    @ResponseBody
    public ResultInfo addReply(Long commentId, Long customerId, String content) throws BusinessException {
        return ResultInfo.success("新增回复成功", replyService.addReply(commentId, customerId, content));
    }

    /**
     * 删除回复
     *
     * @param replyId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/deleteReply")
    @ResponseBody
    public ResultInfo deleteReply(Long replyId) throws BusinessException {
        replyService.deleteReply(replyId);
        return ResultInfo.success("删除回复成功", null);
    }
}