package io.github.forezp.discovery;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.loadbalancer.Server;

import java.util.Map;

public class NacosPluginAdapter extends AbstractPluginAdapter {
    @Override
    public Map<String, String> getMetaData(Server server) {
        if (server instanceof NacosServer) {
            NacosServer nacosServer = (NacosServer) server;
            return nacosServer.getMetadata();
        }
        return null;
    }
}
