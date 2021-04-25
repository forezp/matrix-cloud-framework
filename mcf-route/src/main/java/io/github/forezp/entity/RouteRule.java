package io.github.forezp.entity;

import java.util.List;

/**
 * 路由规则：
 * swr.route.enable: true
 * swr.route.services:
 *  - swr_service_name：provider
 *    swr_app_version: 1.0,1.1
 *    swr_instance_id:
 *    swr_group_id:
 *    swr_local_ip:
 *    zone:
 *    region:
 */

public class RouteRule {
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
