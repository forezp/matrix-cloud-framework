package io.github.forezp.loader;


import io.github.forezp.cache.LimitRuleCache;

import io.github.forezp.entity.LimitRule;
import org.springframework.beans.factory.annotation.Autowired;

import static io.github.forezp.constant.LimitConstants.LIMIT_CACHE;


public class NacosLimitRuleLoader implements LimitRuleLoader {

    @Autowired
    LimitRuleCache authRuleCache;


    @Override
    public LimitRule load() {
        LimitRule limitRule = (LimitRule) authRuleCache.get(LIMIT_CACHE);
        return limitRule;
    }
}
