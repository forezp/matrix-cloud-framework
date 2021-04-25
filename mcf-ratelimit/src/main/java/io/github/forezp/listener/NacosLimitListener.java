package io.github.forezp.listener;


import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import io.github.forezp.cache.LimitRuleCache;
import io.github.forezp.entity.LimitRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.concurrent.Executor;

import static io.github.forezp.constant.LimitConstants.LIMIT_CACHE;


@RefreshScope
public class NacosLimitListener implements InitializingBean {

    Logger logger = LoggerFactory.getLogger(NacosLimitListener.class);

    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private NacosConfigManager nacosConfigManager;
    @Autowired
    private NacosConfigProperties configProperties;

    @Autowired
    LimitRuleCache limitRuleCache;

    @Override
    public void afterPropertiesSet() throws Exception {

        //启动时获取，后面监听
        String configInfo = nacosConfigManager.getConfigService().getConfig(appName + "-limit.json", "LIMIT_GROUP", 30000);
        logger.info(configInfo);
        LimitRule limitRule = JSON.parseObject(configInfo, LimitRule.class);
        if (limitRule != null) {
            limitRuleCache.put(LIMIT_CACHE, limitRule);
        }
        nacosConfigManager.getConfigService().addListener(appName + "-limit.json", "LIMIT_GROUP",
                new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return null;
                    }

                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        logger.info(configInfo);
                        LimitRule limitRule= JSON.parseObject(configInfo, LimitRule.class);
                        if (limitRule != null) {
                            limitRuleCache.put(LIMIT_CACHE, limitRule);
                        }
                    }
                });
    }
}
