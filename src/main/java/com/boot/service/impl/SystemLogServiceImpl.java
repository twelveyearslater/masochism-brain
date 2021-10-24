package com.boot.service.impl;

import com.boot.dao.SystemLogDao;
import com.boot.entity.SystemLog;
import com.boot.entity.UserMsg;
import com.boot.service.SystemLogService;
import com.boot.util.ActionLog;
import com.boot.util.DealStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Autowired
    private SystemLogDao systemLogDao;

    @Override
    public void save(ActionLog actionLog, HttpServletRequest req) {

        SystemLog log = new SystemLog();
//        HttpSession session = req.getSession();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false);
        UserMsg user = (UserMsg) session.getAttribute("user");
        String username = "";
        try{
            username = user.getUsername();
        } catch (Exception e) {
            username = null;
        }
        log.setId(req.getParameter("requestCode"));
        log.setIp(req.getRemoteAddr());
        log.setLogDate(new Date());
        log.setOperator(username);
        log.setType(0);
        String logContent = actionLog.log();
        List<String> paramsList = new DealStringUtils().getParamsInBrace(logContent);
        for(String param : paramsList){
            if(param.indexOf("session.") == 0){
                logContent = logContent.replace("{" + param + "}", session.getAttribute(param.substring(8)).toString());
            }else{
                logContent = logContent.replace("{" + param + "}",req.getParameter(param));
            }
        }
        log.setLogDesc(logContent);
        int result = systemLogDao.save(log);
    }
}
