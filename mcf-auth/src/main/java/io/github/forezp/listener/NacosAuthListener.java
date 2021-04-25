package io.github.forezp.listener;


import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import io.github.forezp.cache.AuthRuleCache;
import io.github.forezp.entity.AuthRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.concurrent.Executor;

import static io.github.forezp.constant.AuthConstants.AUTH_CACHE;


@RefreshScope
public class NacosAuthListener implements InitializingBean {

    Logger logger = LoggerFactory.getLogger(NacosAuthListener.class);

    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private NacosConfigManager nacosConfigManager;
    @Autowired
    private NacosConfigProperties configProperties;

    @Autowired
    AuthRuleCache authRuleCache;

    @Override
    public void afterPropertiesSet() throws Exception {

        //启动时获取，后面监听
        String configInfo = nacosConfigManager.getConfigService().getConfig(appName + "-auth.json", "AUTH_GROUP", 30000);
        logger.info(configInfo);
        AuthRule authRule = JSON.parseObject(configInfo, AuthRule.class);
        if (authRule != null) {
            authRuleCache.put(AUTH_CACHE, authRule);
        }
        nacosConfigManager.getConfigService().addListener(appName + "-auth.json", "AUTH_GROUP",
                new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return null;
                    }

                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        logger.info(configInfo);
                        AuthRule authRule = JSON.parseObject(configInfo, AuthRule.class);
                        if (authRule != null) {
                            authRuleCache.put(AUTH_CACHE, authRule);
                        }
                    }
                });
    }
}
