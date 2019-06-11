package io.github.forezp.filter;

import io.github.forezp.checker.AuthChecker;
import io.github.forezp.entity.AuthCheckResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by forezp on 2019/5/2.
 */
public class AuthFilter extends OncePerRequestFilter {
    Logger logger= LoggerFactory.getLogger(AuthFilter.class);
    private AuthChecker authChecker;
    public AuthFilter(AuthChecker authChecker){
        this.authChecker=authChecker;
    }
    private static List<String> whiteUrls=new ArrayList<>();
    static{
        whiteUrls.add("/actuator/health");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String url=httpServletRequest.getRequestURI();
        if(whiteUrls.contains(url)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        AuthCheckResult result=authChecker.check(httpServletRequest);
        logger.info("url:{},authCheck result: {}",httpServletRequest.getRequestURI(),result.toString());
        if(result.getResultType().equals(AuthCheckResult.ResultType.ACESS)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }else {
            httpServletResponse.setStatus(403);
        }

    }
}
