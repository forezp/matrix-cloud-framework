package io.github.forezp.ribbonrule;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import com.netflix.loadbalancer.Server;
import io.github.forezp.adapter.PluginAdapter;
import io.github.forezp.adapter.RouteAdapter;
import io.github.forezp.config.ConfigLoader;
import io.github.forezp.config.McfCoreConfig;
import io.github.forezp.entity.RouteRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.consul.discovery.ConsulServer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by forezp on 2019/5/18.
 */

public class McfRoutePredict extends AbstractServerPredicate {

    private ConfigLoader configLoader;

    private RouteAdapter routeAdapter;

    public McfRoutePredict(ConfigLoader configLoader, RouteAdapter routeAdapter) {
        this.configLoader = configLoader;
        this.routeAdapter = routeAdapter;
    }

    Logger logger = LoggerFactory.getLogger(McfRoutePredict.class);

    @Override
    public boolean apply(PredicateKey predicateKey) {
        return predicateKey != null &&
                doApply(predicateKey.getServer());
    }


    private boolean doApply(Server server) {
        logger.info(server.getHost() + ":" + server.getPort() + server.getMetaInfo().getAppName());
        return routeAdapter.apply(server, configLoader.getRouteRule());
    }


}