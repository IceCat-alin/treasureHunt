package com.treasure.hunt.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/7/7 10:31
 * @Version 版本号：v1.0.0
 */
public class MessageDto implements Serializable {

    /**
     * 已读
     */
    public static final Byte READ_TRUE = 1;

    /**
     * 未读
     */
    public static final Byte READ_FALSE = 0;

    /**
     * 点赞
     */
    public static final Byte TYPE_LIKE = 1;

    /**
     * 评论
     */
    public static final Byte TYPE_COMMENT = 2;

    /**
     * 审核
     */
    public static final Byte TYPE_AUDIT = 3;

    /**
     * 审核
     */
    public static final Byte TYPE_JOIN = 4;

    private Long id;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 发送人Id
     */
    private Long customerId;

    /**
     * 昵称
     */
    private String customerName;

    /**
     * 头像
     */
    private String customerImg;

    /**
     * 接收人
     */
    private Long toCustomerId;

    /**
     * 接收内容
     */
    private String content;

    /**
     * 类型
     */
    private Byte type;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getToCustomerId() {
        return toCustomerId;
    }

    public void setToCustomerId(Long toCustomerId) {
        this.toCustomerId = toCustomerId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }
}
