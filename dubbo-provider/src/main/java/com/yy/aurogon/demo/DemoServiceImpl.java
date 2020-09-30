package com.yy.aurogon.demo;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author liuzicong
 */
@DubboService(version = "1.0.0")
public class DemoServiceImpl implements DemoService {


    @Override
    public String sayHello(final String name) {
        final RpcContext rpcContext = RpcContext.getContext();
        return String.format("[DemoService.%s, port: %s] : Hello,%s",
                rpcContext.getMethodName(),
                rpcContext.getLocalPort(),
                name
        );
    }
}
