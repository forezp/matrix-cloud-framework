package io.github.forezp.excutor;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

import io.github.forezp.entity.LimitEntity;
import io.github.forezp.entity.LimitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 采用RateLimiter 令牌桶的算法
 * <p>
 * Email miles02@163.com
 *
 * @author fangzhipeng
 * create 2018-06-26
 **/
public class GuavaLimitExcutor implements LimitExcutor {

    Logger log = LoggerFactory.getLogger(GuavaLimitExcutor.class);

    private Map<String, RateLimiter> rateLimiterMap = Maps.newConcurrentMap();


    @Override
    public LimitResult tryAccess(LimitEntity limitEntity) {

        RateLimiter rateLimiter = getRateLimiter(limitEntity);
        if (rateLimiter == null) {
            return null;
        }
        LimitResult limitResult = new LimitResult();
        limitResult.setName(limitEntity.getName());

        boolean access = rateLimiter.tryAcquire(1, 200, TimeUnit.MILLISECONDS);
        log.info("name:" + limitEntity.getName() + " access:{}", access);
        if (access) {
            limitResult.setResultType(LimitResult.ResultType.SUCCESS);
        } else {
            limitResult.setResultType(LimitResult.ResultType.FAIL);
        }
        return limitResult;
    }

    private RateLimiter getRateLimiter(LimitEntity limitEntity) {
        if (limitEntity == null) {
            return null;
        }
        String key = limitEntity.getName();
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        RateLimiter rateLimiter = rateLimiterMap.get(key);
        Double limitNum = Double.valueOf(limitEntity.getNum());
        Double permitsPerSecond = limitNum / limitEntity.getSecond();
        if (rateLimiter == null) {
            RateLimiter newRateLimiter = RateLimiter.create(permitsPerSecond);
            rateLimiter = rateLimiterMap.putIfAbsent(key, newRateLimiter);
            if (rateLimiter == null) {
                rateLimiter = newRateLimiter;
            }
        } else {
            if (rateLimiter.getRate() != permitsPerSecond) {
                RateLimiter newRateLimiter = RateLimiter.create(permitsPerSecond);
                rateLimiter = rateLimiterMap.put(key, newRateLimiter);
                if (rateLimiter == null) {
                    rateLimiter = newRateLimiter;
                }
            }
        }
        return rateLimiter;

    }

}
