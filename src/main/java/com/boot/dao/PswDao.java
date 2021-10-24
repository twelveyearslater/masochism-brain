package com.boot.dao;

import com.boot.entity.PageData;
import com.boot.entity.PswMsg;
import org.apache.ibatis.session.ResultHandler;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by eleven on 2018/12/20.
 */
public interface PswDao {

    // 查询用户的全部密码信息
    List<PswMsg> getPswMsgByUserId(int id);
    // 通过id获取密码信息
    PswMsg getPswMsgById(int id);
    // 插入一条密码信息
    int insertPswMsg(PswMsg pswMsg);
    // 修改一条密码信息
    int updatePswMsg(PswMsg pswMsg);
    // 通过id删除一条密码信息
    int deletePswMsg(int id);
    // 根据用户查询分页数据
    List<PswMsg> getPswPageList(PageData page);
    // 根据用户查询总数量
    Integer getPswByUserCount(PageData page);
    // 查询导出数据（流式读取）
    void getCursorForExport(String userId, ResultHandler<LinkedHashMap> handler);
    // 批量插入用户密码信息
    int batchInsert(List<PswMsg> list);
}
