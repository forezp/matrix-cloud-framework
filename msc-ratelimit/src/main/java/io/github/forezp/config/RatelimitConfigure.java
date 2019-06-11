package io.github.forezp.config;


import io.github.forezp.collector.ConsolLimitDataCollector;
import io.github.forezp.collector.LimitDataCollector;
import io.github.forezp.filter.RateLimitFilter;
import io.github.forezp.limit.GuavaLimitExcutor;
import io.github.forezp.limit.LimitExcutor;
import io.github.forezp.rule.LimitEntityBuilder;
import io.github.forezp.rule.LimitEntityBuilderImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * Created by forezp on 2019/5/4.
 */
@Configuration
@ConditionalOnProperty(havingValue = "rate.limit.enable",matchIfMissing = false)
public class RatelimitConfigure {

    @Bean
    @ConditionalOnMissingBean
    public LimitExcutor limitExcutor(){
        LimitExcutor limitExcutor=new GuavaLimitExcutor();
        return limitExcutor;
    }

    @Bean
    @ConditionalOnMissingBean
    public LimitEntityBuilder limitEntityBuilder(){
        LimitEntityBuilder limitEntityBuilder=new LimitEntityBuilderImpl();
        return limitEntityBuilder;
    }

    @Bean
    @ConditionalOnMissingBean
    public LimitDataCollector limitDataCollector(){
        LimitDataCollector limitDataCollector=new ConsolLimitDataCollector();
        return limitDataCollector;
    }


    @Bean
    public RateLimitFilter rateLimitFilter(){
        RateLimitFilter authFilter=new RateLimitFilter();
        return authFilter;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(RateLimitFilter rateLimitFilter){
        FilterRegistrationBean filterRegistrationBean=new  FilterRegistrationBean(rateLimitFilter);
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE+1);
        return filterRegistrationBean;
    }

}
