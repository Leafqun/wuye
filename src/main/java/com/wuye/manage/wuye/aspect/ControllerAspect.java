package com.wuye.manage.wuye.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Pointcut("execution(public * com.wuye.manage.wuye.*.controller.*.*(..))")
    public void webLog() {}

    @AfterThrowing(pointcut = "webLog()")
    public void doAfterThrow(JoinPoint point) {
        log.error(point.getSignature().getDeclaringTypeName() + "中的" + point.getSignature().getName() + "方法抛出异常");
    }
}
