package com.dennis.blog.mapper;

import com.dennis.blog.model.Question;

public interface QuestionExtendMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}