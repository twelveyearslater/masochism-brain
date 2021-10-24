package com.boot.entity;

import java.util.Date;

public class Emoji {
    // 表情ID
    private int id;
    // 表情名称（用户描述）
    private String name;
    // UUID+文件名
    private String filename;
    // 上传用户ID
    private int userId;
    // 上传时间
    private Date createTime;
    // 相似度比较（哈希值）
    private String hashValue;
    // ocr识别文字1
    private String ocrWord1;
    // ocr识别文字2
    private String ocrWord2;
    // ocr识别文字3
    private String ocrWord3;
    // ocr识别文字4
    private String ocrWord4;
    // ocr识别文字5
    private String ocrWord5;
    // ocr识别文字（全部）
    private String ocrAllWord;
    // 0：未公开； 1：公开
    private int isPublic = 1;
    // 0：未删除； 1：已删除
    private int isRemove = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public String getOcrWord1() {
        return ocrWord1;
    }

    public void setOcrWord1(String ocrWord1) {
        this.ocrWord1 = ocrWord1;
    }

    public String getOcrWord2() {
        return ocrWord2;
    }

    public void setOcrWord2(String ocrWord2) {
        this.ocrWord2 = ocrWord2;
    }

    public String getOcrWord3() {
        return ocrWord3;
    }

    public void setOcrWord3(String ocrWord3) {
        this.ocrWord3 = ocrWord3;
    }

    public String getOcrWord4() {
        return ocrWord4;
    }

    public void setOcrWord4(String ocrWord4) {
        this.ocrWord4 = ocrWord4;
    }

    public String getOcrWord5() {
        return ocrWord5;
    }

    public void setOcrWord5(String ocrWord5) {
        this.ocrWord5 = ocrWord5;
    }

    public String getOcrAllWord() {
        return ocrAllWord;
    }

    public void setOcrAllWord(String ocrAllWord) {
        this.ocrAllWord = ocrAllWord;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public int getIsRemove() {
        return isRemove;
    }

    public void setIsRemove(int isRemove) {
        this.isRemove = isRemove;
    }
}
