package io.github.forezp.loader;

import io.github.forezp.entity.AuthRule;
import io.github.forezp.entity.SvcInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.forezp.constant.McfConfigConstant.AUTH_SERVICE_BLACK;
import static io.github.forezp.constant.McfConfigConstant.AUTH_SERVICE_WHITE;

/**
 * 黑名单  白名单 互斥，如果都配了，默认只生效白名单
 * auth.service.black: mcf-consumer[1;2;3],mcf-consumer2
 * Created by forezp on 2019/5/2.
 */

public class LocalAuthRuleLoader implements AuthRuleLoader {

    @Value("${" + AUTH_SERVICE_WHITE + ":}")
    String ruleWhite;

    @Value("${" + AUTH_SERVICE_BLACK + ":}")
    String ruleBlack;


    public AuthRule load() {
        if (StringUtils.isEmpty(ruleWhite) && StringUtils.isEmpty(ruleBlack)) {
            return null;
        }
        if (!StringUtils.isEmpty(ruleWhite)) {

            return buildAuthRule(ruleWhite,true);
        }
        return buildAuthRule(ruleBlack,false);
    }

    private AuthRule buildAuthRule(String ruleStr, boolean isWhite) {

        String[] whiteSvcs = ruleStr.split(",");
        AuthRule authRule = new AuthRule();
        Map<String, List<SvcInstance>> map = new HashMap<String, List<SvcInstance>>();
        for (String svc : whiteSvcs) {
            if (svc.contains("[") && svc.contains("]")) {
                int idStart = svc.indexOf("[");
                int idEnd = svc.indexOf("]");
                String svcName = svc.substring(0, idStart);
                String ids = svc.substring(idStart, idEnd);
                String[] idsArray = ids.split(";");
                List<SvcInstance> list = new ArrayList<SvcInstance>();
                for (String s : idsArray) {
                    SvcInstance svcInstance = new SvcInstance();
                    svcInstance.setSvcName(svcName);
                    svcInstance.setSvcInstanceId(s);
                    list.add(svcInstance);
                }
                map.put(svc, list);
            } else {
                map.put(svc, null);
            }
        }
        if (isWhite) {
            authRule.setWhiteMap(map);
        } else {
            authRule.setBlackMap(map);
        }
        return authRule;

    }
}
