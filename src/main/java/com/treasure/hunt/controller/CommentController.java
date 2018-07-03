package com.treasure.hunt.controller;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.ResultInfo;
import com.treasure.hunt.dto.CommentDto;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.CommentService;
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
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 获取评论列表
     *
     * @param pageNo
     * @param pageSize
     * @param activityId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getCommentPage")
    @ResponseBody
    public ResultInfo getCommentPage(Integer pageNo, Integer pageSize, Long activityId) throws BusinessException {
        PageList<CommentDto> commentPage = commentService.getCommentPage(pageNo, pageSize, activityId);
        return ResultInfo.success("分页查询评论成功", commentPage);
    }


    /**
     * 设置最佳答案
     *
     * @param commentId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/setBestComment")
    @ResponseBody
    public ResultInfo setBestComment(Long commentId) throws BusinessException {
        commentService.setBestComment(commentId);
        return ResultInfo.success("设置最佳答案成功", null);
    }

    /**
     * 新增评论
     *
     * @param commentDto
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/addComment")
    @ResponseBody
    public ResultInfo addComment(CommentDto commentDto) throws BusinessException {
        return ResultInfo.success("新增评论成功", commentService.addComment(commentDto));
    }

    /**
     * 删除评论
     *
     * @param commentId
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/deleteComment")
    @ResponseBody
    public ResultInfo deleteComment(Long commentId) throws BusinessException {
        commentService.deleteComment(commentId);
        return ResultInfo.success("删除评论成功", null);
    }
}