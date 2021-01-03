package io.github.forezp.client.dto;

public class InstanceDetailDto {

    /**
     * {
     * "metadata": {},
     * "instanceId": "10.10.10.10-8888-DEFAULT-nacos.test.2",
     * "port": 8888,
     * "service": "nacos.test.2",
     * "healthy": false,
     * "ip": "10.10.10.10",
     * "clusterName": "DEFAULT",
     * "weight": 1.0
     * }
     */

    private String metadata;
    private String instanceId;
    private Integer port;
    private String service;
    private Boolean healthy;
    private String ip;
    private String clusterName;
    private String weight;

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Boolean getHealthy() {
        return healthy;
    }

    public void setHealthy(Boolean healthy) {
        this.healthy = healthy;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
