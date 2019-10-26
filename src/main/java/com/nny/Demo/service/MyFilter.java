package com.nny.Demo.service;

import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 学习Reactive下的Filter实现
 * 参考博客：https://wanshi.iteye.com/blog/2409097
 * 实现WebFilter,实现抽象方法：Mono<Void> filter(ServerWebExchange serverWebExchange,WebFilterChain webFilterChain)
 */
@Component
@Order(0)
public class MyFilter implements WebFilter {

    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {

        AtomicReference<String> bodyRef = new AtomicReference<>(); //缓存读取的request body信息

        ServerHttpRequest request =  serverWebExchange.getRequest();
        Flux<DataBuffer> body = request.getBody();
        body.subscribe(
                dataBuffer -> {
                    CharBuffer charBuffer = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer());
                    DataBufferUtils.release(dataBuffer);
                    bodyRef.set(charBuffer.toString());
                }
        );//读取request body到缓存
        String bodyStr = bodyRef.get(); //获取request body
        System.out.println("输出："+bodyStr);

        ServerHttpResponse response =  serverWebExchange.getResponse();
        String tokenValue = request.getHeaders().getFirst("token");
        request.getBody();
        if(null == tokenValue){
//            ServerHttpRequest authErrorReq = request.mutate().path("/account/getRankingList").method(HttpMethod.GET).build();
//            ServerWebExchange authErrorExchange = serverWebExchange.mutate().request(authErrorReq).build();
//            return webFilterChain.filter(authErrorExchange);



            // 直接返回响应结果，跳过后面的filter和controller
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response.writeWith(
                    Mono.just(
                            response.bufferFactory().wrap("{\"msg\":\"no token\"}".getBytes())
                    )
            );
        }
        else {
            return webFilterChain.filter(serverWebExchange);
        }
    }

}
