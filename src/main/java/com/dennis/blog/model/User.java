package com.dennis.blog.model;

import lombok.Data;

@Data
public class User {

    private Integer id;

    private String name;

    private String token;

    private String accountId;

    private Long gmtCreate;

    private Long gmtModified;

    private String avatarUrl;

}
