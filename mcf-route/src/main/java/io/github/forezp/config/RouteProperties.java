package io.github.forezp.config;


import io.github.forezp.entity.RouteRule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
@RefreshScope
@ConfigurationProperties(prefix = "mcf.route")
public class RouteProperties {


    private Boolean enable;
    private List<RouteRule> services;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<RouteRule> getServices() {
        return services;
    }

    public void setServices(List<RouteRule> services) {
        this.services = services;
    }
}

