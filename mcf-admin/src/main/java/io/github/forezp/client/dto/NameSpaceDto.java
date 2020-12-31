package io.github.forezp.client.dto;



import java.util.List;

public class NameSpaceDto {

    //{"code":200,"message":null,"data":[{"namespace":"","namespaceShowName":"public","quota":200,"configCount":0,"type":0}]}


    private Integer code;
    private String Message;
    private List<Instance> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<Instance> getData() {
        return data;
    }

    public void setData(List<Instance> data) {
        this.data = data;
    }

    static class Instance{
        private String namespace;
        private String namespaceShowName;
        private Integer quota;
        private Integer configCount;
        private Integer type;

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public String getNamespaceShowName() {
            return namespaceShowName;
        }

        public void setNamespaceShowName(String namespaceShowName) {
            this.namespaceShowName = namespaceShowName;
        }

        public Integer getQuota() {
            return quota;
        }

        public void setQuota(Integer quota) {
            this.quota = quota;
        }

        public Integer getConfigCount() {
            return configCount;
        }

        public void setConfigCount(Integer configCount) {
            this.configCount = configCount;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }
    }
}
