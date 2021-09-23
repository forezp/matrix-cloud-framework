package io.github.forezp.mcfgatewayroute.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import io.github.forezp.configure.Metadata;
import io.github.forezp.context.SwrContextHolder;
import io.github.forezp.entity.GlobalRouteEntity;
import io.github.forezp.entity.RouteRule;
import io.github.forezp.loader.RouteRuleLoader;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


import java.util.List;

import static io.github.forezp.constant.SwrContextConstants.*;


public class DefaultGatewayInitFilter implements GatewayInitFilter {

    Logger log = LoggerFactory.getLogger(DefaultGatewayInitFilter.class);

    @Autowired(required = false)
    Metadata metadata;

    @Autowired(required = false)
    RouteRuleLoader routeRuleLoader;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        SwrContextHolder.get().setServerWebExchange(exchange);
        // 通过过滤器设置路由Header头部信息，并全链路传递到服务端
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder requestBuilder = request.mutate();
        if (metadata != null) {
            String metadataStr = JSON.toJSONString(metadata);
            setHeader(requestBuilder, SWR_META_DATA, metadataStr, true);
        }

        //传 g_version;g_version_weight;
        setGHeader(G_VERSION, request, requestBuilder);
        setGHeader(G_VERSION_WEIGHT, request, requestBuilder);
        ServerHttpRequest newRequest = requestBuilder.build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
        // 把新的ServerWebExchange放入ThreadLocal中
        SwrContextHolder.get().setServerWebExchange(newExchange);
        return chain.filter(newExchange);

    }

    @Override
    public int getOrder() {
        return 9000;
    }

    /**
     * 优先从http请求的header中获取，如果没有，则从RuleLoader中获取
     *
     * @param headerName
     * @param request
     * @param requestBuilder
     */
    private void setGHeader(String headerName, ServerHttpRequest request, ServerHttpRequest.Builder requestBuilder) {
        List<String> gVersionHeaders = request.getHeaders().get(headerName);
        if (!CollectionUtils.isEmpty(gVersionHeaders) && gVersionHeaders.size() > 0) {
            setHeader(requestBuilder, headerName, gVersionHeaders.get(0), true);
        } else {
            if (routeRuleLoader == null) {
                return;
            }
            RouteRule routeRule = routeRuleLoader.loadRouteRule();
            if (routeRule != null) {
                GlobalRouteEntity globalRouteEntity = routeRule.getGlobal();
                if (globalRouteEntity != null && StrUtil.isNotBlank(globalRouteEntity.getG_version())) {
                    setHeader(requestBuilder, headerName, globalRouteEntity.getG_version(), true);
                }
            }
        }
    }

    private void setHeader(ServerHttpRequest.Builder requestBuilder, String headerName, String headerValue, boolean gatewayHeaderPriority) {
        if (StringUtils.isEmpty(headerValue)) {
            return;
        }
        if (gatewayHeaderPriority) {
            // 需要把外界的Header清除
            requestBuilder.headers(headers -> headers.remove(headerName));
        }
        // 不管外界是否传递了Header，网关侧都加入Header
        // 当外界没传递了Header，由网关侧Header来替代
        // 当外界传递了Header，虽然网关侧也添加了Header，但传递到调用链的还是第一个Header。参考exchange.getRequest().getHeaders().getFirst(name)
        // 在spring-web-5.1.9.RELEASE版本中，requestBuilder.header(headerName, headerValue)已经标识为@Deprecated，为兼容5.1.8版本，改为如下代码
        requestBuilder.headers(headers -> headers.add(headerName, headerValue));
    }
}
