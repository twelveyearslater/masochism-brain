package com.boot.service.impl;

import com.boot.dao.MenuDao;
import com.boot.entity.Menu;
import com.boot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> getMenus() {
        return menuDao.getMenus();
    }
}
