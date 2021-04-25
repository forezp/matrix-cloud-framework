package io.github.forezp.entity;

import java.io.Serializable;

/**
 * Created by forezp on 2019/5/21.
 * 路由规则
 *
 * @see io.github.forezp.constant.MetadataConstants
 */
public class RouteEntity implements Serializable {

    private String swr_service_name;//服务名
    private String swr_app_version;//多个版本用逗号隔开。
    private String swr_instance_id;
    private String swr_group_id;
    private String swr_local_ip;
    private String region;
    private String zone;

    public String getSwr_app_version() {
        return swr_app_version;
    }

    public void setSwr_app_version(String swr_app_version) {
        this.swr_app_version = swr_app_version;
    }

    public String getSwr_instance_id() {
        return swr_instance_id;
    }

    public void setSwr_instance_id(String swr_instance_id) {
        this.swr_instance_id = swr_instance_id;
    }

    public String getSwr_group_id() {
        return swr_group_id;
    }

    public void setSwr_group_id(String swr_group_id) {
        this.swr_group_id = swr_group_id;
    }


    public String getSwr_local_ip() {
        return swr_local_ip;
    }

    public void setSwr_local_ip(String swr_local_ip) {
        this.swr_local_ip = swr_local_ip;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getSwr_service_name() {
        return swr_service_name;
    }

    public void setSwr_service_name(String swr_service_name) {
        this.swr_service_name = swr_service_name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
