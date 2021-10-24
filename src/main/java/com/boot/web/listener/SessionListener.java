package com.boot.web.listener;

import com.boot.entity.UserMsg;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class SessionListener implements ServletRequestListener, HttpSessionListener {

    private HttpServletRequest request;

    private static int onlineCount = 0;
    private static ConcurrentHashMap<String, HttpSession> userSessions = new ConcurrentHashMap<>();
    private static Vector<String> activeSessions;

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        isLogin(sessionEvent.getSession());
        onlineCount++;
        System.out.println("--------------------- 当前在线人数： " + onlineCount + " ---------------------");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        onlineCount--;
        userSessions.remove(((UserMsg)sessionEvent.getSession().getAttribute("user")).getUsername());
        System.out.println("--------------------- 当前在线人数： " + onlineCount + " ---------------------");
    }

    /**
     * @todo 强制下线登陆的同一用户会话
     * @param currentSession 当前创建的session
     */
    private void isLogin(HttpSession currentSession){
        String userName = request.getParameter("userName");
        if(userName != null && userSessions.containsKey(userName)){
            HttpSession onlineSession = userSessions.get(userName);
            if(!currentSession.getId().equals(onlineSession.getId())){
                userSessions.remove(userName);
                userSessions.put(userName, currentSession);
            }
        }
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        request = (HttpServletRequest) sre.getServletRequest();
    }

    /**
     * 用户登陆时更新Session
     */
    public static void update(String userName, HttpSession session) {
        if(userSessions.containsKey(userName) && session != userSessions.get(userName)){
            if (! userSessions.get(userName).equals(session)) {
                userSessions.get(userName).invalidate();
            }
        }
        userSessions.put(userName, session);
    }

}
