package com.boot.util;

//import com.sun.media.jfxmedia.track.Track;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Raytine on 2019/4/20.
 */
public class FileUtil {

    /*
    * 查看文件是否存在
    * */
    public boolean exist(File file){
        return file.exists();
    }

    /*
    * 检查文件名后缀
    * */
    public boolean validateFileType(String fileName, String types[]){
        for(String type : types) {
            if(fileName.substring(fileName.lastIndexOf('.')).equals(type)){
                return true;
            }
        }
        return false;
    }

    /*
    * 按行读取文本文件
    * */
    public List<String> readTxtByLine(File file) throws Exception {
        List<String> list = new ArrayList<String>();
        BufferedReader br = null;
        String encoding = null;
        if(!exist(file)){
            throw new Exception("文件不存在");
        }
        try{
            encoding = resolveEncoding(file);
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
            String temp = "";
            while((temp = br.readLine())!= null){
                if(temp.length()>5){
                    list.add(temp);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            br.close();
        }
        return list;
    }

    /*
    * 判断文本文件的编码格式
    *
    * 注意：
    * 1. txt默认的选项是ANSI，即GBK编码
    * 2. txt文本文档有四种编码选项：ANSI、Unicode、Unicode big endian、UTF-8
    * 3. 因此我们读取txt文件可能有时候并不知道其编码格式，所以需要用程序动态判断获取txt文件编码
    *
    * ANSI： 无格式定义
    * Unicode：  前两个字节为FFFE Unicode文档以0xFFFE开头
    * Unicode big endian： 前两字节为FEFF
    * UTF-8： 前两字节为EFBB UTF-8以0xEFBBBF开头
    *
    * */
    public String resolveEncoding(File sourceFile) throws Exception {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                return charset; //文件编码为 ANSI
            } else if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE"; //文件编码为 Unicode
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE"; //文件编码为 Unicode big endian
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8"; //文件编码为 UTF-8
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                        // (0x80
                        // - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
            bis.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(charset);
        return charset;
    }

    // 获取文件后缀
    public static String getFileSuffix(String filename){
        return filename.substring(filename.lastIndexOf("."));
    }

    // 生成上传文件的文件名
    public static String newFileNameByUUID(String fileOriginName){
        return UUID.randomUUID().toString().replace("-", "") + getFileSuffix(fileOriginName);
    }

    public static boolean upload(MultipartFile file, String path, String filename){

        String realPath = path + File.separator + filename;
        File dest = new File(realPath);
        // 判断文件路径是否存在，不存在则创建
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            // 保存文件
            file.transferTo(dest);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String arg[]){
        try{
            List<String> list = new FileUtil().readTxtByLine(new File("C:\\Users\\Raytine\\Desktop\\pwd.txt"));
            for(String str : list){
                System.out.println(str);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void createDirHowever(String path) throws Exception{
        File file = new File(path);
        if(! file.exists()){
            file.mkdirs();
        }
    }


    // MultipartFile 转 File
    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    // 获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
