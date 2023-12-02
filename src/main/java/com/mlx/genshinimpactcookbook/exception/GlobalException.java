package com.mlx.genshinimpactcookbook.exception;

import com.mlx.genshinimpactcookbook.domain.common.HttpMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Santa Antilles
 * @Date 2023/12/2-14:01
 */

@ControllerAdvice
public class GlobalException {
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public HttpMessage handleServiceException(ServiceException e){
        return new HttpMessage(e.getCode(), e.getMessage());
    }
}
