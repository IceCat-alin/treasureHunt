package com.treasureHunt.framework.database;

import org.hibernate.criterion.MatchMode;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * @author 创建人：linhong
 * @version 1.0.0
 * @description 类描述：条件构造器 用于创建条件表达式
 * @modify 修改人：linying
 * @date 修改时间：2016年3月14日 下午7:49:23
 * @description 修改备注：
 */
public class Restrictions {

    /**
     * 等于
     *
     * @param fieldName  列属性
     * @param value      列值
     * @param ignoreNull 是否忽略null
     * @return 条件表达式
     */
    public static SimpleExpression eq(String fieldName, Object value, boolean ignoreNull) {
        if (StringUtils.isEmpty(value) && ignoreNull) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.EQ);
    }

    /**
     * 不等于
     *
     * @param fieldName  列属性
     * @param value      列值
     * @param ignoreNull 是否忽略null
     * @return 条件表达式
     */
    public static SimpleExpression ne(String fieldName, Object value, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.NE);
    }

    /**
     * 模糊匹配
     *
     * @param fieldName  列属性
     * @param value      列值
     * @param ignoreNull 是否忽略null
     * @return 条件表达式
     */
    public static SimpleExpression like(String fieldName, String value, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value.trim(), Criterion.Operator.LIKE);
    }

    /**
     * 模糊匹配
     *
     * @param fieldName  列属性
     * @param value      列值
     * @param matchMode  匹配模式
     * @param ignoreNull 是否忽略null
     * @return 条件表达式
     */
    public static SimpleExpression like(String fieldName, String value, MatchMode matchMode, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return null;
    }

    /**
     * 大于
     *
     * @param fieldName  列属性
     * @param value      列值
     * @param ignoreNull 是否忽略null
     * @return 条件表达式
     */
    public static SimpleExpression gt(String fieldName, Object value, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.GT);
    }

    /**
     * 小于
     *
     * @param fieldName  列属性
     * @param value      列值
     * @param ignoreNull 是否忽略null
     * @return 条件表达式
     */
    public static SimpleExpression lt(String fieldName, Object value, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.LT);
    }

    /**
     * 大于等于
     *
     * @param fieldName  列属性
     * @param value      列值
     * @param ignoreNull 是否忽略null
     * @return 条件表达式
     */
    public static SimpleExpression gte(String fieldName, Object value, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.GTE);
    }

    /**
     * 小于等于
     *
     * @param fieldName  列属性
     * @param value      列值
     * @param ignoreNull 是否忽略null
     * @return 条件表达式
     */
    public static SimpleExpression lte(String fieldName, Object value, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.LTE);
    }

    /**
     * 并且
     *
     * @param criterions 条件
     * @return 条件表达式
     */
    public static LogicalExpression and(Criterion... criterions) {
        return new LogicalExpression(criterions, Criterion.Operator.AND);
    }

    /**
     * 或者
     *
     * @param criterions 条件
     * @return 条件表达式
     */
    public static LogicalExpression or(Criterion... criterions) {
        return new LogicalExpression(criterions, Criterion.Operator.OR);
    }

    /**
     * 包含于
     *
     * @param fieldName  列属性
     * @param value      列值
     * @param ignoreNull 是否忽略null
     * @return 条件表达式
     */
    @SuppressWarnings("rawtypes")
    public static LogicalExpression in(String fieldName, Collection value, boolean ignoreNull) {
        if (ignoreNull && (value == null || value.isEmpty())) {
            return null;
        }
        SimpleExpression[] ses = new SimpleExpression[value.size()];
        int i = 0;
        for (Object obj : value) {
            ses[i] = new SimpleExpression(fieldName, obj, Criterion.Operator.EQ);
            i++;
        }
        return new LogicalExpression(ses, Criterion.Operator.IN);
    }

    /**
     * @param criterion 表达式
     * @return 逻辑非表达式
     * @description 逻辑非
     */
    public static NotExpression not(Criterion criterion) {
        return new NotExpression(criterion);
    }
}