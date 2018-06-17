package com.treasureHunt.framework.database;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author 创建人：linhong
 * @version 1.0.0
 * @description 类描述：逻辑非查询等
 * @modify 修改人：linying
 * @date 修改时间：2016年3月14日 下午7:47:21
 * @description 修改备注：
 */
public class NotExpression implements Criterion {
    /**
     * 逻辑非表达式中包含的表达式
     */
    private Criterion criterion;

    /**
     * 创建一个新的实例 NotExpression.
     *
     * @param criterion 表达式
     */
    public NotExpression(Criterion criterion) {
        this.criterion = criterion;
    }

    /**
     * @param root    root
     * @param query   query
     * @param builder builder
     * @return predicate
     * @description 构造查询条件
     */
    @Override
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return criterion == null ? null : builder.not(criterion.toPredicate(root, query, builder));
    }

}