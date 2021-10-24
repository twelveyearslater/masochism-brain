package com.boot.mongodb;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoManager {
    /**
     * MongoClient的实例代表数据库连接池，是线程安全的，可以被多线程共享，客户端在多线程条件下仅维持一个实例即可
     * Mongo是非线程安全的，目前mongodb API中已经建议用MongoClient替代Mongo
     */
    private MongoClient mongoClient = null;
    private static com.boot.mongodb.MongoManager MongoManager = null;
    private String hostConfString = "47.94.7.121:2888";
    private String database = "webapp";
    private String username = "webapp";
    private String password = "a347852901";

    /**
     * 构造方法
     *
     * @throws UnknownHostException
     */
    private MongoManager(){
        if (mongoClient != null) return;
        try {
            //ServerAddress()两个参数分别为 服务器地址 和 端口
            String[] address = hostConfString.split(":");//读取服务IP地址和端口号
            ServerAddress serverAddress = new ServerAddress(address[0], Integer.valueOf(address[1]));

            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
            MongoCredential mongoCredential = MongoCredential.createCredential(username, database, password.toCharArray());
            //通过连接认证获取MongoDB连接
//            MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(mongoCredential));

            MongoClientOptions.Builder build = new MongoClientOptions.Builder();
            /*
             * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
             * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
             * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
             */
            build.maxWaitTime(1000 * 60 * 2);
            build.connectTimeout(1000 * 60 * 1);    //与数据库建立连接的timeout设置为1分钟
            build.socketTimeout(0);// 套接字超时时间，0无限制
            build.connectionsPerHost(300);   //连接池设置为300个连接,默认为100
            build.threadsAllowedToBlockForConnectionMultiplier(5000);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
            build.writeConcern(WriteConcern.ACKNOWLEDGED);
            MongoClientOptions myOptions = build.build();
            try {
                //数据库连接实例
                mongoClient = new MongoClient(Arrays.asList(serverAddress), mongoCredential, myOptions);
            } catch (MongoException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取实例
     *
     * @return
     */
    public synchronized static com.boot.mongodb.MongoManager getInstance() {
        if (MongoManager == null) {
            MongoManager = new MongoManager();
        }
        return MongoManager;
    }

    public MongoCollection<HashMap> getCollection(String dbName, String collectionName) {
        return getDatabase(dbName).getCollection(collectionName, HashMap.class);
    }

    public MongoDatabase getDatabase(String dbName) {
        // create codec registry for POJOs（为POJO创建编解码器注册表）
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        return mongoClient.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry);
    }

    public long getCount(String dbName, String collectionName) {
        return getDatabase(dbName).getCollection(collectionName).estimatedDocumentCount();
    }

    public void createCollection(String dbName, String collectionName) {
        getDatabase(dbName).createCollection(collectionName);
    }

    public void insertOne(String dbName, String collectionName, Document document) {
        getDatabase(dbName).getCollection(collectionName).insertOne(document);
    }

    public static void main(String[] args) {
        new MongoManager().createCollection("webapp", "test200810");
    }

}
