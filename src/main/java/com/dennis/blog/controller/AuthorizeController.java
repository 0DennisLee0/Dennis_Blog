package com.dennis.blog.controller;

import com.dennis.blog.dto.AccessTokenDTO;
import com.dennis.blog.dto.GithubUser;
import com.dennis.blog.mapper.UserMapper;
import com.dennis.blog.model.User;
import com.dennis.blog.provider.GithubProvider;
import com.dennis.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletResponse response){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();

        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if(githubUser != null){
            User user = new User();
            //UUID随机码
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userMapper.insert(user);
            userService.createOrUpdate(user);
            //put token into cookie
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
            //success, write into cookie and session
        }else{
            return "redirect:/";
            //failed
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie_token = new Cookie("token", null);
        Cookie cookie_jsessionid = new Cookie("JSESSIONID", null);
        cookie_token.setMaxAge(0);
        cookie_jsessionid.setMaxAge(0);
        response.addCookie(cookie_token);
        response.addCookie(cookie_jsessionid);
        return "redirect:/";
    }
}
