package io.github.forezp.entity;

import java.util.List;

/**
 *
 * Created by forezp on 2019/5/4.
 *
 * swr.limit.type: local or redis
 * swr.limit.enable: true
 * swr.limit.global.num: 100
 * swr.limit.global.second: 1
 * swr.limit.services:
 *    - name: mcf-provider
 *      num: 10
 *      second: 11
 *    - name: mcf-consumer
 *      num: 23
 *      second: 11
 */

public class LimitRule {

    private Boolean enable;
    private String type;
    private LimitEntity global;
    private List<LimitEntity> services;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public LimitEntity getGlobal() {
        return global;
    }

    public void setGlobal(LimitEntity global) {
        this.global = global;
    }

    public List<LimitEntity> getServices() {
        return services;
    }

    public void setServices(List<LimitEntity> services) {
        this.services = services;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
