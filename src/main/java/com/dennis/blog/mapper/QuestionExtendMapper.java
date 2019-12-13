package com.dennis.blog.mapper;

import com.dennis.blog.dto.QuestionQueryDTO;
import com.dennis.blog.model.Question;

import java.util.List;

public interface QuestionExtendMapper {
    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}