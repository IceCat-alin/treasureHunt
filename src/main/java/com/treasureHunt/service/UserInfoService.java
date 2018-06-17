package com.treasureHunt.service;

import com.treasureHunt.common.PageList;
import com.treasureHunt.entity.User;
import com.treasureHunt.framework.exception.BusinessException;

public interface UserInfoService {
    PageList<User> getUserPage(Integer pageNo, Integer pageSize, String sort, String sortField, String searchInfo);

    User addUser(String name, String password);

    User updateUser(Long id, String name, String password) throws BusinessException;

    void deleteUser(Long id) throws BusinessException;

    User findByUserNameAndPassword(User user);

}
