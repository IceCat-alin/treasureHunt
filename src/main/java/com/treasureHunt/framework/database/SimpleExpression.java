package com.treasureHunt.framework.database;

import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

/**
 * @author 创建人：linhong
 * @version 1.0.0
 * @description 类描述：简单条件表达式
 * @modify 修改人：linying
 * @date 修改时间：2016年3月14日 下午7:53:32
 * @description 修改备注：
 */
public class SimpleExpression implements Criterion {
    /**
     * 属性名
     */
    private String fieldName;

    /**
     * 对应值
     */
    private Object value;

    /**
     * 计算符
     */
    private Operator operator;

    /**
     * 简单条件表达式
     *
     * @param fieldName 属性名
     * @param value     对应值
     * @param operator  计算符
     */
    protected SimpleExpression(String fieldName, Object value, Operator operator) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }

    /**
     * @param root    root
     * @param query   query
     * @param builder builder
     * @return predicate
     * @description 将查询添加联合起来
     */
    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path expression = null;
        if (fieldName.contains(".")) {
            String[] names = StringUtils.split(fieldName, ".");
            expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                expression = expression.get(names[i]);
            }
        } else {
            expression = root.get(fieldName);
        }

        switch (operator) {
            case EQ:
                if (value == null) {
                    return builder.isNull(expression);
                }
                return builder.equal(expression, value);
            case NE:
                return builder.notEqual(expression, value);
            case LIKE:
                return builder.like(builder.function("str", String.class, expression), "%" + value + "%");
            case LT:
                return builder.lessThan(expression, (Comparable) value);
            case GT:
                return builder.greaterThan(expression, (Comparable) value);
            case LTE:
                return builder.lessThanOrEqualTo(expression, (Comparable) value);
            case GTE:
                return builder.greaterThanOrEqualTo(expression, (Comparable) value);
            default:
                return null;
        }
    }

}
