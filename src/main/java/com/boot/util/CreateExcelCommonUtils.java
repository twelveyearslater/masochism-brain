package com.boot.util;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class CreateExcelCommonUtils {

    private String title;
    private List<String> columns;
    private String fileName;
    private Sheet sheet = null;
    private Table table = null;
    private ExcelWriter writer = null;
    private HttpServletResponse response;
    private ServletOutputStream out = null;

    public static CreateExcelCommonUtils getInstance(String title, List<String> columns, String fileName, HttpServletResponse response) throws IOException {
        CreateExcelCommonUtils createExcel = new CreateExcelCommonUtils();
        createExcel.setResponse(response);
        createExcel.setFileName(fileName);
        createExcel.setTitle(title);
        createExcel.setOut(response.getOutputStream());
        createExcel.setColumns(columns);
        createExcel.init();
        return createExcel;
    }

    private void init() {
        this.writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
        // 设置SHEET名称
        this.sheet = new Sheet(1, 0);
        sheet.setSheetName("sheet1");
        // 设置标题
        this.table = new Table(1);
        List<List<String>> titles = new ArrayList<>();
        for(int i = 0; i < columns.size(); i++){
            titles.add(Arrays.asList(columns.get(i)));
        }
        table.setHead(titles);
    }

    public void insertData(LinkedHashMap<String, Object> data) {
        List<List<Object>> dataList = new ArrayList<>();
        dataList.add(new ArrayList<>(data.values()));
        writer.write1(dataList, sheet, table);
    }

    public void output() throws IOException {
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        writer.finish();
        out.flush();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ExcelWriter getWriter() {
        return writer;
    }

    public void setWriter(ExcelWriter writer) {
        this.writer = writer;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public ServletOutputStream getOut() {
        return out;
    }

    public void setOut(ServletOutputStream out) {
        this.out = out;
    }
}
