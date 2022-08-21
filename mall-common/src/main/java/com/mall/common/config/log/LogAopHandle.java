package com.mall.common.config.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author sbn
 * @date 2022/7/28 9:45
 */
@Component
@Aspect
@Slf4j
public class LogAopHandle extends BaseLogAspect {

    @Pointcut("@annotation(com.mall.common.config.log.LogAnnotation)")
    public void requestServer() {
    }

    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        if (annotation != null) {
            long start = System.currentTimeMillis();
            RequestInfo beforeInfo = new RequestInfo();
            beforeInfo.setDesc(annotation == null ? "方法无描述说明" : annotation.desc());
            beforeInfo.setRequestParams(getRequestParamsByProceedingJoinPoint(proceedingJoinPoint));
            log.info("方法入参:{}", JSON.toJSONString(beforeInfo, SerializerFeature.IgnoreErrorGetter));
            Object result = proceedingJoinPoint.proceed();
            if (annotation.whetherPrintReturnInfo()) {
                RequestInfo afterInfo = new RequestInfo();
                afterInfo.setDesc(annotation == null ? "方法无描述说明" : annotation.desc());
                afterInfo.setResult(result);
                afterInfo.setTimeCost(System.currentTimeMillis() - start);
                log.info("方法出参:{}", JSON.toJSONString(afterInfo));
            } else {
                log.info("执行完毕:{}", beforeInfo.getDesc());
            }
            return result;
        }
        return proceedingJoinPoint.proceed();
    }
}
