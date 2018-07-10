package com.treasure.hunt;

import com.alibaba.fastjson.JSON;
import com.treasure.hunt.dao.ReplyDao;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ActivityService;
import com.treasure.hunt.service.CommentService;
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
public class CommentTests {

    @Autowired
    CommentService commentService;

    @Autowired
    ReplyDao replyDao;

    @Test
    public void getCommentPage() throws BusinessException {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        System.out.println(JSON.toJSON(replyDao.findByCommentIdsGroupByCommentId(ids)));
    }



}
