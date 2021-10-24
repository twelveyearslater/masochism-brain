package com.boot.service;

import com.boot.entity.UserMsg;

public interface SessionLogService {

    void save(UserMsg user, String ip, int type);
}
