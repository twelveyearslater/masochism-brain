package com.boot.service.impl;

import com.boot.dao.AreaDao;
import com.boot.entity.Area;
import com.boot.service.AreaService;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by eleven on 2018/12/11.
 */

@Service
public class AreaServiceImpl implements AreaService{

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> queryArea() {
        return areaDao.queryArea();
    }

    @Override
    public Area queryAreaById(int areaId) {
        return areaDao.queryAreaById(areaId);
    }

    @Override
    @Transactional
    public boolean addArea(Area area) {
        if(null != area.getAreaName() && !"".equals(area.getAreaName())){
            area.setCreateTime(new Date());
            area.setLastEditTime(new Date());
            try {
                int effectedNum = areaDao.insertArea(area);
                if(effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("插入地区信息失败！");
                }
            }catch(RuntimeException e){
                throw new RuntimeException("插入地区信息失败：" + e.getMessage());
            }
        } else {
            throw new RuntimeException("区域信息不能为空！");
        }
    }

    @Override
    @Transactional
    public boolean modifyArea(Area area) {
        if (null != area.getAreaId() && area.getAreaId() > 0) {
            area.setLastEditTime(new Date());
            try {
                int effectedNum = areaDao.updateArea(area);
                if (effectedNum > 0){
                    return true;
                } else {
                    throw new RuntimeException("更新区域信息失败！");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException("更新区域信息失败：" + e.getMessage());
            }
        } else {
            throw new RuntimeException("区域编号不能为空或者小于零！");
        }
    }

    @Override
    @Transactional
    public boolean deleteArea(int areaId) {
        if (areaId > 0){
            try {
                int effectedNum = areaDao.deleteArea(areaId);
                if(effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("删除区域信息失败！");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException("删除区域信息失败：" + e.getMessage());
            }
        } else {
            throw new RuntimeException("区域编号不能小于零！");
        }
    }

    @Override
    public List<LinkedHashMap> test(int start, int end){
        HashMap<String, Object> map = new HashMap<>();
        map.put("start",start);
        map.put("end",end);
//        List<LinkedHashMap> list = areaDao.queryAble(map);
        List<LinkedHashMap> list = new ArrayList<>();
        areaDao.getCursor(map, resultContext -> {
            // 回调处理逻辑
            list.add(resultContext.getResultObject());
        });
        return list;
    }

}
