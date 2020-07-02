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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by forezp on 2019/5/18.
 */

public class McfRoutePredict extends AbstractServerPredicate {

    Logger logger=LoggerFactory.getLogger(McfRoutePredict.class);

    private ConfigLoader configLoader;

    private RouteAdapter routeAdapter;

    public McfRoutePredict(ConfigLoader configLoader, RouteAdapter routeAdapter) {
        this.configLoader = configLoader;
        this.routeAdapter = routeAdapter;
    }



    @Override
    public boolean apply(PredicateKey predicateKey) {
        return predicateKey != null &&
                doApply(predicateKey.getServer());
    }


    private boolean doApply(Server server) {
        logger.info(server.getHost() + ":" + server.getPort() + ":" +server.getMetaInfo().getAppName());
        return routeAdapter.apply(server, getCandidateRouteRule(server));
    }


    private RouteRule getCandidateRouteRule(Server server){
        String serverName=server.getMetaInfo().getAppName();
        logger.info("serverName:"+serverName);
        if(StringUtils.isEmpty(serverName)){
          return null;
        }
        List<RouteRule> list=configLoader.getRouteRule();
        if(!CollectionUtils.isEmpty(list)){
            for (RouteRule routeRule:list){
                if(serverName.equals(routeRule.getName())){
                    return routeRule;
                }

            }
        }
        return null;
    }

}