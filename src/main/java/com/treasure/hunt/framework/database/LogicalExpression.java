package com.treasure.hunt.framework.database;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 创建人：linhong
 * @version 1.0.0
 * @description 类描述：逻辑条件表达式,用于复杂条件时使用，如但属性多对应值的OR查询等
 * @modify 修改人：linying
 * @date 修改时间：2016年3月14日 下午7:47:21
 * @description 修改备注：
 */
public class LogicalExpression implements Criterion {
    /**
     * 逻辑表达式中包含的表达式
     */
    private Criterion[] criterion;

    /**
     * 计算符
     */
    private Operator operator;

    public LogicalExpression(Criterion[] criterions, Operator operator) {
        this.criterion = criterions;
        this.operator = operator;
    }

    /**
     * @param root    root
     * @param query   query
     * @param builder builder
     * @return predicate
     * @description 将查询添加联合起来
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (operator) {
            case OR:
                List<Predicate> predicates = new ArrayList<Predicate>();
                for (int i = 0; i < this.criterion.length; i++) {
                    predicates.add(this.criterion[i].toPredicate(root, query, builder));
                }
                return builder.or(predicates.toArray(new Predicate[predicates.size()]));
            case IN:
                String fieldName = null;
                List inList = new ArrayList();
                for (int i = 0; i < this.criterion.length; i++) {
                    SimpleExpression simpleExpression = (SimpleExpression) this.criterion[i];
                    if (fieldName == null) {
                        fieldName = simpleExpression.getFieldName();
                    }
                    inList.add(simpleExpression.getValue());
                }
                if (criterion.length == 0) {
                    return builder.or(new Predicate[0]);
                }
                return root.get(fieldName).in(inList);
            default:
                return null;
        }
    }

}