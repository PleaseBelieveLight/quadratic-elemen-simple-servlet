package com.anim.study.dao;

import com.anim.study.domain.Collect;
import com.anim.study.domain.CollectRole;

import java.util.List;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    List<Collect> selectByPrimaryKey(Integer userId);

    List<CollectRole> selectCollectRoles(Integer userId);
}