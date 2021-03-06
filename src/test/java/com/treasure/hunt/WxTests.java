package com.treasure.hunt;

import com.alibaba.fastjson.JSON;
import com.treasure.hunt.entity.WxCustomer;
import com.treasure.hunt.framework.exception.BusinessException;
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
public class WxTests {

    @Autowired
    WxAuthService wxAuthService;

    @Test
    public void getCustomerPage() throws BusinessException {
        System.out.println(JSON.toJSON(wxAuthService.getCustomerPage(1, 10, new WxCustomer())));
    }

    @Test
    public void getMyRank() throws BusinessException {
        System.out.println(wxAuthService.getMyRank(1L));
    }

    @Test
    public void getRank() throws BusinessException {
        System.out.println(JSON.toJSON(wxAuthService.getRank()));
    }

    @Test
    public void getQrCode() throws Exception {
        System.out.println(JSON.toJSON(wxAuthService.getQrCode("pages/detail/detail?activityId=9")));
    }
}
