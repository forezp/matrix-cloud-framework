package io.github.forezp.client.dto;

import java.util.List;

public class InstanceDto {

    /**
     * {
     * 	"dom": "nacos.test.1",
     * 	"cacheMillis": 1000,
     * 	"useSpecifiedURL": false,
     * 	"hosts": [{
     * 		"valid": true,
     * 		"marked": false,
     * 		"instanceId": "10.10.10.10-8888-DEFAULT-nacos.test.1",
     * 		"port": 8888,
     * 		"ip": "10.10.10.10",
     * 		"weight": 1.0,
     * 		"metadata": {}
     *        }],
     * 	"checksum": "3bbcf6dd1175203a8afdade0e77a27cd1528787794594",
     * 	"lastRefTime": 1528787794594,
     * 	"env": "",
     * 	"clusters": ""
     * }
     */

    private String dom;
    private Integer cacheMillis;
    private Boolean useSpecifiedURL;
    private List<Host> hosts;
    private String checksum;
    private Long lastRefTime;
    private String env;
    private String clusters;

    static class  Host{
        private Boolean valid;
        private Boolean marked;
        private String instanceId;
        private Integer port;
        private String ip;
        private Float weight;
        private String metadata;

        public Boolean getValid() {
            return valid;
        }

        public void setValid(Boolean valid) {
            this.valid = valid;
        }

        public Boolean getMarked() {
            return marked;
        }

        public void setMarked(Boolean marked) {
            this.marked = marked;
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

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Float getWeight() {
            return weight;
        }

        public void setWeight(Float weight) {
            this.weight = weight;
        }

        public String getMetadata() {
            return metadata;
        }

        public void setMetadata(String metadata) {
            this.metadata = metadata;
        }
    }

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }

    public Integer getCacheMillis() {
        return cacheMillis;
    }

    public void setCacheMillis(Integer cacheMillis) {
        this.cacheMillis = cacheMillis;
    }

    public Boolean getUseSpecifiedURL() {
        return useSpecifiedURL;
    }

    public void setUseSpecifiedURL(Boolean useSpecifiedURL) {
        this.useSpecifiedURL = useSpecifiedURL;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public Long getLastRefTime() {
        return lastRefTime;
    }

    public void setLastRefTime(Long lastRefTime) {
        this.lastRefTime = lastRefTime;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getClusters() {
        return clusters;
    }

    public void setClusters(String clusters) {
        this.clusters = clusters;
    }
}
