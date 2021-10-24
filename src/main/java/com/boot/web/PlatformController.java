package com.boot.web;

import com.boot.entity.Result;
import com.boot.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/platform")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @ResponseBody
    @RequestMapping("/getListForSelect")
    public Result getPlatformForSelect(){
        Result result = new Result();
        result.setData(platformService.queryPlatformForSelect());
        return result;
    }
}
