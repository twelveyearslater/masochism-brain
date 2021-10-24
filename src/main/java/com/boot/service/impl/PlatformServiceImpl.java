package com.boot.service.impl;

import com.boot.dao.PlatformDao;
import com.boot.entity.Platform;
import com.boot.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    private PlatformDao PlatformDao;

    @Override
    public int addPlatform(Platform platform) {
        Date date = new Date();
        platform.setCreateTime(date);
        platform.setUpdateTime(date);
        int result = PlatformDao.addPlatform(platform);
        return result;
    }

    @Override
    public List<Platform> queryPlatformList(Integer userId) {
        List<Platform> platformList = PlatformDao.queryPlatform();
        return platformList;
    }

    @Override
    public Map<String, Map<Integer, String>> queryPlatforms2MapArr() {
        Map<String, Map<Integer, String>> maps = new HashMap<>();
        Map<Integer, String> mapA = new HashMap<>();
        Map<Integer, String> mapB = new HashMap<>();
        List<Platform> platformList = PlatformDao.queryPlatform();
        for(Platform platform : platformList){
            mapA.put(platform.getId(), platform.getPlatformName());
            mapB.put(platform.getId(), platform.getIconUrl());
        }
        maps.put("name", mapA);
        maps.put("url", mapA);
        return maps;
    }

    public List<Platform> queryPlatformForSelect() {
        return PlatformDao.queryPlatformSoMuchUseful();
    }
}
