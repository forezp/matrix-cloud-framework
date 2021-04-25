package io.github.forezp.discovery;

import com.netflix.loadbalancer.Server;

import org.springframework.cloud.consul.discovery.ConsulServer;

import java.util.Map;

/**
 * Created by forezp on 2019/6/16.
 */
public class ConsulPluginAdapter extends AbstractPluginAdapter {


    @Override
    public Map<String, String> getMetaData(Server server) {
        if(server instanceof ConsulServer){
            ConsulServer consulServer= (ConsulServer) server;
            return consulServer.getMetadata();
        }
        return null;
    }
}
