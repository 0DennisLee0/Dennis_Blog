package com.dennis.blog.cache;

import com.dennis.blog.dto.TagDTO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TagCache {

    public static List<TagDTO> get(){

        List<TagDTO> tagDTOS= new ArrayList<>();

        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("php","java","node.js","python","c++","c","golang","spring","django","flask","springboot","后端","c#","swoole","ruby","asp.net","ruby-on-rails","scala","rust","lavarel","爬虫"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("laravel","spring","express","django","flask","yii","ruby-on-rails","tornado","koa","structs"));
        tagDTOS.add(framework);

        TagDTO maintainance = new TagDTO();
        maintainance.setCategoryName("运维");
        maintainance.setTags(Arrays.asList("linux","nginx","docker","apache","centos","ubuntu","服务器负载","均衡运维","ssh","vagrant","容器","jenkins","devops","debian","fabric"));
        tagDTOS.add(maintainance);

        return tagDTOS;

    }

    public static String filterInvalid(String tags){

        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));

        return invalid;
    }

}

