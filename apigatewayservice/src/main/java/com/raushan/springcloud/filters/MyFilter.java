package com.raushan.springcloud.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MyFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Pre Processing Logic Goes here " + exchange.getRequest());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            System.out.println("Post Processing Logic Goes Here " + exchange.getResponse());
        }));
    }
}
