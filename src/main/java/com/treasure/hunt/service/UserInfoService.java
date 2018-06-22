package com.treasure.hunt.service;

import com.treasure.hunt.common.PageList;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.entity.User;

public interface UserInfoService {
    PageList<User> getUserPage(Integer pageNo, Integer pageSize, String sort, String sortField, String searchInfo);

    User addUser(String name, String password);

    User updateUser(Long id, String name, String password) throws BusinessException;

    void deleteUser(Long id) throws BusinessException;

    User findByUserNameAndPassword(User user);

}
