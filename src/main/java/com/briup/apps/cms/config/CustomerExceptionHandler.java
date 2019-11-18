package com.briup.apps.cms.config;

import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: app01
 * @description: 统一异常处理类
 * @author: charles
 * @create: 2019-03-13 21:03
 **/
@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(value =  Exception.class) // 捕获 Controller 中抛出的指定类型的异常，也可以指定其他异常
    public <E> Message handler(Exception exception){
        exception.printStackTrace();
        	//不希望所有的异常都报同样的信息，所以要进行判断
        if(exception instanceof CustomerException)   {
        	return MessageUtil.success(exception.getMessage());
        }
        
        return MessageUtil.error("后台接口异常");
    }
}
