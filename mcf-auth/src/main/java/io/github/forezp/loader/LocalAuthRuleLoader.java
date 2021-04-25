package io.github.forezp.loader;


import io.github.forezp.entity.AuthRule;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 读取配置文件
 */
public class LocalAuthRuleLoader implements AuthRuleLoader {

    @Autowired(required = false)
    AuthRuleProperties authRuleProperties;

    @Override
    public AuthRule load() {
        AuthRule authRule = new AuthRule();
        authRule.setEnable(authRuleProperties.enable);
        authRule.setBlacks(authRuleProperties.getBlacks());
        authRule.setWhites(authRuleProperties.getWhites());
        return authRule;
    }
}
