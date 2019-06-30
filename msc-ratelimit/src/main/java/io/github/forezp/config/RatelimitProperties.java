package io.github.forezp.config;

import io.github.forezp.entity.GlobalLimitRule;
import io.github.forezp.entity.RatelimitRule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 *
 * Created by forezp on 2019/5/4.
 *
 * rate.limit.enable: true
 * rate.limit.global.num: 100
 * rate.limit.global.second: 1
 * rate.limit.services:
 *    - name: mcf-provider
 *      num: 10
 *      second: 11
 *    - name: mcf-consumer
 *      num: 23
 *      second: 11
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "rate.limit")
public class RatelimitProperties {

    Boolean enable;
    GlobalLimitRule global;
    List<RatelimitRule> services;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public GlobalLimitRule getGlobal() {
        return global;
    }

    public void setGlobal(GlobalLimitRule global) {
        this.global = global;
    }

    public List<RatelimitRule> getServices() {
        return services;
    }

    public void setServices(List<RatelimitRule> services) {
        this.services = services;
    }
}

