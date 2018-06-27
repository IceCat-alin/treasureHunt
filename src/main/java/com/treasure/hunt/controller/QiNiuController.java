package com.treasure.hunt.controller;

import com.qiniu.util.Auth;
import com.treasure.hunt.common.Constant;
import com.treasure.hunt.common.ResultInfo;
import com.treasure.hunt.framework.exception.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 类描述：
 * @Author 创建人：linying
 * @Date 创建时间：2018/6/27 9:48
 * @Version 版本号：v1.0.0
 */
@Controller
@RequestMapping("/qiniu")
public class QiNiuController {

    /**
     * 获取七牛token
     *
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getToken")
    @ResponseBody
    public ResultInfo getToken() {
        Auth auth = Auth.create(Constant.QN_ACCESS_KEY, Constant.QN_SECRET_KEY);
        String upToken = auth.uploadToken(Constant.QN_BUCKET);
        Map<String, String> map = new HashMap<>(16);
        map.put("uptoken", upToken);
        return ResultInfo.success("获取七牛上传图片token成功！", map);
    }
}
