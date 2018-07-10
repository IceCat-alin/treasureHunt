package com.treasure.hunt;

import com.alibaba.fastjson.JSON;
import com.treasure.hunt.framework.exception.BusinessException;
import com.treasure.hunt.service.MessageService;
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
public class MessageTests {

    @Autowired
    private MessageService messageService;

    @Test
    public void getMessagePage() throws BusinessException {
        System.out.println(JSON.toJSON(messageService.getMessagePage(1,10,1L)));
    }

    @Test
    public void getUnReadNum() {
        System.out.println(JSON.toJSON(messageService.getUnReadNum()));
    }
}
