package io.github.forezp.discovery;


import com.netflix.loadbalancer.Server;

import static io.github.forezp.constant.MetadataConstants.*;

/**
 * Created by forezp on 2019/6/16.
 */
public abstract class AbstractPluginAdapter implements PluginAdapter {


    public String getClusterId(Server server) {
        return getMetaData(server).get(SWR_CLUSTER_ID);
    }

    public String getNamespaceId(Server server) {
        return getMetaData(server).get(SWR_NAMESPACE_ID);
    }

    public String getAppId(Server server) {
        return getMetaData(server).get(SWR_APP_ID);
    }

    public String getAppVersion(Server server) {
        return getMetaData(server).get(SWR_APP_VERSION);
    }

    public String getServiceName(Server server) {
        return getMetaData(server).get(SWR_SERVICE_NAME);
    }

    public String getInstanceId(Server server) {
        return getMetaData(server).get(SWR_INSTANCE_ID);
    }

    public String getGroupId(Server server) {
        return getMetaData(server).get(SWR_GROUP_ID);
    }

    public String getLocalIp(Server server) {
        return getMetaData(server).get(SWR_LOCAL_IP);
    }

    public String getToken(Server server) {
        return getMetaData(server).get(TOKEN);
    }

    public String getRegion(Server server) {
        return getMetaData(server).get(REGION);
    }

    public String getZone(Server server) {
        return getMetaData(server).get(ZONE);
    }

    public String getVersion(Server server) {
        return getMetaData(server).get(VERSION);
    }

    public String getGroup(Server server) {
        return getMetaData(server).get(GROUP);
    }
}
