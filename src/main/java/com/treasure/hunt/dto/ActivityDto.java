package com.treasure.hunt.dto;

import com.treasure.hunt.entity.ActivityImage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description 类描述：活动
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/19 10:32
 * @Version 版本号：v1.0.0
 */
public class ActivityDto implements Serializable {

    /**
     * 审核中
     */
    public static final Byte STATUS_AUDIT = 0;

    /**
     * id
     */
    private Long id;

    /**
     * 发起人Id
     */
    private Long customerId;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动内容
     */
    private String content;

    /**
     * 活动类型
     */
    private Long typeId;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 活动地址
     */
    private String address;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 二维码
     */
    private String qrCode;

    /**
     * 活动状态：0审核中，1进行中，2活动结束
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 活动图片
     */
    private List<ActivityImage> imageList;

    /**
     * 活动图片
     */
    private String[] images;

    /**
     * 是否点赞
     */
    private Boolean isLike = false;

    /**
     * 是否加入
     */
    private Boolean isJoin = false;

    /**
     * 点赞人数
     */
    private Long likeNum = 0L;

    /**
     * 评论人数
     */
    private Long commentNum = 0L;

    /**
     * 加入人数
     */
    private Long joinNum = 0L;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public List<ActivityImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<ActivityImage> imageList) {
        this.imageList = imageList;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public Long getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(Long joinNum) {
        this.joinNum = joinNum;
    }

    public Boolean getJoin() {
        return isJoin;
    }

    public void setJoin(Boolean join) {
        isJoin = join;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }
}
