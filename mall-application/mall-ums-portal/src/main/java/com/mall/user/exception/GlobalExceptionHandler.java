package com.mall.user.exception;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.mall.common.Result;
import com.mall.common.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 全局异常
 *
 * @author sbn
 * @date 2022/8/15 9:43
 */
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final transient Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception ex) {
        log.error("全局异常:{}", ex.getMessage());
        ex.printStackTrace();
        return Result.failed(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result globalException(RuntimeException ex) {
        log.error("运行时异常:{}", ex);
        ex.printStackTrace();
        return Result.failed(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
    }

    /**
     * 用来处理bean validation异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationExceptionHandler(ConstraintViolationException ex) {
        String rspMsg = "";
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            StringJoiner stringJoiner = new StringJoiner("|");
            for (ConstraintViolation constraintViolation : constraintViolations) {
                stringJoiner.add(constraintViolation.getMessage());
            }
            rspMsg += "(" + stringJoiner + ")";
        }
        return Result.failed(ResultEnum.PARAM_ERROR.getCode(), String.format(ResultEnum.PARAM_ERROR.getMsg(), rspMsg));
    }

    @ExceptionHandler(BindException.class)
    public Result constraintViolationExceptionHandler(BindException ex) {
        String rspMsg = "";
        BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            StringJoiner stringJoiner = new StringJoiner("|");
            for (FieldError fieldError : fieldErrors) {
                stringJoiner.add(fieldError.getDefaultMessage());
            }
            rspMsg += "(" + stringJoiner + ")";
        }
        return Result.failed(ResultEnum.PARAM_ERROR.getCode(), String.format(ResultEnum.PARAM_ERROR.getMsg(), rspMsg));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String rspMsg = "";
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append("|");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            rspMsg += "(" + errorMessage + ")";
        }
        return Result.failed(ResultEnum.PARAM_ERROR.getCode(), String.format(ResultEnum.PARAM_ERROR.getMsg(), rspMsg));
    }


}
