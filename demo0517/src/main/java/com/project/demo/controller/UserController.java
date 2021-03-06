package com.project.demo.controller;/*
@author jianghai
@data 2021 - 15:51
*/

import com.mysql.cj.util.StringUtils;
import com.project.demo.domain.User;
import com.project.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    //表示用get的方式去请求
    @GetMapping("login")
    public String login(String name, String password) {
        if (StringUtils.isEmptyOrWhitespaceOnly(name)) {
            return "用户名不允许为空";
        }

        if (StringUtils.isEmptyOrWhitespaceOnly(password)) {
            return "密码不允许为空";
        }

        User user = userMapper.selectUser(name);
        if (user == null) return "登录失败";
        if (Objects.equals(password, user.getPassword())) return "登录成功";

        return "登录失败密码错误";
    }

    @GetMapping("register")
    public String register(String name, String password) {
        log.info("name:{}", name);
        log.info("password:{}", password);
        //数据库传过来的参数是不允许为空的
        if (StringUtils.isEmptyOrWhitespaceOnly(name)) {
            return "用户名不允许为空";
        }

        if (StringUtils.isEmptyOrWhitespaceOnly(password)) {
            return "密码不允许为空";
        }

        User user = userMapper.selectUser(name);
        if (user != null) return "注册失败，用户已经存在";
        //再这里写一个保存方法把用户数据进行保存
        int resultCount = userMapper.saveUser(name, password);

        return (resultCount == 0) ? "注册失败" : "注册成功";
    }

}
