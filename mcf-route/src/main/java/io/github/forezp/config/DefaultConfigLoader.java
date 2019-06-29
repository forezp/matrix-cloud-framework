package io.github.forezp.config;

import io.github.forezp.entity.RouteRule;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by forezp on 2019/6/17.
 */
public class DefaultConfigLoader implements ConfigLoader {

    @Autowired
    RouteProperties routeProperties;

    @Override
    public List<RouteRule> getRouteRule() {

        if (routeProperties.getEnable()) {
            return routeProperties.getServices();
        } else {
            return null;
        }
    }
}
