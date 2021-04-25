package io.github.forezp.loader;

import io.github.forezp.cache.AuthRuleCache;
import io.github.forezp.entity.AuthRule;
import org.springframework.beans.factory.annotation.Autowired;

import static io.github.forezp.constant.AuthConstants.AUTH_CACHE;

public class NacosAuthRuleLoader implements AuthRuleLoader {

    @Autowired
    AuthRuleCache authRuleCache;


    @Override
    public AuthRule load() {
        AuthRule authRule = (AuthRule) authRuleCache.get(AUTH_CACHE);
        return authRule;
    }
}
