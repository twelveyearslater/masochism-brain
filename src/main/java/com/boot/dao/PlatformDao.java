package com.boot.dao;

import com.boot.entity.Platform;

import java.util.List;

/**
 * Created by eleven on 2018/12/18.
 */
public interface PlatformDao {

    // 添加平台信息
    int addPlatform(Platform platform);
    // 查询平台
    List<Platform> queryPlatform();
    // 查询最常被用的20个平台
    List<Platform> queryPlatformSoMuchUseful();
    // 通过名称查询平台对象
    Platform queryPlatformByName(String platformName);
    // 自增平台网站使用次数
    int autoIncrementCount(Platform platform);

}
