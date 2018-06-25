package com.treasure.hunt;

import com.alibaba.fastjson.JSON;
import com.treasure.hunt.common.PageList;
import com.treasure.hunt.entity.User;
import com.treasure.hunt.service.ActivityLikeService;
import com.treasure.hunt.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ActivityLikeTests {

    @Autowired
    ActivityLikeService activityLikeService;

    @Test
    public void getUserPageTest() {
        List<Long> activityIds = new ArrayList<>();
        activityIds.add(1L);
        activityIds.add(2L);
        activityLikeService.groupByActivityId(activityIds);
    }

}
