package com.treasure.hunt;

import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.ReplyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReplyTests {

    @Autowired
    private ReplyService replyService;

    @Test
    public void addReply() throws BusinessException {
        replyService.addReply(1L,1L,"asdfasd");
    }

}
