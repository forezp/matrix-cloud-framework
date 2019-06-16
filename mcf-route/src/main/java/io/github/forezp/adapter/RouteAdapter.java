package io.github.forezp.adapter;

import com.netflix.loadbalancer.Server;
import io.github.forezp.entity.RouteRule;

/**
 * Created by forezp on 2019/6/16.
 */
public interface RouteAdapter {

    boolean apply(Server server, RouteRule routeRule);

}
