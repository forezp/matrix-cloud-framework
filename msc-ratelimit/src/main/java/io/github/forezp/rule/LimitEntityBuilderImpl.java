package io.github.forezp.rule;

import io.github.forezp.entity.ComposeLimitEntity;
import io.github.forezp.entity.GlobalLimitRule;
import io.github.forezp.entity.LimitEntity;
import io.github.forezp.entity.RatelimitRule;
import io.github.forezp.enums.LimitType;
import io.github.forezp.config.RatelimitProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by forezp on 2019/6/7.
 */
public class LimitEntityBuilderImpl implements LimitEntityBuilder{

    /**
     *
     * Created by forezp on 2019/5/4.
     *
     * rate.limit.enable: true
     * rate.limit.global.num: 100
     * rate.limit.global.second: 1
     * rate.limit.services:
     *    - name: mcf-provider
     *      num: 10
     *      second: 11
     *    - name: mcf-consumer
     *      num: 23
     *      second: 11
     */
    @Autowired
    RatelimitProperties ratelimitProperties;

    @Override
    public ComposeLimitEntity build() {

        if(!ratelimitProperties.getEnable()){
            return null;
        }
        ComposeLimitEntity composeLimitEntity=new ComposeLimitEntity();
        GlobalLimitRule globalLimitRule= ratelimitProperties.getGlobal();
        if(globalLimitRule!=null){
            LimitEntity limitEntity=new LimitEntity();
            limitEntity.setName("global");
            limitEntity.setLimitType(LimitType.GLOBAL);
            limitEntity.setNum(globalLimitRule.getNum());
            limitEntity.setSecond(globalLimitRule.getSecond());
            composeLimitEntity.setGlobalLimit(limitEntity);

        }
        List<LimitEntity> seviceLimit=new ArrayList<>();
        List<RatelimitRule> list=ratelimitProperties.getServices();
        if(!CollectionUtils.isEmpty(list)){
           for (RatelimitRule ratelimitRule:list){
               LimitEntity limitEntity=new LimitEntity();
               BeanUtils.copyProperties(ratelimitRule,limitEntity);
               limitEntity.setLimitType(LimitType.SERVICE);
               seviceLimit.add(limitEntity);
           }
           composeLimitEntity.setSeviceLimit(seviceLimit);
        }
        return composeLimitEntity;
    }
}
