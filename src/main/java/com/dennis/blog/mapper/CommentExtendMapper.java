package com.dennis.blog.mapper;

import com.dennis.blog.model.Comment;

public interface CommentExtendMapper {
    int incCommentCount(Comment record);
}