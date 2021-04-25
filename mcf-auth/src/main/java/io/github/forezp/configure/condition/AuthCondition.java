package io.github.forezp.configure.condition;


import io.github.forezp.condition.EqualCondition;

import static io.github.forezp.constant.AuthConstants.AUTH_ENABLE;
import static io.github.forezp.constant.AuthConstants.CONFIG_TRUE;

/**
 * Created by forezp on 2019/5/3.
 */
public class AuthCondition extends EqualCondition {

    public AuthCondition() {
        super(AUTH_ENABLE, CONFIG_TRUE);
    }
}
