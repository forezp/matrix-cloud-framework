package io.github.forezp.ribbonrule;


import com.google.common.base.Optional;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.PredicateBasedRule;
import com.netflix.loadbalancer.Server;
import io.github.forezp.config.ConfigLoader;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by forezp on 2019/5/21.
 */
public abstract class AbstractPredicateBasedRule extends PredicateBasedRule {


    @Autowired
    ConfigLoader configLoader;

    @Override
    public Server choose(Object key) {
        ILoadBalancer lb = getLoadBalancer();
        Optional<Server> server = getPredicate().chooseRoundRobinAfterFiltering(lb.getAllServers(), key);
        if (server.isPresent()) {
            return server.get();
        } else {
            return null;
        }
    }
}
