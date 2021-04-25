package io.github.forezp.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.Serializable;

import static io.github.forezp.constant.MetadataConstants.*;

/**
 * Created by forezp on 2019/5/2.
 */
@Configuration
public class Metadata implements Serializable{


    /**
     * 集群Id
     */
    private String clusterId = "";
    /**
     * 命名空间Id
     */
    private String namespaceId = "";
    /**
     * 应用id
     */
    private String appId = "";

    /**
     * 应用版本
     */
    private String appVersion = "";

    /**
     * 服务名spring.application.name
     */
    private String serviceName = "";

    /**
     * 实例id
     */
    private String instanceId = "";
    /**
     * 部署组id
     */
    private String groupId = "";


    /**
     * 实例ip
     */
    private String localIp = "";

    /**
     * token
     */
    private String token = "";

    /**
     * zone
     */
    private String zone;

    /**
     * 和appVersion一样
     */
    private String version="";

    /**
     * 和groupId一样
     */
    private String group="";



    @Value("${"+SWR_CLUSTER_ID+":}")
    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }
    @Value("${"+SWR_NAMESPACE_ID+":}")
    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }
    @Value("${"+SWR_APP_ID+":}")
    public void setAppId(String appId) {
        this.appId = appId;
    }
    @Value("${"+SWR_APP_VERSION+":}")
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
    @Value("${"+SWR_SERVICE_NAME+":${"+SPRING_APPLICATION_NAME+":}}")
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    @Value("${"+SWR_INSTANCE_ID+":}")
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
    @Value("${"+SWR_GROUP_ID+":}")
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    @Value("${"+SWR_LOCAL_IP+":}")
    public void setLocalIp(String localIp) {
        if (StringUtils.isEmpty(localIp)) {
            InetUtils inetUtils = new InetUtils(new InetUtilsProperties());
            localIp = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
        }
        this.localIp = localIp;
    }
    @Value("${"+TOKEN+":}")
    public void setToken(String token) {
        this.token = token;
    }
    @Value("${"+ZONE+":}")
    public void setZone(String zone) {
        this.zone = zone;
    }
    @Value("${"+VERSION+":}")
    public void setVersion(String version) {
        this.version = version;
    }
    @Value("${"+GROUP+":}")
    public void setGroup(String group) {
        this.group = group;
    }

    public String getClusterId() {
        return clusterId;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getLocalIp() {
        return localIp;
    }

    public String getToken() {
        return token;
    }

    public String getZone() {
        return zone;
    }

    public String getVersion() {
        return version;
    }

    public String getGroup() {
        return group;
    }
}
