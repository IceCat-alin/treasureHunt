package com.treasure.hunt.framework.database;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> 泛型
 * @author 创建人：linhong
 * @version 1.0.0
 * @description 类描述：定义一个查询条件容器
 * @modify 修改人：linying
 * @date 修改时间：2016年3月14日 下午7:38:36
 * @description 修改备注：
 */
public class Criteria<T> implements Specification<T> {
    /**
     * 查询列表
     */
    private List<Criterion> criterions = new ArrayList<Criterion>();

    /**
     * @param root    root
     * @param query   query
     * @param builder builder
     * @return predicate
     * @description 将所有条件用 联合起来
     */
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (!criterions.isEmpty()) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            for (Criterion c : criterions) {
                predicates.add(c.toPredicate(root, query, builder));
            }
            // 将所有条件用 and 联合起来
            if (predicates.size() > 0) {
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }
        return builder.conjunction();
    }

    /**
     * @param criterion 查询条件
     * @description 增加简单条件表达式
     */
    public void add(Criterion criterion) {
        if (criterion != null) {
            criterions.add(criterion);
        }
    }
}