package com.example.example.web.rest.errors;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Component
public class ApiErrorAtributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String ,Object> allErrorAtributes=super.getErrorAttributes(webRequest, options);
        Map<String ,Object>errorAtributes=new HashMap<>();
        int statusCode=(int)allErrorAtributes.get("status");
        errorAtributes.put("estado", HttpStatus.valueOf(statusCode));
        errorAtributes.put("fecha", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/mm/yy hh:mm:ss")));

        String message="";
        Throwable throwable=getError(webRequest);
        if(throwable instanceof ResponseStatusException responseStatusException){
            message=responseStatusException.getReason()==null?"":responseStatusException.getReason();
        }else {
            if(throwable.getCause()!=null){
                message=throwable.getCause().getMessage()==null?"":throwable.getCause().getMessage();
            }

        }
        errorAtributes.put("mensaje",message);

        return errorAtributes;
    }
}
