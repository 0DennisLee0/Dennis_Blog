package com.dennis.blog.service;

import com.dennis.blog.dto.NotificationDTO;
import com.dennis.blog.dto.PaginationDTO;
import com.dennis.blog.mapper.NotificationMapper;
import com.dennis.blog.model.Notification;
import com.dennis.blog.model.NotificationExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO list(Integer userId, Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);
        totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;

        if (page > totalPage) {
            page = totalPage;
        }

        if (page < 1) {
            page = 1;
        }


        paginationDTO.setPagination(totalPage, page);

        //page = size *(page-1)
        Integer offset = size * (page - 1);

        if (offset < 0) {
            offset = 0;
        }

        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(userId);
        List<Notification> notifications =
                notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<NotificationDTO> notificationDTOS = new ArrayList<>();

        paginationDTO.setQuestions(notificationDTOS);
        return paginationDTO;

    }
}
