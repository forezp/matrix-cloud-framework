package io.github.forezp.config;

import io.github.forezp.checker.AuthChecker;
import io.github.forezp.checker.AuthCheckerImpl;
import io.github.forezp.config.condition.AuthCondition;
import io.github.forezp.filter.AuthFilter;
import io.github.forezp.loader.AuthRuleLoader;
import io.github.forezp.loader.LocalAuthRuleLoader;
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
    @ConditionalOnMissingBean
    public AuthRuleLoader authRuleLoader(){
      return new LocalAuthRuleLoader();
    }

    @Bean
    public AuthChecker authChecker(AuthRuleLoader authRuleLoader){
        return new AuthCheckerImpl(authRuleLoader);
    }



    @Bean
    public AuthFilter authFilter(AuthChecker authChecker){
        AuthFilter authFilter=new AuthFilter(authChecker);
        return authFilter;
    }

    @Bean

    public FilterRegistrationBean authFilterRegistrationBean(AuthFilter authChecker){
        FilterRegistrationBean filterRegistrationBean=new  FilterRegistrationBean(authChecker);
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;
    }


}
