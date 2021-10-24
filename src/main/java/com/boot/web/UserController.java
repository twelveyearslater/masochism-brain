package com.boot.web;

import com.alibaba.fastjson.JSONObject;
import com.boot.entity.PswMsg;
import com.boot.entity.Result;
import com.boot.entity.UserMsg;
import com.boot.service.SessionLogService;
import com.boot.service.UserService;
import com.boot.util.BCryptUtil;
import com.boot.util.CusAccessObjectUtil;
import com.boot.web.listener.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eleven on 2018/12/18.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SessionLogService sessionLogService;

    /*
    * 用户通过用户ID、邮箱、用户名、微信号登录（网页端）
    * */
    @ResponseBody
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public Result login(HttpServletRequest req, String code, String ups){
        Result result = new Result();
        //验证认证密码：
        if (userService.validatePsw(code, ups)) {
            UserMsg user = userService.getUserByCode(code);
            //获取用户信息
            result.setData(user);
            HttpSession session = req.getSession(true);
            // System.out.println(session.getId() + "的失效时间为：" + session.getMaxInactiveInterval());
            SessionListener.update(user.getUsername(), session);
            session.setAttribute("user", user);
            sessionLogService.save((UserMsg) session.getAttribute("user"), CusAccessObjectUtil.getIpAddress(req), 1);
        } else {
            result.setSuccess(false);
            result.setErrCode("01");
            result.setMsg("用户不存在或密码不正确");
        }
        return result;
    }
    /*
    * 用户通过微信直接登录，并获取（小程序）
    * */
    @ResponseBody
    @RequestMapping(value="/loginbyuserwxid", method = RequestMethod.GET)
    public Map<String,Object> loginByWxId(String wxId){
        Map<String,Object> modelMap = new HashMap<>();
        //获取用户信息
        modelMap.put("user", userService.getUserByWxId(wxId));
        modelMap.put("success", true);
        return modelMap;
    }
    /*
    * 用户首次授权该小程序后创建的用户（微信小程序）
    * */
    @ResponseBody
    @RequestMapping(value="/addusr", method = RequestMethod.POST)
    public Map<String,Object> addUser(@RequestBody UserMsg user) {
        Map<String,Object> modelMap = new HashMap<>();
        boolean success = userService.addUser(user) == 1;
        modelMap.put("success", success);
        return modelMap;
    }
    /*
     * 修改用户信息（web端）
     * */
    @ResponseBody
    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public Result edit(String jsonData) {
        Result result = new Result();
        UserMsg user = JSONObject.parseObject(jsonData, UserMsg.class);
        boolean success = userService.modifyUserMsg(user);
        result.setSuccess(success);
        return result;
    }
    /*
    * 修改用户信息（微信小程序）
    * */
    @ResponseBody
    @RequestMapping(value="/modifyusr", method = RequestMethod.POST)
    public Map<String,Object> modifyUser(@RequestBody UserMsg user) {
        Map<String,Object> modelMap = new HashMap<>();
        boolean success = userService.modifyUserMsg(user);
        modelMap.put("success",success);
        return modelMap;
    }

    /*
    * 用户通过网页登陆（暂未使用）
    * type: 0:userName 1:wx 2:qq 3:email 4:phone
    * */
    @ResponseBody
    @RequestMapping(value="/loginforpc",method = RequestMethod.GET)
    public Map<String,Object> loginForPC(HttpServletRequest req, String accNo, String pwd, int accType) {
        Map<String,Object> modelMap = new HashMap<>();
        UserMsg user = userService.getUserForLogin(accNo, accType);
        if(BCryptUtil.checkpw(pwd, user.getPassword())){
            HttpSession session = req.getSession(true);
            session.setAttribute("user",user);
            session.setAttribute("loginTime",System.currentTimeMillis());
        }
        return modelMap;
    }

    /*
     * 检查用户登录状态（网页端）
     * */
    @ResponseBody
    @RequestMapping(value="/check",method = RequestMethod.GET)
    public Result checkUser(HttpServletRequest req) {
        Result result = new Result();
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute("user") != null){
            result.setSuccess(true);
            result.setData(session.getAttribute("user"));
        }else{
            result.setSuccess(false);
            result.setMsg("请先登录！");
        }
        return result;
    }

    /*
     *  注册用户（网页端）
     * */
    @ResponseBody
    @RequestMapping(value="/signUp",method = RequestMethod.GET)
    public Result signUp(String user) {
        Result result = new Result();
        int res = userService.addUser(JSONObject.parseObject(user, UserMsg.class));
        if (res == 1) {

        } else {
            result.setSuccess(false);
            result.setData(res);
            result.setMsg(res == 0 ? "账号或邮箱已被注册" : "请重新获取激活邮件");
        }
        return result;
    }

    /*
     *  用户退出（网页端）
     * */
    @ResponseBody
    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public Result logout(HttpServletRequest req) {
        Result result = new Result();
        try {
            HttpSession session = req.getSession(false);
            if(session != null) {
                sessionLogService.save((UserMsg)session.getAttribute("user"), CusAccessObjectUtil.getIpAddress(req), 2);
                session.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value="/active",method = RequestMethod.GET)
    public void active(HttpServletResponse response, String code) throws IOException {
        int res = userService.activeByCode(code);
        response.sendRedirect("http://localhost:8080/");
    }

    /*
     * 用户通过用户ID、邮箱、用户名、微信号登录（网页端）
     * */
    @ResponseBody
    @RequestMapping(value="/unlock", method = RequestMethod.GET)
    public Result unlock(String id, String ups){
        Result result = new Result();
        //验证认证密码：
        if ( !userService.validatePsw(id, ups)) {
            result.setSuccess(false);
            result.setMsg("密码错误！");
        }
        return result;
    }

}
