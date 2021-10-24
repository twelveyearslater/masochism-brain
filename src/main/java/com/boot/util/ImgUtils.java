package com.boot.util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

public class ImgUtils {

    public static void toSmallNoShapeChange(File fromPic, File toPic) throws IOException {
        long size = fromPic.length();
        int v = Integer.parseInt("" + (size / 1024 / 1024));
        System.out.println(v);
        float f = 0f;
        switch (v){
            case 0:
                break;
            case 1:
                f = 0.75f;
                break;
            case 2:
            case 3:
                f = 0.6f;
                break;
            case 4:
            case 5:
            case 6:
                f = 0.4f;
                break;
            default:
                f = 0.25f;
        }
        Thumbnails.of(fromPic).scale(1f).outputQuality(f).toFile(toPic);
    }

    public static void main(String[] args) throws IOException {
        toSmallNoShapeChange(new File("C:\\Users\\xiaoc\\Desktop\\report_6.jpg"),new File("C:\\Users\\xiaoc\\Desktop\\s_report_6.jpg"));
        System.out.println("ok");
    }
}
