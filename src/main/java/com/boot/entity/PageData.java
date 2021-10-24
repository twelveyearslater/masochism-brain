package com.boot.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageData {

    public int currentPage; // 当前页
    public int firstCount;  // 第一条数据顺序数
    public int lastCount;   // 最后一条数据顺序数
    public int totalCount;  // 总条数
    public int pageCount;   // 单页个数
    public String orderBy;  // 排序字段
    public Map<String, Object> position = new HashMap<>();  // 查询条件
    public List data;   // 结果集

    public PageData(){}

    public PageData(int currentPage, int pageCount){
        this.currentPage = currentPage;
        this.pageCount = pageCount;
        this.firstCount = pageCount * (currentPage - 1);
        this.lastCount = firstCount + pageCount ;
    }

    public void addPosition(String key, Object value) {
        position.put(key, value);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getFirstCount() {
        return firstCount;
    }

    public void setFirstCount(int firstCount) {
        this.firstCount = firstCount;
    }

    public int getLastCount() {
        return lastCount;
    }

    public void setLastCount(int lastCount) {
        this.lastCount = lastCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
