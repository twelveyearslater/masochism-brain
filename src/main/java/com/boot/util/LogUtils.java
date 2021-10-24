package com.boot.util;

import com.boot.mongodb.MongoManager;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class LogUtils {

    private String database = "webapp";

    public void saveRequestLog(Document log, Date current) {
        String collectionName = getRequestLogName(current);
        MongoCollection<HashMap> collection = MongoManager.getInstance().getCollection(database, collectionName);
        if (collection == null) MongoManager.getInstance().createCollection(database, collectionName);  // 如果没有该月日志表则创建一个
        MongoManager.getInstance().insertOne(database, collectionName, log);
    }

    public void saveSystemLog(Document log, Date current) {
        String collectionName = getSystemLogName(current);
        MongoCollection<HashMap> collection = MongoManager.getInstance().getCollection(database, collectionName);
        if (collection == null) MongoManager.getInstance().createCollection(database, collectionName);  // 如果没有该月日志表则创建一个
        MongoManager.getInstance().insertOne(database, collectionName, log);
    }

    public void saveExceptionLog(Document log) {
        MongoCollection<HashMap> collection = MongoManager.getInstance().getCollection(database, "ExceptionLog");
        if (collection == null) MongoManager.getInstance().createCollection(database, "ExceptionLog");  // 如果没有该日志表则创建一个
        MongoManager.getInstance().insertOne(database, "ExceptionLog", log);
    }

    private String getRequestLogName(Date current) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return "RequestLog" + sdf.format(current);
    }

    private String getSystemLogName(Date current) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return "SystemLog" + sdf.format(current);
    }

}
