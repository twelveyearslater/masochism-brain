package com.boot.web;

import com.boot.DemoApplication;
import com.boot.service.PswService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Raytine on 2021/6/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class PswControllerTest {

    @Autowired
    private PswService pswService;

    @Test
    public void queryPswList() throws Exception {
        System.out.println(pswService.hashCode());
    }

}