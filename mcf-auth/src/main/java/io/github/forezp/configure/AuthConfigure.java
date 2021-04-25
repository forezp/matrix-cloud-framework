package io.github.forezp.configure;

import com.alibaba.cloud.nacos.NacosConfigManager;
import io.github.forezp.cache.AuthRuleCache;
import io.github.forezp.checker.AuthChecker;
import io.github.forezp.checker.AuthCheckerImpl;
import io.github.forezp.configure.condition.AuthCondition;
import io.github.forezp.filter.AuthFilter;
import io.github.forezp.listener.NacosAuthListener;
import io.github.forezp.loader.AuthRuleLoader;
import io.github.forezp.loader.LocalAuthRuleLoader;
import io.github.forezp.loader.NacosAuthRuleLoader;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * Created by forezp on 2019/5/3.
 */

@Configuration
@Conditional(AuthCondition.class)
public class AuthConfigure {

    @Bean
    public AuthRuleCache authRuleCache() {
        return new AuthRuleCache();
    }

    @Bean
    @ConditionalOnClass(NacosConfigManager.class)
    public NacosAuthRuleLoader nacosAuthRuleLoader() {
        return new NacosAuthRuleLoader();
    }

    @Bean
    @ConditionalOnClass(NacosConfigManager.class)
    NacosAuthListener nacosAuthListener() {
        return new NacosAuthListener();
    }


    @Bean
    @ConditionalOnMissingBean
    public AuthRuleLoader authRuleLoader() {
        return new LocalAuthRuleLoader();
    }


    @Bean
    public AuthChecker authChecker() {
        return new AuthCheckerImpl();
    }


    @Bean
    public AuthFilter authFilter(AuthChecker authChecker, AuthRuleLoader authRuleLoader) {
        AuthFilter authFilter = new AuthFilter(authChecker, authRuleLoader);
        return authFilter;
    }

    @Bean
    public FilterRegistrationBean authFilterRegistrationBean(AuthFilter authChecker) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(authChecker);
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        return filterRegistrationBean;
    }


}
