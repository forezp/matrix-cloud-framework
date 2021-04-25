package io.github.forezp.loader;

import io.github.forezp.configure.RouteProperties;
import io.github.forezp.entity.RouteRule;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by forezp on 2019/6/17.
 */
public class DefaultConfigLoader implements RouteRuleLoader {

    @Autowired
    RouteProperties routeProperties;

    @Override
    public RouteRule loadRouteRule() {

        RouteRule routeRule = new RouteRule();
        routeRule.setEnable(routeProperties.getEnable());
        routeRule.setServices(routeProperties.getServices());
        return routeRule;
    }
}
