package com.boot.service;

import com.boot.entity.UserMsg;

/**
 * 用户逻辑处理的接口
 * Created by eleven on 2018/12/19.
 */
public interface UserService {
    //验证密码
    boolean validatePsw(String code,String pwd);
    //通过id获取用户信息
    UserMsg getUserById(Integer userId);
    //通过微信号获取用户信息
    UserMsg getUserByWxId(String wxId);
    //首次登陆小程序添加用户
    int addUser(UserMsg user);
    //修改用户资料
    boolean modifyUserMsg(UserMsg user);
    // 用户登录时获取用户信息
    UserMsg getUserForLogin(String accNo, Integer accType);
    //通过id,邮箱或username获取用户信息
    UserMsg getUserByCode(String userId);
    // 通过code激活用户
    int activeByCode(String code);
}
