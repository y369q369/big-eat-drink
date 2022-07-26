package com.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author gs
 * @Date 2021-11-15 19:27
 * @Description
 */
@Slf4j
public class SwaggerStartUtil {

    public static void start(Class<?> primarySource, String... args){
        ConfigurableApplicationContext application = SpringApplication.run(primarySource, args);
        Environment env = application.getEnvironment();
        String applicationName = "";
        String contextPath = env.getProperty("server.servlet.context-path");
        if (contextPath == null) {
            contextPath = "";
        } else {
            applicationName = contextPath.substring(1);
        }
        String address = null;
        try {
            address = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取真实ip异常", e);
            address = "127.0.0.1";
        }
        String port = env.getProperty("server.port");
        String external = "http://" + address + ":" + port + contextPath;
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application {} is running! Access URLs: \n\t" +
                        "External: \t {} \n\t"+
                        "Doc: \t\t {}/doc.html \n"+
                        "----------------------------------------------------------",
                applicationName,
                external,
                external);
    }
}
