package io.github.forezp.config;

import io.github.forezp.entity.RouteRule;

import java.util.List;

/**
 * Created by forezp on 2019/5/21.
 */
public interface ConfigLoader {

    List<RouteRule> getRouteRule();

}
