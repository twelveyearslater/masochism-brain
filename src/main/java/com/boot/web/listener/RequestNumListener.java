package com.boot.web.listener;

import com.boot.dao.SystemLogDao;
import com.boot.util.CusAccessObjectUtil;
import com.boot.util.SystemParamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@WebListener
public class RequestNumListener implements ServletRequestListener {

    private static ThreadLocal<HttpServletRequest> httpServletRequestHolder = new ThreadLocal<HttpServletRequest>();

    public void requestDestroyed (ServletRequestEvent requestEvent) {
        //new SystemParamUtils().update();
    }

    public void requestInitialized (ServletRequestEvent requestEvent) {
        HttpServletRequest request = (HttpServletRequest) requestEvent.getServletRequest();
        if(request.getRequestURI()!=null && request.getRequestURI().contains("/user/")){
            SystemParamUtils.addRequestCount();
//            System.out.println(SystemParamUtils.getCount());
        }else if(request.getRequestURI()!=null && request.getRequestURI().contains("_profile.html")) {
            ServletContext application = requestEvent.getServletContext();
            WebApplicationContext appCtx = WebApplicationContextUtils.getWebApplicationContext(application);
            SystemLogDao systemLogDao = appCtx.getBean(SystemLogDao.class);
            HashMap<String, Object> param = new HashMap<>();
            String ip = CusAccessObjectUtil.getIpAddress(request);
            Date current = new Date();
            param.put("ip", ip);
            param.put("current", current);
            systemLogDao.saveProfileLog(param);
        }
    }

    public static HttpServletRequest getHttpServletRequest() {
        return httpServletRequestHolder.get();
    }
}
