package com.treasure.hunt.service.impl;

import com.alibaba.fastjson.JSON;
import com.treasure.hunt.common.Constant;
import com.treasure.hunt.common.PageList;
import com.treasure.hunt.common.PageSort;
import com.treasure.hunt.dao.UserInfoDao;
import com.treasure.hunt.entity.User;
import com.treasure.hunt.framework.database.Criteria;
import com.treasure.hunt.framework.database.Restrictions;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.framework.utils.DateUtil;
import com.treasure.hunt.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * @param pageNo     页码
     * @param pageSize   条数
     * @param sort       排序
     * @param sortField  排序字段
     * @param searchInfo 查询条件
     * @return
     * @Description 分页查询用户
     * @Date 2018/4/17 10:47
     **/
    @Override
    public PageList<User> getUserPage(Integer pageNo, Integer pageSize, String sort, String sortField, String searchInfo) {
        int currentPage = pageNo != null && pageNo > 0 ? pageNo - 1 : Constant.DEFAULT_PAGE;
        int currentSize = pageSize != null && pageSize > 0 ? pageSize : Constant.DEFAULT_SIZE;
        PageRequest pageable = PageRequest.of(currentPage, currentSize, PageSort.getSort(sort, sortField));

        Criteria<User> criteria = new Criteria<>();
        if (StringUtils.isNotBlank(searchInfo)) {
            criteria = queryCondition(searchInfo);
        }

        Page<User> page = userInfoDao.findAll(criteria, pageable);

        return new PageList(page.getContent(), page.getTotalElements(), page.getTotalPages());
    }

    /**
     * @param searchInfo
     * @return
     * @Description 分页查询条件
     * @Date 2018/4/17 9:39
     **/
    private Criteria<User> queryCondition(String searchInfo) {
        Criteria<User> criteria = new Criteria<User>();
        Map searchMap = JSON.parseObject(searchInfo, Map.class);
        if (searchMap.get("name") != null && StringUtils.isNotBlank(searchMap.get("name").toString())) {
            criteria.add(Restrictions.eq("name", searchMap.get("name"), true));
        }
        if (searchMap.get("beginTime") != null && StringUtils.isNotBlank(searchMap.get("beginTime").toString())) {
            criteria.add(Restrictions.gte("createTime", DateUtil.formatStrToDateCommon(searchMap.get("beginTime").toString()), true));
        }
        if (searchMap.get("endTime") != null && StringUtils.isNotBlank(searchMap.get("endTime").toString())) {
            criteria.add(Restrictions.lte("createTime", DateUtil.formatStrToDateCommon(searchMap.get("endTime").toString()), true));
        }
        return criteria;
    }

    /**
     * @param name
     * @param password
     * @return
     * @Description 新增用户
     * @Date 2018/4/17 9:42
     **/
    @Override
    public User addUser(String name, String password) {
        User user = new User();
        user.setUserName(name);
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userInfoDao.save(user);
    }

    /**
     * @param id
     * @param name
     * @param password
     * @return
     * @throws BusinessException 业务异常
     * @Description 更新用户
     * @Date 2018/4/17 9:57
     **/
    @Override
    public User updateUser(Long id, String name, String password) throws BusinessException {
        Optional<User> user = userInfoDao.findById(id);
        if (!user.isPresent()) {
            throw new BusinessException("找不到id：" + id + "的用户");
        }
        user.get().setUserName(name);
        user.get().setPassword(password);
        user.get().setUpdateTime(new Date());
        return userInfoDao.save(user.get());
    }

    /**
     * @param id
     * @return
     * @throws BusinessException 业务异常
     * @Description 删除用户
     * @Date 2018/4/17 10:24
     **/
    @Override
    public void deleteUser(Long id) throws BusinessException {
        Optional<User> user = userInfoDao.findById(id);
        if (!user.isPresent()) {
            throw new BusinessException("找不到id：" + id + "的用户");
        }
        userInfoDao.deleteById(id);
    }

    @Override
    public User findByUserNameAndPassword(User user) {
        return userInfoDao.findByUserNameAndPassword(user.getUserName(),user.getPassword());
    }
}
