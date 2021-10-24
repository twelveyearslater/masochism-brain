package com.boot.util;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.UUID;

/**
 * Created by Raytine on 2019/11/3.
 */
public class YellowFileUtils {

    public static void copyFileRename(File resource, String targetPath) {

        FileChannel inputChannel = null;
        FileChannel outputChannel = null;

        if (!resource.exists()) return;
        String ext = resource.getName().substring(resource.getName().lastIndexOf("."));
        File newFile = new File(targetPath + File.separator + UUID.randomUUID() + ext);
        try {
            inputChannel = new FileInputStream(resource).getChannel();
            outputChannel = new FileOutputStream(newFile).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputChannel.close();
                inputChannel.close();
                resource.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        String target = "F:\\DD2";
        File rootFile = new File("F:\\SS\\星之cc");
        File[] files = rootFile.listFiles();
        for(File file : files) {
            if(file.isDirectory()){
                for(File f : file.listFiles()) {
                    if(f.isDirectory()){
                        for(File f1 : f.listFiles()) {
                            if(f1.isDirectory()){
                                if (f1 == null) continue;
                                for(File f2 : f1.listFiles()) {
                                    if(!f2.getName().trim().contains("jpg")) continue;
                                    copyFileRename(f2, target);
                                }
                            }
                            if(!f1.getName().trim().contains("jpg")) continue;
                            copyFileRename(f1, target);
                        }
                    }
                    if(!f.getName().trim().contains("jpg")) continue;
                    copyFileRename(f, target);
                }
            }
            if(!file.getName().trim().contains("jpg")) continue;
            copyFileRename(file, target);
        }
    }
}
