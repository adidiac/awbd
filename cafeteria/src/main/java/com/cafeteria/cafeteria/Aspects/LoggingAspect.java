package com.cafeteria.cafeteria.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.cafeteria.cafeteria.controllers.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Executing: " + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* com.cafeteria.cafeteria.controllers.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Executed: " + joinPoint.getSignature().toShortString() + ", Result: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.cafeteria.cafeteria.controllers.*.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in: " + joinPoint.getSignature().toShortString(), exception);
    }
}
