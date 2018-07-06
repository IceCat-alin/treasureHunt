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
     * 进行中
     */
    public static final Byte STATUS_START = 1;

    /**
     * 结束
     */
    public static final Byte STATUS_END = 2;

    /**
     * 置顶
     */
    public static final Byte TOP_TRUE = 1;

    /**
     * 置顶
     */
    public static final Byte TOP_FALSE = 0;

    /**
     * id
     */
    private Long id;

    /**
     * 发起人Id
     */
    private Long customerId;

    /**
     * name
     */
    private String customerName;

    /**
     * image
     */
    private String customerImg;

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
     * 纬度
     */
    private Double lat;

    /**
     * 经度
     */
    private Double lng;

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
     * 是否置顶
     */
    private Byte isTop;

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
     * 点赞人数
     */
    private Integer likeNum = 0;

    /**
     * 评论人数
     */
    private Integer commentNum = 0;

    /**
     * 加入人数
     */
    private Integer joinNum = 0;

    /**
     * 浏览人数
     */
    private Integer viewNum = 0;

    /**
     * 加入时间
     */
    private Date joinTime;

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

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(Integer joinNum) {
        this.joinNum = joinNum;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
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

    public Byte getIsTop() {
        return isTop;
    }

    public void setIsTop(Byte isTop) {
        this.isTop = isTop;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
