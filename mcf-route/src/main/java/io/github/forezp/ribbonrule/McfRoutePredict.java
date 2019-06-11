package io.github.forezp.ribbonrule;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import com.netflix.loadbalancer.Server;
import io.github.forezp.config.ConfigLoader;
import io.github.forezp.entity.RouteRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by forezp on 2019/5/18.
 */

public class McfRoutePredict extends AbstractServerPredicate {

    private ConfigLoader configLoader;

    private RouteRule routeRule;



    public McfRoutePredict( ConfigLoader configLoader){
        this.configLoader=configLoader;
        this.routeRule=configLoader.getRouteRule();
    }

    Logger logger= LoggerFactory.getLogger(McfRoutePredict.class);

    @Override
    public boolean apply( PredicateKey predicateKey) {
        return predicateKey != null &&
                apply1(predicateKey.getServer());
    }


    private boolean apply1(Server server) {
        logger.info(server.getHost()+":"+server.getPort()+server.getMetaInfo().getAppName());
        server.getMetaInfo();

        if(server.getPort()==8762){
            return false;
        }
        return true;
    }
}