package io.github.forezp.config.condition;

import static io.github.forezp.constant.McfConfigConstant.AUTH_SERVICE_ENABLE;
import static io.github.forezp.constant.McfConfigConstant.CONFIG_TRUE;

/**
 * Created by forezp on 2019/5/3.
 */
public class AuthCondition extends EqualCondition{

    public AuthCondition() {
        super(AUTH_SERVICE_ENABLE, CONFIG_TRUE);
    }
}
