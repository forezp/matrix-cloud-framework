package io.github.forezp.configure;


import io.github.forezp.entity.RouteEntity;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
@RefreshScope
@ConfigurationProperties(prefix = "swr.route")
public class RouteProperties {


    private Boolean enable;
    private List<RouteEntity> services;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<RouteEntity> getServices() {
        return services;
    }

    public void setServices(List<RouteEntity> services) {
        this.services = services;
    }
}

