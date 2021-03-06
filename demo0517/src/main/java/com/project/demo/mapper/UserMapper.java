package com.project.demo.mapper;/*
@author jianghai
@data 2021 - 15:58
*/

import com.project.demo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Insert("insert into tab_user(name, password) values(#{name}, #{password})")
    int saveUser(@Param("name") String name, @Param("password") String password);

    @Select("select id, name, password from tab_user where name=#{name}")
    User selectUser(@Param("name") String name);
}
