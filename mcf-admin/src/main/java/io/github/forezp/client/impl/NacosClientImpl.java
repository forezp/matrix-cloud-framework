package io.github.forezp.client.impl;

import com.alibaba.fastjson.JSON;
import io.github.forezp.HttpClientExecutor;

import io.github.forezp.ResonseCallBack;
import io.github.forezp.client.NacosClient;
import io.github.forezp.client.dto.InstanceDetailDto;
import io.github.forezp.client.dto.InstanceDto;
import io.github.forezp.client.dto.NameSpaceDto;
import io.github.forezp.client.dto.SvcDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NacosClientImpl implements NacosClient {

    Logger logger = LoggerFactory.getLogger(NacosClientImpl.class);
    @Value("${nacos.api.url:http://127.0.0.1:8848}")
    private String NacosApiUrl;

    @Autowired
    HttpClientExecutor httpClientExecutor;

    public String createNamespace(String customNamespaceId, String namespaceName, String namespaceDesc) {
        String url = NacosApiUrl + "/nacos/v1/console/namespaces";
        Map<String, Object> paras = new HashMap<>();
        paras.put("customNamespaceId", customNamespaceId);
        paras.put("namespaceName", namespaceName);
        paras.put("namespaceDesc", namespaceDesc);
        ResonseCallBack.DEAULT deault = new ResonseCallBack.DEAULT();
        httpClientExecutor.postForm(url, paras, deault);
        logger.info(deault.getData());
        return deault.getData();
    }

    @Override
    public NameSpaceDto getNamespaces() {
        String url = NacosApiUrl + "/nacos/v1/console/namespaces";
        ResonseCallBack.DEAULT deault = new ResonseCallBack.DEAULT();
        httpClientExecutor.get(url, deault);
        if (deault != null && StringUtils.isNotEmpty(deault.getData())) {
            NameSpaceDto nameSpaceDto = JSON.parseObject(deault.getData(), NameSpaceDto.class);
            return nameSpaceDto;
        }
        return null;
    }

    @Override
    public String putNamespaces(String customNamespaceId, String namespaceName, String namespaceDesc) {
        String url = NacosApiUrl + "/nacos/v1/console/namespaces";
        Map<String, Object> paras = new HashMap<>();
        paras.put("customNamespaceId", customNamespaceId);
        paras.put("namespaceName", namespaceName);
        paras.put("namespaceDesc", namespaceDesc);
        ResonseCallBack.DEAULT deault = new ResonseCallBack.DEAULT();
        httpClientExecutor.putForm(url, paras, deault);
        logger.info(deault.getData());
        return deault.getData();
    }

    @Override
    public String deleteNamespaces(String namespaceId) {
        String url = NacosApiUrl + "/nacos/v1/console/namespaces";
        Map<String, Object> paras = new HashMap<>();
        paras.put("namespaceId", namespaceId);
        ResonseCallBack.DEAULT deault = new ResonseCallBack.DEAULT();
        httpClientExecutor.deleteForm(url, paras, deault);
        logger.info(deault.getData());
        return deault.getData();
    }

    @Override
    public String getConfigs(String tenant, String dataId, String group) {
        String url = NacosApiUrl + "/nacos/v1/cs/configs";
        Map<String, Object> paras = new HashMap<>();
        paras.put("tenant", tenant);
        paras.put("dataId", dataId);
        paras.put("group", group);
        ResonseCallBack.DEAULT deault = new ResonseCallBack.DEAULT();
        httpClientExecutor.get(url, paras, deault);
        logger.info(deault.getData());
        return deault.getData();
    }

    @Override
    public String publishConfig(String tenant, String dataId, String group, String content, String type) {
        String url = NacosApiUrl + "/nacos/v1/cs/configs";
        Map<String, Object> paras = new HashMap<>();
        paras.put("tenant", tenant);
        paras.put("dataId", dataId);
        paras.put("group", group);
        paras.put("content", content);
        paras.put("type", type);
        ResonseCallBack.DEAULT deault = new ResonseCallBack.DEAULT();
        httpClientExecutor.putForm(url, paras, deault);
        logger.info(deault.getData());

        return deault.getData();
    }

    @Override
    public String deleteConfigs(String tenant, String dataId, String group) {
        String url = NacosApiUrl + "/nacos/v1/cs/configs";

        Map<String, Object> paras = new HashMap<>();
        paras.put("tenant", tenant);
        paras.put("dataId", dataId);
        paras.put("group", group);
        ResonseCallBack.DEAULT deault = new ResonseCallBack.DEAULT();
        httpClientExecutor.deleteForm(url, paras, deault);
        logger.info(deault.getData());
        return deault.getData();
    }

    @Override
    public SvcDto getSvcList(Integer pageNo, Integer pageSize, String groupName, String namespaceId) {

        String url = NacosApiUrl + "/nacos/v1/ns/service/list";
        Map<String, Object> paras = new HashMap<>();
        paras.put("pageNo", pageNo);
        paras.put("pageSize", pageSize);
        paras.put("groupName", groupName);
        paras.put("namespaceId", namespaceId);
        ResonseCallBack.DEAULT deault = new ResonseCallBack.DEAULT();
        httpClientExecutor.get(url, paras, deault);
        logger.info(deault.getData());
        SvcDto svcDto = JSON.parseObject(deault.getData(), SvcDto.class);
        return svcDto;
    }

    @Override
    public InstanceDto getInstances(String serviceName, String groupName, String namespaceId, String clusters, Boolean healthyOnly) {
        String url = NacosApiUrl + "/nacos/v1/ns/instance/list";
        Map<String, Object> paras = new HashMap<>();
        paras.put("serviceName", serviceName);
        paras.put("groupName", groupName);
        paras.put("namespaceId", namespaceId);
        paras.put("clusters", clusters);
        paras.put("healthyOnly", healthyOnly);
        ResonseCallBack.DEAULT deault = new ResonseCallBack.DEAULT();
        httpClientExecutor.get(url, paras, deault);
        logger.info(deault.getData());
        InstanceDto instanceDto = JSON.parseObject(deault.getData(), InstanceDto.class);
        return instanceDto;

    }

    @Override
    public InstanceDetailDto getInstanceDetail(String serviceName, String groupName, String ip, Integer port, String namespaceId,
                                               String cluster, Boolean healthyOnly, Boolean ephemeral) {

        String url = NacosApiUrl + "/nacos/v1/ns/instance";
        Map<String, Object> paras = new HashMap<>();
        paras.put("serviceName", serviceName);
        paras.put("groupName", groupName);
        paras.put("ip", ip);
        paras.put("port", port);
        paras.put("namespaceId", namespaceId);
        paras.put("cluster", cluster);
        paras.put("healthyOnly", healthyOnly);
        paras.put("ephemeral", ephemeral);
        ResonseCallBack.DEAULT deault = new ResonseCallBack.DEAULT();
        httpClientExecutor.get(url, paras, deault);
        logger.info(deault.getData());
        InstanceDetailDto instanceDetailDto = JSON.parseObject(deault.getData(), InstanceDetailDto.class);
        return instanceDetailDto;
    }


}