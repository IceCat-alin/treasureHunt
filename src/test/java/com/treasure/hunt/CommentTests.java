package com.treasure.hunt;

import com.alibaba.fastjson.JSON;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommentTests {

    @Autowired
    CommentService commentService;

    @Test
    public void getCommentPage() throws BusinessException {
        Byte type = 3;
        System.out.println(JSON.toJSON(commentService.getCommentPage(1, 10, 28L, type)));
    }


}
