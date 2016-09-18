package com.phej.frame.argsname;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AuditAopBean {

    @Pointcut(value = "args(a)", argNames = "a")
    public void auditFields(Object a) {
    }

    @Around(value = "auditFields(value)", argNames = "value")
    public void doAudit(ProceedingJoinPoint pjp, Object value) throws Throwable {
        if (pjp.getSignature().getName().startsWith("set")) {
            pjp.proceed(new Object[]{value});
            Object target = pjp.getTarget();
            if (target instanceof AuditAware) {
                AuditAware audit = (AuditAware) target;
                audit.setChanged(new Date());
            }
        }
    }
    @Pointcut("execution(* *.*SecuredInfo())")
    public void securedInfo() {
    }

    @Before("securedInfo()")
    public void dontAllowAccessToSecuredInfo() {
        System.out.println("dontAllowAccessToSecuredInfo called");
        throw new IllegalStateException();
    }

    @Pointcut("execution(!void get*())")
    public void propertAccessOccurrence() {
    }

    @AfterReturning("propertAccessOccurrence()")
    public void incrementPropertAccess() {
        propertyAccessCount++;
    }

    @Pointcut("execution(* com.phej.frame.argsname.PersistenceApi.save(..))")
    public void saveAnInterfaceMethodCall() {
    }

    @Before("saveAnInterfaceMethodCall()")
    public void incrementSave() {
        System.out.println("increment save");
        saveCount++;
    }

    @Pointcut("execution(* com.javarticles.Employee.*(..))")
    public void anyStringReturningMethod() {
    }

    @AfterReturning(pointcut = "anyStringReturningMethod()", returning = "returnValue")
    public void stringReturnValue(String returnValue) {
        if (returnValue != null) {
            System.out.println("stringReturnValue: " + returnValue);
        }
    }
    public int getPropertyAccessCount() {
        return propertyAccessCount;
    }

    public int getSaveCount() {
        return saveCount;
    }

    private int propertyAccessCount;
    private int saveCount;
}