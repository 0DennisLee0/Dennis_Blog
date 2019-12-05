package com.dennis.blog.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001, "弟弟你这⑧行啊，问题都没有你找NM"),
    TARGET_PARAM_NOT_FOUND(2002, "虚空评论？属实憨憨"),
    NO_LOGIN(2003, "没登录你发个锤子，右上角看不见？"),
    SYS_ERROR(2004,"Server is booming"),
    TYPE_PARAM_WRONG(2005,"评论瞎JB写/没得评论，这下挂了⑧"),
    COMMENT_NOT_FOUND(2006,"评论⑧在，弟弟拜拜");

    private String message;
    private Integer code;

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message){
        this.message = message;
        this.code = code;
    }

}
