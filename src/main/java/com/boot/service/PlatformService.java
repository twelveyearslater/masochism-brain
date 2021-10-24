package com.boot.service;

import com.boot.entity.Platform;

import java.util.List;
import java.util.Map;

/**
 * 平台信息接口
 */
public interface PlatformService {

    // 新增平台信息
    int addPlatform(Platform platform);

    // 查询平台信息
    List<Platform> queryPlatformList(Integer userId);

    // 将平台信息拆成 map<id,name>和map<id,url>;
    Map<String, Map<Integer, String>> queryPlatforms2MapArr();

    // 查询前20条平台信息
    List<Platform> queryPlatformForSelect();
}
