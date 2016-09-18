package com.phej.frame.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class Logging {
    /**
     * Following is the definition for a pointcut to select all the methods
     * available. So advice will be called for all the methods.
     */
    @Pointcut("execution(* com.phej.frame.aspect.*.*(..))")
    private void selectAll() {
    }

    @Around("selectAll()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("已经记录下操作日志@Around 方法执行前");
        Object obj = pjp.proceed();
        System.out.println("已经记录下操作日志@Around 方法执行后");
        return obj;
    }

    /**
     * This is the method which I would like to execute before a selected method
     * execution.
     */
    @Before("selectAll()")
    public void beforeAdvice() {
        System.out.println("Going to setup student profile.");
    }

    /**
     * This is the method which I would like to execute after a selected method
     * execution.
     */
    @After("selectAll()")
    public void afterAdvice() {
        System.out.println("Student profile has been setup.");
    }

    /**
     * This is the method which I would like to execute when any method returns.
     */
    @AfterReturning(pointcut = "selectAll()", returning = "retVal")
    public void afterReturningAdvice(Object retVal) {
        System.out.println("Returning:" + retVal.toString());
    }

    /**
     * This is the method which I would like to execute if there is an exception
     * raised by any method.
     */
    @AfterThrowing(pointcut = "selectAll()", throwing = "ex")
    public void AfterThrowingAdvice(IllegalArgumentException ex) {
        System.out.println("There has been an exception: " + ex.toString());
    }

}