package io.github.forezp.adapter;

import com.netflix.loadbalancer.Server;

import java.util.Map;

/**
 * Created by forezp on 2019/6/16.
 */
public interface PluginAdapter {

    Map<String ,String> getMetaData(Server server);
}
