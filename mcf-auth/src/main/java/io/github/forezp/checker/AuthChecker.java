package io.github.forezp.checker;

import io.github.forezp.entity.AuthCheckResult;
import io.github.forezp.entity.AuthRule;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by forezp on 2019/5/2.
 */
public interface AuthChecker {

    AuthCheckResult check(HttpServletRequest httpServletRequest,AuthRule authRule);
}
