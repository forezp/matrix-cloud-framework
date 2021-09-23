package io.github.forezp.filter;


import io.github.forezp.collector.LimitDataCollector;
import io.github.forezp.configure.Metadata;
import io.github.forezp.context.SwrContextHolder;
import io.github.forezp.entity.*;
import io.github.forezp.exception.LimitException;
import io.github.forezp.excutor.LimitExcutor;
import io.github.forezp.loader.LimitRuleLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by forezp on 2019/5/3.
 */
public class RateLimitFilter extends OncePerRequestFilter {


    @Autowired
    LimitRuleLoader limitRuleLoader;

    @Autowired
    LimitExcutor limitExcutor;

    @Autowired
    LimitDataCollector limitDataCollector;

    private static List<String> whiteUrls = new ArrayList<>();

    static {
        whiteUrls.add("/actuator/health");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (whiteUrls.contains(httpServletRequest.getRequestURI())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        LimitRule limitRule = limitRuleLoader.load();
        if (limitRule == null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if (limitRule.getEnable() != null && !limitRule.getEnable()) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        LimitEntity globalLimit = limitRule.getGlobal();
        if (globalLimit != null) {
            tryAcess(globalLimit);
        }
        List<LimitEntity> serviceLimits = limitRule.getServices();
        if (!CollectionUtils.isEmpty(serviceLimits)) {
            LimitEntity serviceLimit = findCadidateLimit(serviceLimits);
            if (serviceLimit != null) {
                tryAcess(serviceLimit);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void tryAcess(LimitEntity limitEntity) throws LimitException {
        if (limitEntity != null) {
            LimitResult limitResult = limitExcutor.tryAccess(limitEntity);
            limitDataCollector.collect(limitResult);
            if (limitResult.getResultType().equals(LimitResult.ResultType.FAIL)) {
                throw new LimitException("you fail adccess,cause api limit rate,try it later");
            }
        }
    }

    private LimitEntity findCadidateLimit(List<LimitEntity> serviceLimits) {
        Metadata upperMeta = SwrContextHolder.get().getUpperMetadata();
        logger.info("upperMeta:" + upperMeta.toString());
        if (upperMeta == null) {
            return null;
        }

        String upperMetaServiceName = upperMeta.getServiceName();
        if (StringUtils.isEmpty(upperMetaServiceName)) {
            return null;
        }
        Iterator<LimitEntity> iterator = serviceLimits.iterator();
        LimitEntity candidateLimit = null;
        while (iterator.hasNext()) {
            LimitEntity limitEntity = iterator.next();
            if (limitEntity.getName().equals(upperMetaServiceName)) {
                candidateLimit = limitEntity;
                break;
            }
        }
        return candidateLimit;
    }
}
