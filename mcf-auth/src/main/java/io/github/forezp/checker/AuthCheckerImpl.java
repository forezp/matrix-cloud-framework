package io.github.forezp.checker;

import com.alibaba.fastjson.JSON;
import io.github.forezp.entity.AuthCheckResult;
import io.github.forezp.entity.AuthRule;
import io.github.forezp.entity.McfMetaData;
import io.github.forezp.entity.SvcInstance;
import io.github.forezp.loader.AuthRuleLoader;
import io.github.forezp.util.HttpUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static io.github.forezp.constant.McfConstant.MCF_META_HEADER;

/**
 * Created by forezp on 2019/5/2.
 */
public class AuthCheckerImpl implements AuthChecker {

    private AuthRuleLoader authRuleLoader;
    public AuthCheckerImpl(AuthRuleLoader authRuleLoader){
        this.authRuleLoader=authRuleLoader;
    }

    public AuthCheckResult check(HttpServletRequest httpServletRequest) {
        AuthCheckResult authCheckResult=new AuthCheckResult();
        authCheckResult.setResultType(AuthCheckResult.ResultType.REFUSE);
        SvcInstance svcInstance=getSourceSvc(httpServletRequest);
        if(svcInstance!=null){
            //白名单校验
            AuthRule authRule= authRuleLoader.load();
            String serviceName=svcInstance.getSvcName();
            Map<String, List<SvcInstance>> whiteMap=authRule.getWhiteMap();
            Map<String, List<SvcInstance>> blackMap=authRule.getBlackMap();
            if(whiteMap==null&&blackMap==null){
                authCheckResult.setResultType(AuthCheckResult.ResultType.ACESS);
                return authCheckResult;
            }
            if(whiteMap!=null) {
                if (whiteMap.containsKey(serviceName)) {
                    List<SvcInstance> list = whiteMap.get(serviceName);
                    if (list == null) {
                        authCheckResult.setResultType(AuthCheckResult.ResultType.ACESS);
                    } else {
                        boolean isContain = false;
                        for (SvcInstance s : list) {
                            if (s.getSvcInstanceId().equals(svcInstance.getSvcInstanceId())) {
                                isContain = true;
                                break;
                            }
                        }
                        if (isContain) {
                            authCheckResult.setResultType(AuthCheckResult.ResultType.ACESS);
                        }
                    }
                }
                return authCheckResult;
            }
            if(blackMap!=null){
                if(!blackMap.containsKey(serviceName)){
                    authCheckResult.setResultType(AuthCheckResult.ResultType.ACESS);
                }else {
                    List<SvcInstance> list = blackMap.get(serviceName);
                    if(list==null){
                        authCheckResult.setResultType(AuthCheckResult.ResultType.REFUSE);
                    }else {
                        boolean isContain = false;
                        for (SvcInstance s : list) {
                            if (s.getSvcInstanceId().equals(svcInstance.getSvcInstanceId())) {
                                isContain = true;
                                break;
                            }
                        }
                        if (isContain) {
                            authCheckResult.setResultType(AuthCheckResult.ResultType.REFUSE);
                        }
                    }
                }
            }
        }
        return authCheckResult;
    }

    private SvcInstance getSourceSvc(HttpServletRequest httpServletRequest){

       String mcfHeader= HttpUtils.getHeaders(httpServletRequest).get(MCF_META_HEADER);
       if(!StringUtils.isEmpty(mcfHeader)){
           McfMetaData metaData= JSON.parseObject(mcfHeader,McfMetaData.class);
           if(metaData!=null){
               return metaData.getSvcSource();
           }
       }
       return null;
    }
}
