package com.boot.util;

import java.util.ArrayList;
import java.util.List;

public class DealStringUtils {

    /**
     * 获取字符串中的花括号里的字符串
     * @param str
     * @return 返回一个字符串集合
     */
    public List<String> getParamsInBrace(String str){

        List<String> params = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for(char ch : str.toCharArray()){
            if(!flag && 123 - ch == 0){
                flag = true;
            }else if(flag && 123 - ch == 0){
                sb.delete(0,sb.length());
            }else if(flag && 125 - ch == 0){
                if(sb.length() > 0){
                    params.add(sb.toString());
                    sb.delete(0,sb.length());
                }
                flag = false;
            }else if(flag){
                sb.append(ch);
            }
        }
        return params;
    }
}
