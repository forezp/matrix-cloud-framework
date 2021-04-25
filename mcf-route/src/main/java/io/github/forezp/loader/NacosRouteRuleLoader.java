package io.github.forezp.loader;


import io.github.forezp.cache.RouteRuleCache;
import io.github.forezp.entity.RouteRule;
import org.springframework.beans.factory.annotation.Autowired;

import static io.github.forezp.constants.RouteConstants.ROUTE_CACHE;

public class NacosRouteRuleLoader implements RouteRuleLoader {

    @Autowired
    RouteRuleCache authRuleCache;


    @Override
    public RouteRule loadRouteRule() {
        RouteRule routeRule = (RouteRule) authRuleCache.get(ROUTE_CACHE);
        return routeRule;
    }
}
