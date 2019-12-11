package com.dennis.blog.dto;

import com.dennis.blog.model.User;
import lombok.Data;

@Data
public class NotificationDTO {

    private Integer id;

    private Long gmtCreate;

    private Integer status;

    private User notifier;

    private String outerTitle;

    private String type;
}
