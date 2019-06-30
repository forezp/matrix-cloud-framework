package io.github.forezp.filter;


import io.github.forezp.collector.LimitDataCollector;
import io.github.forezp.entity.*;
import io.github.forezp.exception.LimitException;
import io.github.forezp.limit.LimitExcutor;
import io.github.forezp.config.RatelimitProperties;
import io.github.forezp.rule.LimitEntityBuilder;
import io.github.forezp.util.HttpUtils;
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
    RatelimitProperties ratelimitProperties;

    @Autowired
    LimitEntityBuilder limitEntityBuilder;

    @Autowired
    LimitExcutor limitExcutor;

    @Autowired
    LimitDataCollector limitDataCollector;

    private static List<String> whiteUrls=new ArrayList<>();
    static{
        whiteUrls.add("/actuator/health");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.info(httpServletRequest.getRequestURI());
        if(whiteUrls.contains(httpServletRequest.getRequestURI())){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if (!ratelimitProperties.getEnable()) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        ComposeLimitEntity composeLimitEntity = limitEntityBuilder.build();
        if (composeLimitEntity == null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        LimitEntity globalLimit = composeLimitEntity.getGlobalLimit();
        if(globalLimit!=null){
            tryAcess(globalLimit);
        }
        List<LimitEntity> serviceLimits=composeLimitEntity.getSeviceLimit();
        if(!CollectionUtils.isEmpty(serviceLimits)){
            LimitEntity serviceLimit=findCadidateLimit(httpServletRequest,serviceLimits);
            if(serviceLimit!=null) {
                tryAcess(serviceLimit);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

  private void tryAcess(LimitEntity limitEntity)throws LimitException {
      if(limitEntity!=null){
          LimitResult limitResult = limitExcutor.tryAccess(limitEntity);
          limitDataCollector.collect(limitResult);
          if (limitResult.getResultType().equals(LimitResult.ResultType.FAIL)) {
              throw new LimitException("you fail adccess,cause api limit rate,try it later");
          }
      }
  }

   private LimitEntity findCadidateLimit(HttpServletRequest httpServletRequest, List<LimitEntity>  serviceLimits){
       McfMetaData metaData= HttpUtils.getMcfMetaData(httpServletRequest);
       logger.info("metaData:"+metaData.toString());
       if(metaData==null){
           return null;
       }
       SvcInstance svcInstance= metaData.getSvcSource();
       if(svcInstance==null){
           return null;
       }
      String name= svcInstance.getSvcName();
       if(StringUtils.isEmpty(name)){
           return null;
       }
       Iterator<LimitEntity> iterator=serviceLimits.iterator();
       LimitEntity candidateLimit=null;
       while (iterator.hasNext()){
           LimitEntity limitEntity=iterator.next();
           if(limitEntity.getName().equals(name)){
               candidateLimit=limitEntity;
               break;
           }
       }
       return candidateLimit;
   }
}
