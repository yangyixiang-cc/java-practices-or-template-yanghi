package com.yanghi.study.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author 泗安
 */
@Component
public class TokenCheckFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 先获取ServerHttpRequest对象，注意不是HttpServletRequest
        ServerHttpRequest request = exchange.getRequest();
        // 获取请求头中的token
        Optional<List<String>> tokens = Optional.ofNullable(request.getHeaders().get("token"));
        List<String> list = tokens.orElseGet(() -> {
            List<String> strings = new ArrayList<>();
            strings.add("");
            return strings;
        });
        // 判断token是否存在值，存在则放行，不存在则直接返回响应
        if(!list.get(0).equals("")){
            return chain.filter(exchange);
        }else{
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
