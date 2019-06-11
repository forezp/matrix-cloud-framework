package io.github.forezp.limit;


import io.github.forezp.entity.LimitEntity;
import io.github.forezp.entity.LimitResult;

/**
 * Email miles02@163.com
 *
 * @author fangzhipeng
 * create 2018-06-25
 **/

public interface LimitExcutor {

     LimitResult tryAccess(LimitEntity limitEntity);

}
