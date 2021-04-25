package io.github.forezp.entity;

import java.io.Serializable;

/**
 * 规则：黑名单或者白名单，互斥
 * Created by forezp on 2019/5/2.
 */
public class AuthInstance implements Serializable{

    private static final long serialVersionUID = 3346648245119125021L;

    private String swr_service_name;

    private String swr_app_version;


    public String getSwr_service_name() {
        return swr_service_name;
    }

    public void setSwr_service_name(String swr_service_name) {
        this.swr_service_name = swr_service_name;
    }

    public String getSwr_app_version() {
        return swr_app_version;
    }

    public void setSwr_app_version(String swr_app_version) {
        this.swr_app_version = swr_app_version;
    }
}
