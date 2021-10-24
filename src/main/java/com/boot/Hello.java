package com.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by eleven on 2018/12/1.
 */
@RestController
public class Hello {

    @RequestMapping(value="hello",method = RequestMethod.GET)
    public String hello(){
        return "hello springboot!";
    }

}
