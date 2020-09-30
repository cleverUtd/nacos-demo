package com.yy.aurogon.demo;

import java.io.IOException;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

/**
 * @author liuzicong
 */
@EnableDubbo(scanBasePackages = "com.yy.aurogon.demo")
@PropertySource(value = "classpath:/provider.properties")
public final class ProviderApplication {

    private ProviderApplication() {}

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ProviderApplication.class);
        context.refresh();
        System.out.println("ProviderApplication is starting...");
        System.in.read();
    }



}
