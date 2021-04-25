package io.github.forezp.discovery;

import com.netflix.loadbalancer.Server;

import java.util.Map;

import static io.github.forezp.constant.MetadataConstants.*;

/**
 * Created by forezp on 2019/6/16.
 */
public interface PluginAdapter {

    Map<String, String> getMetaData(Server server);

    String getClusterId(Server server);

    String getNamespaceId(Server server);

    String getAppId(Server server);

    String getAppVersion(Server server);

    String getServiceName(Server server);

    String getInstanceId(Server server);

    String getGroupId(Server server);

    String getLocalIp(Server server);

    String getToken(Server server);

    String getRegion(Server server);

    String getZone(Server server);

    String getVersion(Server server);

    String getGroup(Server server);
}
