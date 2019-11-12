package com.wuye.manage.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Pointcut("execution(public * com.wuye.manage.wuye.*.controller.*.*(..))")
    public void webLog() {}

    @AfterThrowing(pointcut = "webLog()")
    public void doAfterThrow(JoinPoint point) {
        log.error("参数：");
        for (int i = 0; i < point.getArgs().length; i++) {
            log.error(((MethodSignature)point.getSignature()).getParameterNames()[i] + ": " + point.getArgs()[i]);
        }
        log.error(point.getSignature().getDeclaringTypeName() + "中的" + point.getSignature().getName() + "方法抛出异常");
    }
}
