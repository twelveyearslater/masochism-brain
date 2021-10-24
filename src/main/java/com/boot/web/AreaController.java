package com.boot.web;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.boot.entity.Area;
import com.boot.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by eleven on 2018/12/11.
 */
@Controller
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @RequestMapping(value="/listarea",method = RequestMethod.GET)
    public Map<String,Object> listArea(){
        Map<String,Object> modelMap = new HashMap<>();
        List<Area> areaList = areaService.queryArea();
        modelMap.put("areaList", areaList);
        return modelMap;
    }

    @RequestMapping(value="/queryarea",method = RequestMethod.GET)
    public Map<String,Object> queryAreaById(Integer areaId){
        Map<String,Object> modelMap = new HashMap<>();
        Area area = areaService.queryAreaById(areaId);
        modelMap.put("area",area);
        return modelMap;
    }

    @RequestMapping(value="/addarea",method = RequestMethod.POST)
    public Map<String,Object> addArea(@RequestBody Area area){
        Map<String,Object> modelMap = new HashMap<>();
        boolean success = areaService.addArea(area);
        modelMap.put("success",success);
        return modelMap;
    }

    @RequestMapping(value="/modifyarea",method = RequestMethod.POST)
    public Map<String,Object> modifyArea(@RequestBody Area area){
        Map<String,Object> modelMap = new HashMap<>();
        boolean success = areaService.modifyArea(area);
        modelMap.put("success",success);
        return modelMap;
    }

    @RequestMapping(value="/deletearea",method = RequestMethod.GET)
    public Map<String,Object> deleteArea(Integer areaId){
        Map<String,Object> modelMap = new HashMap<>();
        boolean success = areaService.deleteArea(areaId);
        modelMap.put("success",success);
        return modelMap;
    }

    @RequestMapping(value="/test",method = RequestMethod.GET)
    public Map<String,Object> test(Integer start, Integer end, HttpServletResponse response){
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        ServletOutputStream out = null;

        try {
            out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

            // 设置EXCEL名称
            String fileName = new String(("SystemExcel").getBytes(), "UTF-8");

            // 设置SHEET名称
            Sheet sheet = new Sheet(1, 0);
            sheet.setSheetName("系统列表sheet1");

            // 设置标题
            Table table = new Table(1);
            List<List<String>> titles = new ArrayList<List<String>>();
            for(int i = 0; i<79; i++){
                titles.add(Arrays.asList("" + i));
            }
            table.setHead(titles);

            // 查询总数并 【封装相关变量 这块直接拷贝就行 不要改动】
            Integer totalRowCount = end;
            Integer pageSize = 500;
            Integer writeCount = totalRowCount % pageSize == 0 ? (totalRowCount / pageSize) : (totalRowCount / pageSize + 1);

            // 写数据 这个i的最大值直接拷贝就行了 不要改
            for (int i = 0; i < writeCount; i++) {
                List<List<Object>> dataList = new ArrayList<>();
                List<LinkedHashMap> list = areaService.test(i, pageSize);
                if (null != list && list.size() > 0) {
                    for(HashMap map:list){
                        List<Object> keyList = new ArrayList<>(map.values());
                        dataList.add(keyList);
                    }
                }
                System.out.println("第" + i + "次");
                writer.write1(dataList, sheet, table);
            }

            // 下载EXCEL
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            writer.finish();
            out.flush();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.gc();
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return modelMap;
    }


}
