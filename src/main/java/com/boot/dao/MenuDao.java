package com.boot.dao;

import com.boot.entity.Menu;

import java.util.List;

public interface MenuDao {

    // 根据条件获取文章列表
    List<Menu> getMenus();
}
