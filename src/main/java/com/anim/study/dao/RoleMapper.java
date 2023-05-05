package com.anim.study.dao;

import com.anim.study.data.body.PagerDataBody;
import com.anim.study.domain.Role;
import java.util.List;

public interface RoleMapper {

    List<Role> selectByCategoryKey(Integer id);

    List<Role> findRolesByPage(PagerDataBody<Integer> pagerDataBody);

    long findRolesCount(Integer id);
}