package com.boot.service;

import com.boot.entity.Area;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by eleven on 2018/12/10.
 */
public interface AreaService {

    List<Area> queryArea();

    Area queryAreaById(int areaId);

    boolean addArea(Area area);

    boolean modifyArea(Area area);

    boolean deleteArea(int areaId);

    List<LinkedHashMap> test(int start, int end);

}
