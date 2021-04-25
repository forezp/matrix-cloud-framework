package io.github.forezp.loader;

import io.github.forezp.entity.AuthInstance;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 数据模型
 * swr.auth.enable: true
 * swr.auth.blacks:
 *    - swr_service_name: consumer
 *    - swr_app_version: 1.1
 */

@Component
@RefreshScope
@ConfigurationProperties(prefix = "swr.auth")
public class AuthRuleProperties {

    Boolean enable;
    List<AuthInstance> blacks;
    List<AuthInstance> whites;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<AuthInstance> getBlacks() {
        return blacks;
    }

    public void setBlacks(List<AuthInstance> blacks) {
        this.blacks = blacks;
    }

    public List<AuthInstance> getWhites() {
        return whites;
    }

    public void setWhites(List<AuthInstance> whites) {
        this.whites = whites;
    }
}

