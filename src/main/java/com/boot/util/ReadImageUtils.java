package com.boot.util;

import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.Util;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class ReadImageUtils {

    private static AipOcr client;

    static {
        client = new AipOcr(Constants.OCR_APP_ID, Constants.OCR_API_Key, Constants.OCR_SECRET_Key);
    }

    /**
     * 读取图片（）
     * @param imageType 图片类型（1：图片路径，2：图片二进制数组，3：远程图片URL）
     * @param image 图片
     * @return 返回图片中的文字（字符串数组）
     */
    public static String[] readWordsOnImage(int imageType, String image) {
        JSONObject res = null;
        HashMap<String, String> options = new HashMap<>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");

        if (imageType == 1) {
            // 参数为本地图片路径
            res = client.basicGeneral(image, options);
            System.out.println(res.toString());
        } else if (imageType == 2) {
            // 参数为本地图片二进制数组
            byte[] file = readImageFile(image);
            if (file == null) return null;
            res = client.basicGeneral(file, options);
            System.out.println(res.toString());
        } else if (imageType == 3) {
            // 通用文字识别, 图片参数image为远程url图片
            res = client.basicGeneralUrl(image, options);
            System.out.println(res.toString());
        }
        if (res == null) return null;
        return parseWords(res);
    }

    private static byte[] readImageFile(String image) {
        try {
            return Util.readFileByBytes(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将平台返回的JSON对象解析为文字数组
     * @param jsonObj 远程接口返回的Json
     * @return 返回文字数组
     */
    private static String[] parseWords(JSONObject jsonObj) {
        JSONArray jsonArr = jsonObj.getJSONArray("words_result");
        String[] resultArr = new String[jsonArr.length()];
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject OCRResultObj = jsonArr.getJSONObject(i);
            resultArr[i] = OCRResultObj.getString("words");
        }
        return resultArr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(ReadImageUtils.readWordsOnImage(1, "d://test2.png")));
    }
}
