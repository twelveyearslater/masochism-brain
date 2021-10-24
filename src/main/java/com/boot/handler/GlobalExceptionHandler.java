package com.boot.handler;

import com.boot.entity.Result;
import com.boot.exception.AreaException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理类
 * Created by eleven on 2018/12/12.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value=AreaException.class)
    @ResponseBody
    private Map<String,Object> areaExceptionHandler(HttpServletRequest req, AreaException e){
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",false);
        modelMap.put("errMsg",e.getMessage());
        e.printStackTrace();
        return modelMap;
    }

    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    private Result exceptionHandler(HttpServletRequest req, Exception e){
        Result result = new Result();
        result.setSuccess(false);
        result.setMsg(e.getMessage());
        e.printStackTrace();
        return result;
    }
}
