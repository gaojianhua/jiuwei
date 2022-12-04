package com.example.springoverview;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  Bean 实例化
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        String path = "classpath:/META-INF/bean-instantiation-context.xml";
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        User user = beanFactory.getBean("user-by-static",User.class);
        System.out.println(user);
    }

}
