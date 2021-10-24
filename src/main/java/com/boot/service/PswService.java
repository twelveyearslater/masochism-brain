package com.boot.service;


import com.boot.entity.PageData;
import com.boot.entity.PswMsg;
import com.boot.entity.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * 密码信息的业务层
 * Created by eleven on 2018/12/20.
 */
public interface PswService {

    //查询密码信息清单
    List<PswMsg> queryPswList(Integer userId);
    //通过id获取一条密码信息
    PswMsg queryPswById(Integer id);
    //添加一条密码信息
    boolean addPswMsg(PswMsg pswMsg) throws Exception;
    //通过id删除一条密码信息
    boolean deletePswMsg(Integer id);
    //修改一条密码信息
    boolean modifyPswMsg(PswMsg pswMsg);
    //读取文件中的密码信息
    List<PswMsg> readFile2PwdList(File file,Integer userId);
    //用户初次生成密钥对，返回私钥
    String getInitKeys(Integer userId);
    //根据用户查询分页数据
    PageData queryPswPageList(PageData pageData);
    // 导出Excel
    void exportExcel(HttpServletRequest request, HttpServletResponse response, String userId) throws Exception;
    // 解析Excel并存储密码信息
    boolean parseExcel(HttpServletRequest request, MultipartFile file, Result result) throws Exception;
}
