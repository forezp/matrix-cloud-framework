package io.github.forezp.loader;


import io.github.forezp.entity.LimitEntity;
import io.github.forezp.entity.LimitRule;
import io.github.forezp.configure.RatelimitProperties;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by forezp on 2019/6/7.
 */
public class LocalLimitRuleLoader implements LimitRuleLoader {

    /**
     * Created by forezp on 2019/5/4.
     * <p>
     * rate.limit.enable: true
     * rate.limit.global.num: 100
     * rate.limit.global.second: 1
     * rate.limit.services:
     * - name: mcf-provider
     * num: 10
     * second: 11
     * - name: mcf-consumer
     * num: 23
     * second: 11
     */
    @Autowired
    RatelimitProperties ratelimitProperties;

    @Override
    public LimitRule load() {
        LimitRule limitRule = new LimitRule();
        limitRule.setType(ratelimitProperties.getType());
        limitRule.setEnable(ratelimitProperties.getEnable());
        LimitEntity global = ratelimitProperties.getGlobal();
        global.setName("global");
        limitRule.setGlobal(global);
        limitRule.setServices(ratelimitProperties.getServices());
        return limitRule;
    }
}