package io.github.forezp.entity;

import java.io.Serializable;

/**
 * Created by forezp on 2019/5/21.
 */
public class RouteRule implements Serializable{

    private static final long serialVersionUID = 1356648445119125021L;

    private String name;

    private String host;

    private String version;

    private String region;

    private String group;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
