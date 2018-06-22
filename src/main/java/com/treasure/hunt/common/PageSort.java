package com.treasure.hunt.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/4/17 10:31
 * @Version 版本号：v1.0.0
 */
public class PageSort {
    /**
     * @param sort
     * @param sortField
     * @return
     * @Description 获取排序
     * @Date 2018/4/17 10:43
     **/
    public static Sort getSort(String sort, String sortField) {
        // 默认按照倒序排列
        Sort.Direction direction = Sort.Direction.DESC;
        if (StringUtils.isNotBlank(sort) && "ASC".equals(sort)) {
            direction = Sort.Direction.ASC;
        }

        List<String> fields = new ArrayList<String>();
        // 默认使用创建时间倒序排列
        fields.add("createTime");
        if (StringUtils.isNotBlank(sortField)) {
            fields.clear();
            fields = Arrays.asList(sortField.split(","));
        }
        return new Sort(direction, fields);
    }
}
