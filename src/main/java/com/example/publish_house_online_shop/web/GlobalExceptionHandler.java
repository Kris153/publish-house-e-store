package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFound() {
        ModelAndView modelAndView = new ModelAndView("object-not-found");
        return modelAndView;
    }
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ModelAndView handleClientErrorNotFound() {
        ModelAndView modelAndView = new ModelAndView("object-not-found");
        return modelAndView;
    }
}
