package com.boot.web;

import com.boot.entity.Menu;
import com.boot.entity.Result;
import com.boot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ResponseBody
    @RequestMapping(value="/getCommon",method = RequestMethod.GET)
    public Result getMenus(HttpServletRequest request) {
        Result result = new Result();
        List<Menu> menus = menuService.getMenus();
        if (menus == null || menus.size() == 0) {
            result.setSuccess(false);
            result.setMsg("内部错误！");
        } else {
            result.setSuccess(true);
            result.setData(menus);
        }
        return result;
    }
}
