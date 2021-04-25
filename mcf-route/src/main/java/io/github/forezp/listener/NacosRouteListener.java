package io.github.forezp.listener;


import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import io.github.forezp.cache.RouteRuleCache;
import io.github.forezp.entity.RouteRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.concurrent.Executor;

import static io.github.forezp.constants.RouteConstants.ROUTE_CACHE;



@RefreshScope
public class NacosRouteListener implements InitializingBean {

    Logger logger = LoggerFactory.getLogger(NacosRouteListener.class);

    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private NacosConfigManager nacosConfigManager;
    @Autowired
    private NacosConfigProperties configProperties;

    @Autowired
    RouteRuleCache routeRuleCache;

    @Override
    public void afterPropertiesSet() throws Exception {

        //启动时获取，后面监听
        String configInfo = nacosConfigManager.getConfigService().getConfig(appName + "-route.json", "ROUTE_GROUP", 30000);
        logger.info(configInfo);
        RouteRule routeRule = JSON.parseObject(configInfo, RouteRule.class);
        if (routeRule != null) {
            routeRuleCache.put(ROUTE_CACHE, routeRule);
        }
        nacosConfigManager.getConfigService().addListener(appName + "-route.json", "ROUTE_GROUP",
                new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return null;
                    }

                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        logger.info(configInfo);
                        RouteRule routeRule = JSON.parseObject(configInfo, RouteRule.class);
                        if (routeRule != null) {
                            routeRuleCache.put(ROUTE_CACHE, routeRule);
                        }
                    }
                });
    }
}
