package io.github.forezp.config;

import com.netflix.appinfo.EurekaInstanceConfig;
import io.github.forezp.discovery.ClientDiscoveryImpl;
import io.github.forezp.cloud.McfMetaConsulRegiestCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistrationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import static io.github.forezp.constant.McfConstant.MCF_REGIEST_DATA;

/**
 * Created by forezp on 2019/5/3.
 */
@Configuration
public class McfCoreConfig {


    @ConditionalOnClass(ConsulRegistration.class)
    @Configuration
    public static class McfConsulConfig{

        @Bean
        public ConsulRegistrationCustomizer consulRegiestCustomizer(){
            return new McfMetaConsulRegiestCustomizer();
        }
    }

    @Configuration
    @EnableConfigurationProperties
    @ConditionalOnBean(EurekaInstanceConfig.class)
    public static class MeteEurekaConfig {

        @Autowired
        private EurekaInstanceConfig instance;

        @PostConstruct
        public void init() {
            this.instance.getMetadataMap().put(MCF_REGIEST_DATA, MCF_REGIEST_DATA);
        }
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @ConditionalOnMissingBean
    public ClientDiscoveryImpl clientDiscoveryFactory(DiscoveryClient discoveryClient) {

        ClientDiscoveryImpl clientDiscoveryFactory = new ClientDiscoveryImpl( discoveryClient );
        return clientDiscoveryFactory;
    }
}
