package com.microservice.apigateway.filter;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
@Component
public class CustomFilterGatewayFilterFactory
        extends AbstractGatewayFilterFactory<CustomFilterGatewayFilterFactory.Config> {

    public CustomFilterGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            List<?> x = exchange.getRequest().getHeaders().get("X-CustomSum-x");
            List<?> y = exchange.getRequest().getHeaders().get("X-CustomSum-y");
            System.out.println(x);
            System.out.println(y);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                exchange.getResponse()
                        .getHeaders()
                        .add("X-CustomSum", String.valueOf("test"));
            }));
        };
    }

    @Data
    static class Config{
        private String myMessage;
    }

}

/**
 * This filter hashes the request body, placing the value in the X-Hash header.
 * Note: This causes the gateway to be memory constrained.
 * Sample usage: RequestHashing=SHA-256
 */
//import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.CACHED_SERVER_HTTP_REQUEST_DECORATOR_ATTR;
//@Component
//public class CustomFilterGatewayFilterFactory extends
//        AbstractGatewayFilterFactory<CustomFilterGatewayFilterFactory.Config> {
//
//    private static final String HASH_ATTR = "hash";
//    private static final String HASH_HEADER = "X-Hash";
//    private final List<HttpMessageReader<?>> messageReaders =
//            HandlerStrategies.withDefaults().messageReaders();
//
//    public CustomFilterGatewayFilterFactory() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        MessageDigest digest = config.getMessageDigest();
//        return (exchange, chain) -> ServerWebExchangeUtils
//                .cacheRequestBodyAndRequest(exchange, (httpRequest) -> ServerRequest
//                        .create(exchange.mutate().request(httpRequest).build(),
//                                messageReaders)
//                        .bodyToMono(String.class)
//                        .doOnNext(requestPayload -> exchange
//                                .getAttributes()
//                                .put(HASH_ATTR, computeHash(digest, requestPayload)))
//                        .then(Mono.defer(() -> {
//                            ServerHttpRequest cachedRequest = exchange.getAttribute(
//                                    CACHED_SERVER_HTTP_REQUEST_DECORATOR_ATTR);
//                            Assert.notNull(cachedRequest,
//                                    "cache request shouldn't be null");
//                            exchange.getAttributes()
//                                    .remove(CACHED_SERVER_HTTP_REQUEST_DECORATOR_ATTR);
//
//                            String hash = exchange.getAttribute(HASH_ATTR);
//                            cachedRequest = cachedRequest.mutate()
//                                    .header(HASH_HEADER, hash)
//                                    .build();
//                            return chain.filter(exchange.mutate()
//                                    .request(cachedRequest)
//                                    .build());
//                        })));
//    }
//
//    @Override
//    public List<String> shortcutFieldOrder() {
//        return Collections.singletonList("algorithm");
//    }
//
//    private String computeHash(MessageDigest messageDigest, String requestPayload) {
//        return Hex.toHexString(messageDigest.digest(requestPayload.getBytes()));
//    }
//
//    static class Config {
//
//        private MessageDigest messageDigest;
//
//        public MessageDigest getMessageDigest() {
//            return messageDigest;
//        }
//
//        public void setAlgorithm(String algorithm) throws NoSuchAlgorithmException {
//            messageDigest = MessageDigest.getInstance(algorithm);
//        }
//    }
//}