package com.dennis.blog.advice;

import com.dennis.blog.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(CustomizeException.class)
    ModelAndView handle(Throwable e, Model model) {

        if (e instanceof CustomizeException) {
            model.addAttribute("message", e.getMessage());
        }
        else {
            model.addAttribute("message", "坏起来了，先埋伏他一手");
        }
        return new ModelAndView("error");

    }

}
