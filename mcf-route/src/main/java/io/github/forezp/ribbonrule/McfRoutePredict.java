package io.github.forezp.ribbonrule;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import com.netflix.loadbalancer.Server;
import io.github.forezp.adapter.RouteAdapter;
import io.github.forezp.discovery.PluginAdapter;
import io.github.forezp.entity.RouteRule;
import io.github.forezp.loader.RouteRuleLoader;
import io.github.forezp.entity.RouteEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by forezp on 2019/5/18.
 */

public class McfRoutePredict extends AbstractServerPredicate {

    Logger logger = LoggerFactory.getLogger(McfRoutePredict.class);

    private RouteRuleLoader ruleLoader;

    private RouteAdapter routeAdapter;

    @Autowired
    PluginAdapter pluginAdapter;

    public McfRoutePredict(RouteRuleLoader ruleLoader, RouteAdapter routeAdapter, PluginAdapter pluginAdapter) {
        this.ruleLoader = ruleLoader;
        this.routeAdapter = routeAdapter;
        this.pluginAdapter = pluginAdapter;
    }


    @Override
    public boolean apply(PredicateKey predicateKey) {
        return predicateKey != null &&
                doApply(predicateKey.getServer());
    }


    private boolean doApply(Server server) {
        boolean result = routeAdapter.apply(server, getCandidateRouteRule(server));
        logger.info("route apply:" + server.getHost() + ":" + server.getPort() + ":" + server.getMetaInfo().getAppName() + ",result:" + result);
        return result;
    }


    private RouteEntity getCandidateRouteRule(Server server) {
        String serverName = pluginAdapter.getServiceName(server);
        if (StringUtils.isEmpty(serverName)) {
            return null;
        }
        RouteRule routeRule = ruleLoader.loadRouteRule();
        if (routeRule == null) {
            return null;
        }
        List<RouteEntity> list = routeRule.getServices();
        if (!CollectionUtils.isEmpty(list)) {
            for (RouteEntity routeEntity : list) {
                if (serverName.equals(routeEntity.getSwr_service_name())) {
                    return routeEntity;
                }
            }
        }
        return null;
    }

}