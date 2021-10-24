package com.boot.dao;

import com.boot.entity.SystemLog;

import java.util.HashMap;

public interface SystemLogDao {

    // 记录日志
    int save(SystemLog log);

    // 记录简历查看日志
    int saveProfileLog(HashMap param);
}
