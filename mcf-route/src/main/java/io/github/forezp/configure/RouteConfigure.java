package io.github.forezp.configure;

import com.netflix.loadbalancer.IRule;
import io.github.forezp.ribbonrule.McfRouteRule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by forezp on 2019/6/17.
 */
@Configuration
@ConditionalOnProperty(havingValue = "mcf.route.enable",matchIfMissing = false)
public class RouteConfigure {




    @Bean
    public IRule routeRule(){
        return new McfRouteRule();
    }
}
