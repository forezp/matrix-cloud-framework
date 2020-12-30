package io.github.forezp.client.impl;

import com.alibaba.fastjson.JSON;
import io.github.forezp.ApacheSyncClientExecutor;
import io.github.forezp.ResonseCallBack;
import io.github.forezp.client.NacosClient;
import io.github.forezp.client.dto.NameSpaceDto;
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
    ApacheSyncClientExecutor httpClientExecutor;

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
        return null;
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
        return null;
    }

}