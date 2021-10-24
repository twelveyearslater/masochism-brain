package com.boot.service.impl;

import com.boot.dao.UserDao;
import com.boot.entity.UserMsg;
import com.boot.service.UserService;
import com.boot.util.ActionLog;
import com.boot.util.MailUtil;
import com.boot.util.PswUtil;
import com.boot.util.RSACoder;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Map;

/**
 * 用户逻辑处理的实现
 * Created by eleven on 2018/12/19.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    /*
    * //验证密码
    * */
    @Override
    public boolean validatePsw(String code, String pwd) {
        if(!"".equals(code)&&!"".equals(pwd)){
            try {
                String authPsw = PswUtil.decode(userDao.queryAuthPwdByCode(code),null);
                if(authPsw.equals(pwd)){
                    return true;
                }
            }catch(Exception e){
                e.printStackTrace();
                throw new RuntimeException("用户不存在或密码不正确");
            }
        }
        return false;
    }

    /*
    * 通过id获取用户信息
    * */
    @Override
    public UserMsg getUserById(Integer userId) {
        UserMsg user = null;
        if(!"".equals(""+userId)){
            try{
                user = userDao.queryUserById(userId);
                if (user == null) {
                    throw new RuntimeException("该用户不存在!");
                }
            }catch(Exception e){
                throw new RuntimeException("获取用户信息失败:"+e.getMessage());
            }
        }
        return user;
    }

    /*
     * 通过id获取用户信息
     * */
    @Override
    public UserMsg getUserByCode(String code) {
        UserMsg user = null;
        if(!"".equals(code)){
            try{
                user = userDao.queryUserByCode(code);
                if (user == null) {
                    throw new RuntimeException("该用户不存在!");
                }
            }catch(Exception e){
                throw new RuntimeException("获取用户信息失败:"+e.getMessage());
            }
        }
        return user;
    }

    /*
    * 通过微信号获取用户信息
    * */
    @Override
    public UserMsg getUserByWxId(String wxId) {
        UserMsg user = null;
        if(!"".equals(wxId)){
            try{
                user = userDao.queryUserByWxId(wxId);
                if (user == null) {
                    throw new RuntimeException("该用户不存在!");
                }
            }catch(Exception e){
                throw new RuntimeException("获取用户信息失败:"+e.getMessage());
            }
        }
        return user;
    }

    /*
    * 首次登陆小程序添加用户
    * @return 0：缺少参数；1：成功；2：邮件发送失败
    * */
    @Override
    @Transactional
    public int addUser(UserMsg user) {
        if(!"".equals(user.getUsername()) && !"".equals(user.getPassword()) && !"".equals(user.getEmail())){
            try {
                int count = userDao.getCountByUsernameOrEmail(user);
                if (count > 0) return 0;
                Map<String, Object> keyMap = RSACoder.initKey();
                user.setEncodeQueue(Base64.encodeBase64String(RSACoder.getPublicKey(keyMap)));
                user.setDecodeQueue(Base64.encodeBase64String(RSACoder.getPrivateKey(keyMap)));
                user.setDecodeType(1);
                user.setPassword(PswUtil.encode(user.getPassword(), null));
                user.setStatus(0);
                user.setSignTime(new Date());
                int effectedNum = userDao.insertUser(user);
                if (effectedNum > 0) {
                    return MailUtil.activeAccount(user) ? 1 : 2;
                }
            } catch (Exception e){
                throw new RuntimeException("新建用户时发生错误：" + e.getMessage());
            }
        }
        return 0;
    }

    /*
    * 修改用户资料（不包含加密序列）
    * */
    @Override
    @Transactional
    public boolean modifyUserMsg(UserMsg user) {
        if(!"".equals(user.getPassword())&&!"".equals(user.getId())){
            try {
                int effectedNum = userDao.updateUser(user);
                if (effectedNum > 0) {
                    return true;
                }
            }catch (Exception e){
                throw new RuntimeException("新建用户时发生错误：" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public UserMsg getUserForLogin(String accNo, Integer accType) {
        return null;
    }

    @Override
    public int activeByCode(String code) {
        return userDao.activeByCode(code);
    }
}
