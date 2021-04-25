package io.github.forezp.ribbonrule;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.AvailabilityPredicate;
import com.netflix.loadbalancer.CompositePredicate;
import io.github.forezp.adapter.RouteAdapter;
import io.github.forezp.discovery.PluginAdapter;
import io.github.forezp.loader.RouteRuleLoader;


/**
 * Created by forezp on 2019/5/18.
 */

public class McfRouteRule extends AbstractPredicateBasedRule {


    private CompositePredicate compositePredicate;
    private McfRoutePredict myBasePredicate;

    public McfRouteRule() {

    }

    public McfRouteRule(RouteRuleLoader configLoader, RouteAdapter routeAdapter, PluginAdapter pluginAdapter) {
        myBasePredicate = new McfRoutePredict(configLoader, routeAdapter, pluginAdapter);
        AvailabilityPredicate availabilityPredicate = new AvailabilityPredicate(this, null);
        compositePredicate = createCompositePredicate(myBasePredicate, availabilityPredicate);
    }

    private CompositePredicate createCompositePredicate(McfRoutePredict discoveryEnabledPredicate, AvailabilityPredicate availabilityPredicate) {
        return CompositePredicate.withPredicates(discoveryEnabledPredicate, availabilityPredicate)
                // .addFallbackPredicate(availabilityPredicate)
                // .addFallbackPredicate(AbstractServerPredicate.alwaysTrue())
                .build();
    }

    @Override
    public AbstractServerPredicate getPredicate() {
        return compositePredicate;
    }
}