package com.boot.dao;

import com.boot.entity.Article;

import java.util.List;

public interface ArticleDao {

    // 新增日志
    int add();
    // 删除日志
    int delete();
    // 修改文章
    int update();
    // 根据条件获取文章列表
    List<Article> getArticles();

}
