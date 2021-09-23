package io.github.forezp.entity;

import java.util.List;

/**
 * 路由规则：
 * swr.route.enable: true
 * swr.route.services:
 * - swr_service_name：provider
 * swr_app_version: 1.0,1.1
 * swr_instance_id:
 * swr_group_id:
 * swr_local_ip:
 * zone:
 * region:
 */

public class RouteRule {
    //是否开启，默认开启，仅对服务路由生效，对全局灰度配置。
    private Boolean enable = true;

    //单个服务的配置
    private List<RouteEntity> services;

    //全局灰度配置，1.从配置中心获取，仅限于gateway服务 2.
    private GlobalRouteEntity global;

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

    public GlobalRouteEntity getGlobal() {
        return global;
    }

    public void setGlobal(GlobalRouteEntity global) {
        this.global = global;
    }
}
