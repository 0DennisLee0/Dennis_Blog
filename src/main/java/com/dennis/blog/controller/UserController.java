package com.dennis.blog.controller;

import com.dennis.blog.mapper.UserMapper;
import com.dennis.blog.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getUserList")
    public Page<User> getUserList(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize")Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Page<User> userList = userMapper.getUserList();
        return userList;
    }

}
