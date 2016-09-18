package com.phej.frame.argsname;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAopPointcutExample {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "Beans.xml");
        try {
            Employee employee = (Employee) context.getBean("emp");
            employee.setAge(32);
            System.out.println("Employee age changed at " + employee.getChanged());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception: " + e);
            }
            employee.setName("Joe");
            System.out.println("Employee name " + employee.getName());
            System.out.println("Employee name changed at " + employee.getChanged());

            try {
                employee.someSecuredInfo();
                System.out.println("shouldn't be able to access secured info");
            } catch (IllegalStateException ise) {
                System.out.println("tried to access secured info");
            }
            AuditAopBean auditAopBean = (AuditAopBean) context.getBean("auditBean");
            System.out.println("property access count: " + auditAopBean.getPropertyAccessCount());

            PersistenceHelper persistenceHelper = (PersistenceHelper) context.getBean("persistenceApi");
            persistenceHelper.save(employee);
            System.out.println("save count: " + auditAopBean.getSaveCount());
        } finally {
            context.close();
        }
    }
}