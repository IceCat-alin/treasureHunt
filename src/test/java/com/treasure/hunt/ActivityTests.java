package com.treasure.hunt;

import com.alibaba.fastjson.JSON;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ActivityService;
import com.treasure.hunt.service.WxAuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ActivityTests {

    @Autowired
    ActivityService activityService;

    @Test
    public void getTopActivity() throws BusinessException {
        System.out.println(JSON.toJSON(activityService.getTopActivity()));
    }

}
