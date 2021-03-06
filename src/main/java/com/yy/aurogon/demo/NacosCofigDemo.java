package com.yy.aurogon.demo;

import java.util.Properties;
import java.util.concurrent.Executor;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;

import static com.alibaba.nacos.api.PropertyKeyConst.NAMESPACE;
import static com.alibaba.nacos.api.PropertyKeyConst.SERVER_ADDR;

/**
 * @author liuzicong
 */
public class NacosCofigDemo {


    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.put(SERVER_ADDR, "127.0.0.1:8848");
        properties.put(NAMESPACE, "local");

        ConfigService config = NacosFactory.createConfigService(properties);

        // 获取配置
        String content = config.getConfig("test", "member-aggregation", 5000);
        System.out.println(content);

        // 注册监听器
        config.addListener("test", "member-aggregation", new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(final String configInfo) {
                System.out.println("receive:" + configInfo);
            }
        });


        System.in.read();
    }
}
