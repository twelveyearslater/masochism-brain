package com.boot.service.impl;

import com.boot.dao.SessionLogDao;
import com.boot.entity.SessionLog;
import com.boot.entity.UserMsg;
import com.boot.service.SessionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SessionLogServiceImpl implements SessionLogService {

    @Autowired
    private SessionLogDao sessionLogDao;

    @Override
    public void save(UserMsg user, String ip, int type) {
        SessionLog sessionLog = new SessionLog();
        sessionLog.setIp(ip);
        sessionLog.setDate(new Date());
        sessionLog.setOperator(user.getUsername());
        sessionLog.setType(type);
        if (type == 1) {
            sessionLog.setContent("用户 [" + user.getUsername() + "] 已登录");
        } else {
            sessionLog.setContent("用户 [" + user.getUsername() + "] 已退出");
        }
        sessionLogDao.insertSessionLog(sessionLog);
    }
}
