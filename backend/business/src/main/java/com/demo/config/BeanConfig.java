package com.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author gs
 * @Date 2022-08-18 15:36
 * @Description 普通类获取bean
 */
@Component
public class BeanConfig implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 通过类名获取 Bean
     * @param clazz 类名
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过name获取 Bean
     */
    public static  <T> T getBean(String name){
        Object object = new Object();
        if (object == null) {
            return null;
        } else {
            return (T) applicationContext.getBean(name);
        }
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     */
    public static <T> T getBean(String name, Class<T> clazz){
        return applicationContext.getBean(name, clazz);
    }

}
