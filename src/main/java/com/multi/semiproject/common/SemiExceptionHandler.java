package com.multi.semiproject.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class SemiExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ModelAndView exceptionHandler(Exception ex){

        log.info("Exception 발생: {}",ex.getMessage());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");

        return mv;
    }

}
