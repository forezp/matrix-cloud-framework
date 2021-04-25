package io.github.forezp.configure;


import com.alibaba.cloud.nacos.NacosConfigManager;
import io.github.forezp.cache.LimitRuleCache;
import io.github.forezp.collector.ConsolLimitDataCollector;
import io.github.forezp.collector.LimitDataCollector;
import io.github.forezp.filter.RateLimitFilter;
import io.github.forezp.excutor.GuavaLimitExcutor;
import io.github.forezp.excutor.LimitExcutor;
import io.github.forezp.excutor.RedisLimitExcutor;
import io.github.forezp.listener.NacosLimitListener;
import io.github.forezp.loader.LimitRuleLoader;
import io.github.forezp.loader.LocalLimitRuleLoader;
import io.github.forezp.loader.NacosLimitRuleLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by forezp on 2019/5/4.
 */
@Configuration
@ConditionalOnProperty(name = "swr.limit.enable", havingValue = "true", matchIfMissing = false)
public class RatelimitConfigure {

    @Bean
    @ConditionalOnMissingBean
    public LimitExcutor guavaLimitExcutor() {
        LimitExcutor limitExcutor = new GuavaLimitExcutor();
        return limitExcutor;
    }

    @Bean
    public LimitRuleCache limitRuleCache() {
        return new LimitRuleCache();
    }

    @Bean
    @ConditionalOnClass(NacosConfigManager.class)
    public NacosLimitRuleLoader nacosLimitRuleLoader() {
        return new NacosLimitRuleLoader();
    }

    @Bean
    @ConditionalOnClass(NacosConfigManager.class)
    NacosLimitListener nacosLimitListener() {
        return new NacosLimitListener();
    }


//    @Bean
//    @ConditionalOnProperty(name = "swr.limit.type", havingValue = "redis")
//    public LimitExcutor limitRuleLoader(StringRedisTemplate stringRedisTemplate) {
//        LimitExcutor limitExcutor = new RedisLimitExcutor(stringRedisTemplate);
//        return limitExcutor;
//    }

    @Bean
    @ConditionalOnMissingBean
    public LimitRuleLoader localLimitRuleLoader() {
        LimitRuleLoader limitRuleLoader = new LocalLimitRuleLoader();
        return limitRuleLoader;
    }


    @Bean
    @ConditionalOnMissingBean
    public LimitDataCollector limitDataCollector() {
        LimitDataCollector limitDataCollector = new ConsolLimitDataCollector();
        return limitDataCollector;
    }


    @Bean
    public RateLimitFilter rateLimitFilter() {
        RateLimitFilter authFilter = new RateLimitFilter();
        return authFilter;
    }

    @Bean
    public FilterRegistrationBean rateLimitilterRegistrationBean(RateLimitFilter rateLimitFilter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(rateLimitFilter);
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 2);
        return filterRegistrationBean;
    }


}
