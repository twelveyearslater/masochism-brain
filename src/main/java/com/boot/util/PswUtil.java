package com.boot.util;

import java.util.Random;

/**
 * 密码加密-解密工具
 * Created by eleven on 2018/12/19.
 */
public class PswUtil {

    //默认加密序列
    private static char[] defaultSalt = new char[] { '6', 'f', '9', '1', '7', '8', 'a', '2', 'b',
            '3', 'c', '0', 'd', '5', 'e', '4' };

    //用户加密序列
    private String[] userSalt = null;

    /*
    * 加密工具
    * */
    public static String encode(String psw,char[] salt){
        if(salt==null) salt = defaultSalt;
        String result = "";
        int length = psw.length();
        while (psw.length() < 8) {
            Random random = new Random();
            char c = (char) ('A' + random.nextInt(26));
            psw = psw + c;
        }
        psw = "" + length + '=' + psw;
        char[] p = psw.toCharArray();
        for (int i = 0; i < p.length; i++) {
            int m = p[i] - 0;
            result = salt[m / 16] + result + salt[m % 16];
        }
        return result;
    }

    /*
    * 解密工具
    * */
    public static String decode(String psw,char[] salt){
        if(salt==null) salt = defaultSalt;
        String result = "";
        int length = psw.length();
        char[] array = psw.toCharArray();
        if ((length % 2) != 0)
            return result;
        for (int i = 0; i < length / 2; i++) {
            int tt = pos(array[i], salt) + 1;
            int ll = pos(array[length - i - 1], salt) + 1;
            result = (char) (tt * 16 + ll - 17) + result;
        }
        int index = pos('=', result.toCharArray());
        int j = Integer.valueOf(result.substring(0, index)).intValue();
        result = result.substring(index + 1, index + 1 + j);
        return result;
    }

    private static int pos(char a, char array[]) {
        for (int i = 0; i < array.length; i++) {
            if (a == array[i])
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String resource = "txhpdobdckxkbiai";
        System.out.println(PswUtil.encode(resource, null));
    }
}
