package com.boot.handler;

import com.boot.entity.UserMsg;
import com.boot.util.LogUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.bson.Document;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ServiceLogHandler implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Before("execution(public * com.boot.service.impl..*.add*(..)) || " +
            "execution(public * com.boot.service.impl..*.modify*(..)) || " +
            "execution(public * com.boot.service.impl..*.delete*(..)) || " +
            "execution(public * com.boot.service.impl..*.parse*(..)) " )
    public void before(JoinPoint joinPoint){
        Object args[] = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Date current = new Date();
        saveLog(method, args, current, request);
    }

    @AfterReturning("execution(public * com.boot.service.impl..*.add*(..)) || " +
            "execution(public * com.boot.service.impl..*.modify*(..)) || " +
            "execution(public * com.boot.service.impl..*.delete*(..)) || " +
            "execution(public * com.boot.service.impl..*.parse*(..)) ")
    public void afterReturning(JoinPoint joinPoint){

    }

    @AfterThrowing(throwing="ex", pointcut = "execution(public * com.boot.service.impl..*.add*(..)) || " +
            "execution(public * com.boot.service.impl..*.modify*(..)) || " +
            "execution(public * com.boot.service.impl..*.delete*(..)) || " +
            "execution(public * com.boot.service.impl..*.parse*(..)) ")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Date current = new Date();
        saveExceptionLog(method, current, request, ex);
    }

    private void saveExceptionLog(Method method, Date current, HttpServletRequest request, Throwable ex) {
        Map<String, Object> exceptionLog = new HashMap<>();
        exceptionLog.put("id", request.getAttribute("id"));
        exceptionLog.put("method", method.getDeclaringClass().getName() + "." + method.getName());
        exceptionLog.put("ti", current);
        exceptionLog.put("ex", ex);
        new LogUtils().saveExceptionLog(new Document(exceptionLog));
    }

    private void saveLog(Method method, Object[] args, Date current, HttpServletRequest request){
        Map<String, Object> systemLog = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        systemLog.put("id", request.getAttribute("id"));
        systemLog.put("method", method.getDeclaringClass().getName() + "." + method.getName());
        HttpSession session = request.getSession(false);
        if(session != null) systemLog.put("username", ((UserMsg) session.getAttribute("user")).getUsername());
        systemLog.put("ti", current);
        Parameter[] paramArr = method.getParameters();
        for(int i = 0; i < paramArr.length; i++) {
            Map<String, Object> param = new HashMap<>();
            Parameter p = paramArr[i];
            param.put("class", p.getType().toString());
            param.put("value", args[i]);
            params.put(p.getName(), param);
        }
        systemLog.put("param", params);
        new LogUtils().saveSystemLog(new Document(systemLog), current);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ServiceLogHandler.applicationContext == null) {
            ServiceLogHandler.applicationContext = applicationContext;
        }
    }
}
