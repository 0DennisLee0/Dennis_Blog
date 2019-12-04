package com.dennis.blog.service;

import com.dennis.blog.exception.CustomizeErrorCode;
import com.dennis.blog.exception.CustomizeException;
import com.dennis.blog.model.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    public void insert(Comment comment) {

        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
    }
}
