package io.github.forezp.client;

import io.github.forezp.client.dto.NameSpaceDto;

public interface NacosClient {

    /**
     * 创建集群
     *
     * @param customNamespaceId
     * @param namespaceName
     * @param namespaceDesc
     * @return
     */
    String createNamespace(String customNamespaceId, String namespaceName, String namespaceDesc);

    /**
     * 获取命名空间
     *
     * @return
     */
    NameSpaceDto getNamespaces();

    /**
     * 修改命名空间
     *
     * @return
     */
    String putNamespaces(String customNamespaceId, String namespaceName, String namespaceDesc);


    /**
     * 删除命名空间
     * @param namespaceId
     * @return
     */
    String deleteNamespaces(String namespaceId);


    /**
     * 获取配置
     * @param tenant
     * @param dataId
     * @param group
     * @return
     */
    String getConfigs(String tenant, String dataId, String group);


    /**
     * 发布配置
     * @param tenant
     * @param dataId
     * @param group
     * @param content
     * @param type
     * @return
     */
   String publishConfig(String tenant,String dataId,String group,String content,String type);


    /**
     * 删除配置
     * @param tenant
     * @param dataId
     * @param group
     * @return
     */
   String deleteConfigs(String tenant,String dataId,String group);




}
