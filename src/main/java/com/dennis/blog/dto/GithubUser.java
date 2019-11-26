package com.dennis.blog.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    //fastjson可以直接把驼峰命名转换为数据表中的下划线
    private String avatarUrl;
}
