package com.example.springoverview;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 安全性验证
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置）
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);

        applicationContext.refresh();

        //演示BeanFactory#getBean的安全性
        displayBeanFactoryGetBean(applicationContext);
        //演示ObjectFactory#getObject方法的安全性
        displayObjectBeanFactoryGetBean(applicationContext);
        //演示ObjectProvider
        displayObjectProviderIfAvailableGetBean(applicationContext);

        applicationContext.close();
    }


    public static void displayObjectProviderIfAvailableGetBean(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> objectProvider = context.getBeanProvider(User.class);
        printBeansException("displayObjectBeanFactoryGetBean", () -> objectProvider.getIfAvailable());
    }

    public static void displayObjectBeanFactoryGetBean(AnnotationConfigApplicationContext context) {
        ObjectFactory<User> objectFactory = context.getBeanProvider(User.class);
        printBeansException("displayObjectBeanFactoryGetBean", () -> objectFactory.getObject());
    }

    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeansException(String source, Runnable runnable) {

        System.out.println("Source from " + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }


}
