package com.mall.common.config.log;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author sbn
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String desc() default "";

    boolean whetherPrintReturnInfo() default false;
}
