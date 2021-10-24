package com.boot.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 系统配置信息
 */
public class SystemParamUtils {

    private static Map<String,Object> sysParamsMap = new HashMap<>();

    public void init(){
        getSystemConfig();
        readFileToMap(Constants.SYSTEMPARAMSFILEPATH);
    }

    /**
     * @todo
     * @param fileName 系统配置的路径
     */
    private void readFileToMap(String fileName){
        String configFilePath = this.getClass().getClassLoader().getResource(fileName).getPath();
        Properties properties = new Properties();
        BufferedReader br = null;
        InputStream is = null;
        if(new FileUtil().exist(new File(configFilePath))){
            try {
                br = new BufferedReader(new FileReader(configFilePath));
                properties.load(br);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
//            System.out.println("ok");
            try {
                is = this.getClass().getClassLoader().getResourceAsStream(fileName);
                properties.load(is);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(null != properties && properties.size()>0){
            for(Map.Entry<Object,Object> e : properties.entrySet()){
                sysParamsMap.put(e.getKey().toString(),e.getValue());
            }
        }else{
            // 读取数据库
        }
    }

    public void update(){

        String filePath = this.getClass().getClassLoader().getResource(Constants.SYSTEMPARAMSFILEPATH).getPath();
        System.out.println(filePath);
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(filePath));
            OutputStream fos = new FileOutputStream(filePath);
            for(Map.Entry<String,Object> e :sysParamsMap.entrySet()){
                props.setProperty(e.getKey(), e.getValue().toString());
            }
            props.store(fos, "Update value");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("属性文件更新错误");
        }
    }

    public static String getCount(){
        return sysParamsMap.get("requestTotalCount").toString();
    }

    public static void addRequestCount(){
        sysParamsMap.put("requestTotalCount",Integer.parseInt(""+sysParamsMap.get("requestTotalCount"))+1);
    }

    private static void getSystemConfig(){
        Properties props = System.getProperties();
//        sysParamsMap.put("serverType", props.getProperty("sun.desktop"));
        sysParamsMap.put("javaVersion", props.getProperty("java.version"));
        if(props.getProperty("os.name").toLowerCase().startsWith("win")){
            sysParamsMap.put("serverType","1");
        }else{
            sysParamsMap.put("serverType","0");
        }

        sysParamsMap.put("osArch", props.getProperty("os.arch"));
        sysParamsMap.put("userName", props.getProperty("user.name"));
        sysParamsMap.put("userHome", props.getProperty("user.home"));
        sysParamsMap.put("userDir", props.getProperty("user.dir"));
    }

    public static boolean isLinuxServer(){
        if("0".equals(sysParamsMap.get("serverType"))){
            return true;
        }else{
            return false;
        }

    }

    public static void main(String[] args) {
        String s = "/H:/Anning/project-dev-repository/ALiYun/target/classes/systemParam.properties";
        System.out.println(s);
        s = s.replaceAll("/","\\"+File.separator);
        System.out.println(s);
    }

}
