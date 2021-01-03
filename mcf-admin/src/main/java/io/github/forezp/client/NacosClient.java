package io.github.forezp.client;

import io.github.forezp.client.dto.InstanceDetailDto;
import io.github.forezp.client.dto.InstanceDto;
import io.github.forezp.client.dto.NameSpaceDto;
import io.github.forezp.client.dto.SvcDto;

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

    /**
     *
     * @param pageNo
     * @param pageSize
     * @param groupName
     * @param namespaceId
     * @return
     */
   SvcDto getSvcList(Integer pageNo , Integer pageSize, String groupName, String namespaceId);


    /**
     * 获取服务的实例列表
     * @param serviceName
     * @param groupName
     * @param namespaceId
     * @param clusters
     * @param healthyOnly
     * @return
     */
   InstanceDto getInstances(String serviceName,String groupName,String namespaceId,
                            String clusters,Boolean healthyOnly);

    /**
     * 获取服务实例详情
     * @param serviceName
     * @param groupName
     * @param ip
     * @param port
     * @param namespaceId
     * @param cluster
     * @param healthyOnly
     * @param ephemeral
     * @return
     */
   InstanceDetailDto getInstanceDetail (String serviceName,String groupName,String ip,Integer port,
                                        String namespaceId,String cluster,Boolean healthyOnly,Boolean ephemeral);
}
