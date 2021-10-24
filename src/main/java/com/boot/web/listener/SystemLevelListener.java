package com.boot.web.listener;

import com.boot.util.SystemParamUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SystemLevelListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        new SystemParamUtils().init();
//        System.out.println("过往访问次数"+SystemParamUtils.getCount());
    }

    public void contextDestroyed(ServletContextEvent sce) {
        new SystemParamUtils().update();
//        System.out.println("First...End");
    }

}
