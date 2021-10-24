package com.boot.web.listener;

import com.boot.util.CusAccessObjectUtil;
import com.boot.util.LogUtils;
import com.boot.util.SystemParamUtils;
import org.bson.Document;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@WebListener
public class RequestListener implements ServletRequestListener {

    private static ThreadLocal<HttpServletRequest> httpServletRequestHolder = new ThreadLocal<HttpServletRequest>();

    public void requestDestroyed (ServletRequestEvent requestEvent) {
        //new SystemParamUtils().update();
    }

    public void requestInitialized (ServletRequestEvent requestEvent) {
        SystemParamUtils.addRequestCount();
        HttpServletRequest request = (HttpServletRequest) requestEvent.getServletRequest();
        if(request.getRequestURI() != null && request.getRequestURI().indexOf("/manage/") < 0){
            // 请求日志
            Date current = new Date();
            request.setAttribute("id", UUID.randomUUID().toString().replaceAll("-", ""));
            new LogUtils().saveRequestLog(RequestListener.getRequestLog(request, current), current);
        }
    }

    private static Document getRequestLog(HttpServletRequest request, Date current) {
        Map<String, Object> requestLog = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        Enumeration enu = request.getParameterNames();
        while(enu.hasMoreElements()){
            String name = (String) enu.nextElement();
            params.put(name, request.getParameter(name));
        }
        requestLog.put("id", request.getAttribute("id"));
        requestLog.put("params", params);
        requestLog.put("url", request.getRequestURI());
        requestLog.put("ip", CusAccessObjectUtil.getIpAddress(request));
        if(request.getSession(false) != null) requestLog.put("user", request.getSession().getAttribute("user"));
        requestLog.put("sti", current);
        return new Document(requestLog);
    }

    public static HttpServletRequest getHttpServletRequest() {
        return httpServletRequestHolder.get();
    }
}
