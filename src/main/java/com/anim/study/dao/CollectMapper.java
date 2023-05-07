package com.anim.study.dao;

import com.anim.study.domain.Collect;

import java.util.List;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    List<Collect> selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}