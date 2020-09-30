package com.yy.aurogon.demo;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

/**
 * @author liuzicong
 */
@EnableDubbo
@PropertySource(value = "classpath:/consumer.properties")
public class ConsumerApplication {

    @DubboReference(version = "1.0.0", retries = 0, loadbalance = "roundrobin")
    private DemoService demoService;

    public void sayHello() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.demoService.sayHello("nacos..."));
        }
    }


    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ConsumerApplication.class);
        context.refresh();

        final ConsumerApplication ca = (ConsumerApplication) context.getBean("consumerApplication");
        ca.sayHello();

        context.close();

    }
}
