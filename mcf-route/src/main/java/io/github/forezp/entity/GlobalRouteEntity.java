package io.github.forezp.entity;

/**
 * 仅在网关服务处下发
 */
public class GlobalRouteEntity {

    private String g_version;//{"consumer":"1.1;1.2","provider":"1.1;1.2"}
    private String g_version_weight;//{"consumer":"1.1=85;1.2=15","provider":"1.1=50;1.2=50"}

    public String getG_version() {
        return g_version;
    }

    public void setG_version(String g_version) {
        this.g_version = g_version;
    }

    public String getG_version_weight() {
        return g_version_weight;
    }

    public void setG_version_weight(String g_version_weight) {
        this.g_version_weight = g_version_weight;
    }
}
