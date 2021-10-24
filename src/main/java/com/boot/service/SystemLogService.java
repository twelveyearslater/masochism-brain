package com.boot.service;

import com.boot.entity.SystemLog;
import com.boot.util.ActionLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface SystemLogService {
    // 记录系统日志
    void save(ActionLog actionLog, HttpServletRequest req);
}
