package com.boot.dao;

import com.boot.entity.UserMsg;

import java.util.Map;

/**
 * 操作用户数据的接口
 * Created by eleven on 2018/12/19.
 */
public interface UserDao {

    //通过用户id获取加密认证密码
    String queryAuthPwdById(Integer userId);
    //通过用户id获取用户信息
    UserMsg queryUserById(Integer userId);
    //通过微信号获取用户信息
    UserMsg queryUserByWxId(String wxId);
    //插入一个新用户
    int insertUser(UserMsg user);
    //更新一个用户信息
    int updateUser(UserMsg user);
    //保存用户的公钥
    int saveUserPublicKey(Map map);
    // 通过用户id，邮箱或账号获取加密密码
    String queryAuthPwdByCode(String code);
    // 通过用户id，邮箱或账号获取用户信息
    UserMsg queryUserByCode(String code);
    // 通过用户名和Email获取用户个数
    int getCountByUsernameOrEmail(UserMsg user);
    // 通过code激活账户
    int activeByCode(String code);

}
