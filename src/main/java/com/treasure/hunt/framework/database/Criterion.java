package com.treasure.hunt.framework.database;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author 创建人：linhong
 * @version 1.0.0
 * @Description 类名称:Criterion
 * @Description 类描述：条件接口,用户提供条件表达式接口
 * @modify 修改人：linying
 * @date 修改时间：2016年3月12日 上午11:52:05
 * @Description 修改备注：
 */
public interface Criterion {
    enum Operator {
        /**
         * equal
         */
        EQ,
        /**
         * not equal
         */
        NE,
        /**
         * like
         */
        LIKE,
        /**
         * right
         */
        GT,
        /**
         * left
         */
        LT,
        /**
         * gte
         */
        GTE,
        /**
         * lte
         */
        LTE,
        /**
         * and
         */
        AND,
        /**
         * or
         */
        OR,
        /**
         * in
         */
        IN,
        /**
         * not
         */
        NOT
    }

    /**
     * @param root    root
     * @param query   query
     * @param builder builder
     * @return predicate
     * @description 将查询添加联合起来
     */
    Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder);
}