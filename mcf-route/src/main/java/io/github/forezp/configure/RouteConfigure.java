package io.github.forezp.configure;

import com.netflix.loadbalancer.IRule;
import io.github.forezp.adapter.ConsulPluginAdapter;
import io.github.forezp.adapter.DefaultRouteAdapter;
import io.github.forezp.adapter.PluginAdapter;
import io.github.forezp.adapter.RouteAdapter;
import io.github.forezp.config.ConfigLoader;
import io.github.forezp.config.DefaultConfigLoader;
import io.github.forezp.ribbonrule.McfRouteRule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by forezp on 2019/6/17.
 */
@Configuration
@ConditionalOnProperty(name = "mcf.route.enable",havingValue = "true")
public class RouteConfigure {

    @Bean
    @ConditionalOnMissingBean
    public ConfigLoader configLoader(){
        return new DefaultConfigLoader();
    }


    @Bean
    @ConditionalOnMissingBean
    public PluginAdapter pluginAdapter(){
        return new ConsulPluginAdapter();
    }

    @Bean
    @ConditionalOnMissingBean
    public RouteAdapter routeAdapter(){
        return new DefaultRouteAdapter();
    }


    @Bean
    public IRule routeRule(){
        return new McfRouteRule(configLoader(),routeAdapter());
    }
}
