package com.dennis.blog.service;

import com.dennis.blog.dto.QuestionDTO;
import com.dennis.blog.mapper.QuestionMapper;
import com.dennis.blog.mapper.UserMapper;
import com.dennis.blog.model.Question;
import com.dennis.blog.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {

        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question : questions){
            User user =userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //复制属性
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        return questionDTOList;
    }
}
