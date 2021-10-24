package com.boot.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.boot.entity.Result;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

@WebFilter(urlPatterns = "/*", filterName = "requestFile")
public class RequestFilter implements Filter {

    private static String[] NOT_CHECK_RESOURCE_URL = {".js", ".html", ".png", ".jpg" ,"font", ".css", "login", "logout",".ico"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean isCheck = true;
        String url = ((HttpServletRequest) request).getRequestURI();
        // 检查请求是否需要验证用户登陆情况
        for(String position : NOT_CHECK_RESOURCE_URL){
            if(url.toLowerCase().indexOf(position) > -1){
                isCheck = false;
            }
        }
        if(isCheck){
            HttpSession session = ((HttpServletRequest) request).getSession(false);
            // 验证用户是否登陆
            if(null == session || null == session.getAttribute("user")){
                OutputStream out = response.getOutputStream();
                Result result = new Result();
                result.setSuccess(false);
                result.setErrCode("00");
                result.setMsg("用户未登录");
                String json = JSONObject.toJSONString(result);
                out.write(json.getBytes());
                out.flush();
                out.close();
                return;
            }
        }
        request.setAttribute("requestCode", "" + UUID.randomUUID() + new Date().getTime());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
