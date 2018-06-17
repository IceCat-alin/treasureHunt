package com.treasureHunt;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.treasureHunt.common.PageList;
import com.treasureHunt.entity.User;
import com.treasureHunt.service.UserInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTests {

    @Autowired
    UserInfoService userService;

    @Test
    public void getUserPageTest() {
        PageList<User> page = userService.getUserPage(0, 10,"","","");
        System.out.println(JSON.toJSONString(page));
    }

}
