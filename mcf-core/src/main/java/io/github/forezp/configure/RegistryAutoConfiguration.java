package io.github.forezp.configure;

import io.github.forezp.context.SWrContextAware;

import io.github.forezp.filter.ContextFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

@Configuration
public class RegistryAutoConfiguration {
    @Bean
    public FilterRegistrationBean filterRegistrationBean(ContextFilter contextFilter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(contextFilter, new ServletRegistrationBean[0]);
        filterRegistrationBean.setDispatcherTypes(DispatcherType.ASYNC, new DispatcherType[]{DispatcherType.ERROR, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.REQUEST});
        filterRegistrationBean.setOrder(ContextFilter.ORDER);
        return filterRegistrationBean;
    }

    @Bean
    public ContextFilter contextFilter() {
        return new ContextFilter();
    }

    @Bean
    public SWrContextAware sWrContextAware(){
        return new SWrContextAware();
    }

//    @Bean(initMethod = "init", destroyMethod = "destroy")
//    @ConditionalOnMissingBean
//    public ClientDiscoveryImpl clientDiscoveryFactory(DiscoveryClient discoveryClient) {
//
//        ClientDiscoveryImpl clientDiscoveryFactory = new ClientDiscoveryImpl( discoveryClient );
//        return clientDiscoveryFactory;
//    }
}
