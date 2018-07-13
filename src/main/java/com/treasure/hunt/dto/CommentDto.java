package com.treasure.hunt.dto;

import com.treasure.hunt.entity.Reply;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 类描述：评论
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/19 10:55
 * @Version 版本号：v1.0.0
 */
public class CommentDto implements Serializable {

    /**
     * 最佳
     */
    public static final Byte BEST_TRUE = 1;

    /**
     * 非最佳
     */
    public static final Byte BEST_FALSE = 0;

    /**
     * 评论
     */
    public static final Byte TYPE_COMMENT = 1;

    /**
     * 回答
     */
    public static final Byte TYPE_ANSWER = 2;

    /**
     * 帖子
     */
    public static final Byte TYPE_TOPIC = 3;


    private Long id;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 评论用户id
     */
    private Long customerId;

    /**
     * 用户头像
     */
    private String customerImg;

    /**
     * 用户昵称
     */
    private String customerName;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 是否最佳
     */
    private Byte isBest;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 回复
     */
    private Reply reply;

    /**
     * 类型 1.评论2.回答
     */
    private Byte type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Byte getIsBest() {
        return isBest;
    }

    public void setIsBest(Byte isBest) {
        this.isBest = isBest;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
