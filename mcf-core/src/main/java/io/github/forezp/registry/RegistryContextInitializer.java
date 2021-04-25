package io.github.forezp.registry;

import cn.hutool.core.util.StrUtil;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import io.github.forezp.constant.MetadataConstants;
import io.github.forezp.context.AbstractApplicationContextInitializer;
import io.github.forezp.context.SWrContextAware;
import io.github.forezp.entity.Metadata;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;


public class RegistryContextInitializer extends AbstractApplicationContextInitializer {


    @Override
    protected Object afterInitialization(ConfigurableApplicationContext applicationContext, Object bean, String beanName) throws BeansException {

        if (bean instanceof NacosDiscoveryProperties) {
            ConfigurableEnvironment environment = applicationContext.getEnvironment();

            NacosDiscoveryProperties nacosDiscoveryProperties = (NacosDiscoveryProperties) bean;

            Map<String, String> metadata = nacosDiscoveryProperties.getMetadata();

            String appName = SWrContextAware.getAppName(environment);
            metadata.put(MetadataConstants.SWR_CLUSTER_ID, SWrContextAware.getClusterId(environment));
            metadata.put(MetadataConstants.SWR_NAMESPACE_ID, SWrContextAware.getNamespaceId(environment));
            metadata.put(MetadataConstants.SWR_APP_ID, SWrContextAware.getAppId(environment));
            metadata.put(MetadataConstants.SWR_APP_VERSION, SWrContextAware.getAppVersion(environment));
            metadata.put(MetadataConstants.SWR_SERVICE_NAME, SWrContextAware.getServiceName(environment));
            metadata.put(MetadataConstants.SWR_INSTANCE_ID, SWrContextAware.getInstanceId(environment));
            metadata.put(MetadataConstants.SWR_GROUP_ID, SWrContextAware.getGroupId(environment));
            metadata.put(MetadataConstants.SWR_LOCAL_IP, SWrContextAware.getLocalIp(environment));
            metadata.put(MetadataConstants.TOKEN, SWrContextAware.getToken(environment));
            metadata.put(MetadataConstants.REGION, SWrContextAware.getRegion(environment));
            if (StrUtil.isEmpty(metadata.get(MetadataConstants.ZONE))) {
                metadata.put(MetadataConstants.ZONE, SWrContextAware.getZone(environment));
            }
            if (StrUtil.isEmpty(metadata.get(MetadataConstants.VERSION))) {
                metadata.put(MetadataConstants.VERSION, SWrContextAware.getVersion(environment));
            }
            if (StrUtil.isEmpty(metadata.get(MetadataConstants.GROUP))) {
                metadata.put(MetadataConstants.GROUP, SWrContextAware.getGroup(environment));
            }
            return bean;
        } else {
            return bean;
        }
    }
}