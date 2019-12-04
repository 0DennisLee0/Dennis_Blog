package com.dennis.blog.controller;

import com.dennis.blog.dto.CommentDTO;
import com.dennis.blog.dto.ResultDTO;
import com.dennis.blog.mapper.CommentMapper;
import com.dennis.blog.model.Comment;
import com.dennis.blog.model.User;
import com.dennis.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class CommentController {

    @Autowired
    private CommentService commentService;

    //    @RequestMapping(value="/comment", method = RequestMethod.POST)
    @ResponseBody
    @PostMapping("/comment")
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(2002, "没登录你发个锤子，右上角看不见？");
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setLikeCount(0L);
        commentService.insert(comment);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("message", "成功");
        return objectObjectHashMap;
    }
}
