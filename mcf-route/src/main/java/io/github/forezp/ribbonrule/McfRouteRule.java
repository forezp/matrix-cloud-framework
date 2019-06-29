package io.github.forezp.ribbonrule;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.AvailabilityPredicate;
import com.netflix.loadbalancer.CompositePredicate;


/**
 * Created by forezp on 2019/5/18.
 */

public class McfRouteRule extends AbstractPredicateBasedRule {

    private CompositePredicate compositePredicate;
    private McfRoutePredict myBasePredicate;


    public McfRouteRule() {
        myBasePredicate = new McfRoutePredict(configLoader,routeAdapter);
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