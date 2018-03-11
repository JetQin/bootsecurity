package com.github.jetqin.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@Controller
public class CurrentUser
{
    @RequestMapping("/currentUser")
    @ResponseBody
    public Map getCurrentUser(){
        Map userInfo = new HashMap();
        userInfo.put("name","Serati Ma");
        userInfo.put("avatar","https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png");
        userInfo.put("userid","00000001");
        userInfo.put("notifyCount",12);
        return userInfo;
    }

    @RequestMapping("/fake_chart_data")
    @ResponseBody
    public Map getFakeChartData(){
        Map userInfo = new HashMap();
        userInfo.put("name","Serati Ma");
        userInfo.put("avatar","https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png");
        userInfo.put("userid","00000001");
        userInfo.put("notifyCount",12);
        return userInfo;
    }


}
