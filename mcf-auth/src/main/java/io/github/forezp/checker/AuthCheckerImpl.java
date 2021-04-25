package io.github.forezp.checker;

import io.github.forezp.context.SwrContextHolder;
import io.github.forezp.entity.AuthCheckResult;
import io.github.forezp.entity.AuthInstance;
import io.github.forezp.entity.AuthRule;
import io.github.forezp.entity.Metadata;
import io.github.forezp.loader.AuthRuleLoader;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by forezp on 2019/5/2.
 */
public class AuthCheckerImpl implements AuthChecker {



    public AuthCheckResult check(HttpServletRequest httpServletRequest,  AuthRule authRule) {
        AuthCheckResult authCheckResult = new AuthCheckResult();
        authCheckResult.setResultType(AuthCheckResult.ResultType.REFUSE);
        Metadata upperMeta = SwrContextHolder.get().getUpperMetadata();
        if (upperMeta != null) {
            //白名单校验
            List<AuthInstance> whites = authRule.getWhites();
            List<AuthInstance> blacks = authRule.getBlacks();
            if ((whites == null || whites.size() == 0) && (blacks == null || blacks.size() == 0)) {
                authCheckResult.setResultType(AuthCheckResult.ResultType.ACESS);
                return authCheckResult;
            }
            if (whites != null) {
                boolean isContain = false;
                for (AuthInstance s : whites) {
                    if (s.getSwr_service_name().equals(upperMeta.getServiceName())) {
                        isContain = true;
                        break;
                    }
                }
                if (isContain) {
                    authCheckResult.setResultType(AuthCheckResult.ResultType.ACESS);
                } else {
                    authCheckResult.setResultType(AuthCheckResult.ResultType.REFUSE);
                }
                return authCheckResult;
            }
            if (blacks != null) {
                boolean isContain = false;
                for (AuthInstance s : blacks) {
                    if (s.getSwr_service_name().equals(upperMeta.getServiceName())) {
                        isContain = true;
                        break;
                    }
                }
                if (isContain) {
                    authCheckResult.setResultType(AuthCheckResult.ResultType.REFUSE);
                } else {
                    authCheckResult.setResultType(AuthCheckResult.ResultType.ACESS);
                }
            }
        }
        return authCheckResult;
    }
}


