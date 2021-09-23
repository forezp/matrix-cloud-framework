package io.github.forezp.configure;

import io.github.forezp.context.SWrContextAware;

import io.github.forezp.filter.ContextFilter;

import io.github.forezp.filter.WebClientInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.SpringServletContainerInitializer;

import reactor.core.publisher.Mono;

import javax.servlet.DispatcherType;

@Configuration
public class RegistryAutoConfiguration {

    @Bean
    public SWrContextAware sWrContextAware() {
        return new SWrContextAware();
    }

    @ConditionalOnClass(SpringServletContainerInitializer.class)
    protected static class WebFilterConfiguration {
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

    }

    @ConditionalOnClass(Mono.class)
    protected static class WebFluxFilterConfiguration {

        @Bean
        public WebClientInterceptor webClientInterceptor() {
            return new WebClientInterceptor();
        }
    }


}
