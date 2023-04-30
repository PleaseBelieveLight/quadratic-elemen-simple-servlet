package com.anim.study.dao;

import com.anim.study.domain.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectAllCategory();

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}