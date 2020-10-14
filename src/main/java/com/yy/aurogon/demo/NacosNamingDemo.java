package com.yy.aurogon.demo;

import java.util.Properties;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;

import static com.alibaba.nacos.api.PropertyKeyConst.NAMESPACE;
import static com.alibaba.nacos.api.PropertyKeyConst.SERVER_ADDR;

/**
 * @author liuzicong
 */
public class NacosNamingDemo {


    public static final String SERVICE_NAME = "NacosNamingDemo";
    public static final String GROUP_NAME = "membership";

    private NacosNamingDemo() { }

    public static void main(String[] args) throws Exception {
        final Properties properties = new Properties();
        properties.put(SERVER_ADDR, "127.0.0.1:8848");
        properties.put(NAMESPACE, "local");
        final NamingService naming = NamingFactory.createNamingService(properties);

        naming.registerInstance(SERVICE_NAME, "membership", "127.0.0.1", 8888 );
        naming.registerInstance(SERVICE_NAME, "membership", "127.0.0.1", 8889 );

        System.out.println(naming.getAllInstances("NacosNamingDemo", "membership"));

        naming.subscribe(SERVICE_NAME, "membership", event -> {
            System.out.println("Refresh Event:" + ((NamingEvent) event).getServiceName() + "," + ((NamingEvent) event).getInstances());
        });

        System.in.read();
    }
}
