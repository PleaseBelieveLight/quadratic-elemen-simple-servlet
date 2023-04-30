package com.anim.study.dao;

import com.anim.study.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(
            @Param("username") String name,
            @Param("password") String password
    );

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}