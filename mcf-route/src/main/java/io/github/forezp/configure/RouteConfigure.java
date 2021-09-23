package io.github.forezp.configure;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.loadbalancer.IRule;
import io.github.forezp.cache.RouteRuleCache;
import io.github.forezp.discovery.ConsulPluginAdapter;
import io.github.forezp.adapter.DefaultRouteAdapter;
import io.github.forezp.discovery.NacosPluginAdapter;
import io.github.forezp.discovery.PluginAdapter;
import io.github.forezp.adapter.RouteAdapter;
import io.github.forezp.listener.NacosRouteListener;
import io.github.forezp.loader.NacosRouteRuleLoader;
import io.github.forezp.loader.RouteRuleLoader;
import io.github.forezp.loader.DefaultConfigLoader;
import io.github.forezp.ribbonrule.McfRouteRule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.consul.discovery.ConsulServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by forezp on 2019/6/17.
 */
@Configuration
@ConditionalOnProperty(name = "swr.route.enable", havingValue = "true")
public class RouteConfigure {

    @Bean
    @ConditionalOnClass(NacosConfigManager.class)
    public NacosRouteRuleLoader nacosRouteRuleLoader() {
        return new NacosRouteRuleLoader();
    }

    @Bean
    @ConditionalOnMissingBean
    public RouteRuleLoader routeRuleLoader() {
        return new DefaultConfigLoader();
    }

    @Bean
    public RouteRuleCache routeRuleCache() {
        return new RouteRuleCache();
    }


    @Bean
    @ConditionalOnClass(NacosConfigManager.class)
    NacosRouteListener nacosRouteListener() {
        return new NacosRouteListener();
    }


    @Bean
    @ConditionalOnClass(NacosServer.class)
    public PluginAdapter pluginAdapter() {
        return new NacosPluginAdapter();
    }

    @Bean
    @ConditionalOnMissingBean
    public RouteAdapter routeAdapter() {
        return new DefaultRouteAdapter();
    }


    @Bean
    public IRule routeRule(RouteRuleLoader ruleLoader, RouteAdapter routeAdapter, PluginAdapter pluginAdapter) {
        return new McfRouteRule(ruleLoader, routeAdapter, pluginAdapter);
    }
}
