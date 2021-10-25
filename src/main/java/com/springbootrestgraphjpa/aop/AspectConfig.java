package com.springbootrestgraphjpa.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AspectConfig {

  Logger logger = LoggerFactory.getLogger(getClass());

  //    @Before(value = "execution(* com.springbootrestgraphjpa.controller.*.*(..))")
  //    public void beforeAdvice(JoinPoint joinPoint) {
  //        logger.info("Inside before advice");
  //    }

  @Before(value = "execution(* com.springbootrestgraphjpa.controller.*.*(..)) and args(object)")
  public void beforeAdvice(JoinPoint joinPoint, Object object) {
    logger.info("Before request: {}", object);
  }

  @After(value = "execution(* com.springbootrestgraphjpa.controller.*.*(..)) and args(object)")
  public void afterAdvice(JoinPoint joinPoint, Object object) {
    logger.info("After request: {}", object);
  }

  @AfterReturning(
      value = "execution(* com.springbootrestgraphjpa.controller.*.*(..)) and args(object)",
      returning = "returningObject")
  public void afterReturningAdvice(JoinPoint joinPoint, Object object, Object returningObject) {
    logger.info("After returning response: {}", returningObject);
  }

  @Around(value = "execution(* com.springbootrestgraphjpa.controller.*.*(..)) and args(object)")
  public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint, Object object) {
    logger.info("Around request: {}", object);

    Object returningObject = null;
    try {
      returningObject = proceedingJoinPoint.proceed();
    } catch (Throwable e) {
      e.printStackTrace();
    }

    logger.info("Around response: {}", returningObject);
  }
}
