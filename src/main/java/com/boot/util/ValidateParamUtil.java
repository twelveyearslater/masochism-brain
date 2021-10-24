package com.boot.util;

/**
 * Created by Raytine on 2019/4/24.
 */
public class ValidateParamUtil {

    public boolean isNotNull(Object obj){
        return null == obj ? false : true;
    }

    public boolean isNotEmptyString(String str){
        return "".equals(str) ? false : true;
    }

}
