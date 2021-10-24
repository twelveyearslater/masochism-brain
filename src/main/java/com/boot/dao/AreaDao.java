package com.boot.dao;

import com.boot.entity.Area;
import org.apache.ibatis.session.ResultHandler;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by eleven on 2018/12/9.
 */

public interface AreaDao {

    List<Area> queryArea();

    Area queryAreaById(int areaId);

    int insertArea(Area area);

    int updateArea(Area area);

    int deleteArea(int areaId);

    List<LinkedHashMap> queryAble(HashMap map);

    void getCursor(HashMap map, ResultHandler<LinkedHashMap> handler);

}
