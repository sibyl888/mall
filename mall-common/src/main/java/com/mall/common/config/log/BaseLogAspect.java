package com.mall.common.config.log;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sbn
 * @date 2022/7/28 9:44
 */
@Slf4j
public class BaseLogAspect {

    @AfterThrowing(pointcut = "requestServer()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, Exception e) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation=method.getAnnotation(LogAnnotation.class);
        if(logAnnotation!=null){
            RequestErrorInfo requestErrorInfo = new RequestErrorInfo();
            requestErrorInfo.setDesc(logAnnotation.desc()==null?"方法无描述说明":logAnnotation.desc());
            requestErrorInfo.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
            requestErrorInfo.setExpMsg(e.getMessage());
            log.error("执行出现异常:{}", JSON.toJSONString(requestErrorInfo));
        }
    }

    /**
     * 获取入参
     * @param proceedingJoinPoint
     *
     * @return
     * */
    protected Map<String, Object> getRequestParamsByProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();

        return buildRequestParam(paramNames, paramValues);
    }

    protected Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = joinPoint.getArgs();

        return buildRequestParam(paramNames, paramValues);
    }

    protected Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
        Map<String, Object> requestParams = new HashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];
            requestParams.put(paramNames[i], value);
        }
        return requestParams;
    }

    @Data
    public class RequestInfo {
        private Object requestParams;
        private Object result;
        private Long timeCost;
        private String desc;
    }

    @Data
    public class RequestErrorInfo {
        private Object requestParams;
        private String expMsg;
        private String desc;
    }
}
