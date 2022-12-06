package com.example.springoverview;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 层次性依赖查找示例
 */
public class HierarchicaDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class（配置）
        applicationContext.register(HierarchicaDependencyLookupDemo.class);

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();

        applicationContext.refresh();
        applicationContext.close();
    }

    /**
     * 命名Bean 的注册方式
     *
     * @param registry
     * @param beanName
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 1);
        beanDefinitionBuilder.addPropertyValue("name", "robinGao");
        if (StringUtils.hasLength(beanName)) {
            //注册BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    // 2. 通过@Componment 方式
    @Component
    public static class Config {

        // 1. 通过@Bean 方式定义
        @Bean(name = {"user", "robin-user"})
        public User user() {
            User user = new User();
            user.setId(1);
            user.setName("robin Gao");
            return user;
        }
    }

}
