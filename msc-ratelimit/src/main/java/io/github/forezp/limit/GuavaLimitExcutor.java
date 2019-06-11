package io.github.forezp.limit;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

import io.github.forezp.entity.LimitEntity;
import io.github.forezp.entity.LimitResult;
import io.github.forezp.util.KeyUtil;
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
 *         create 2018-06-26
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

        boolean access = rateLimiter.tryAcquire(1, 2000, TimeUnit.MILLISECONDS);
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
        String key = KeyUtil.getKey(limitEntity);
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
        }else {
            if(rateLimiter.getRate()!=permitsPerSecond){
                RateLimiter newRateLimiter = RateLimiter.create(permitsPerSecond);
                rateLimiter = rateLimiterMap.put(key, newRateLimiter);
                if (rateLimiter == null) {
                    rateLimiter = newRateLimiter;
                }
            }
        }
        return rateLimiter;

    }


//    public static void main(String[] args) {
//        RateLimiter rateLimiter = RateLimiter.create( 0.5 );
//        CyclicBarrier barrier = new CyclicBarrier( 2 );
//        new Thread( new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    System.out.println( "访问次数：" + i );
//                    try {
//                        Thread.sleep( 1000 );
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    boolean access = rateLimiter.tryAcquire( 1 );
//                    System.out.println( "访问次数：" + i + " acess: " + access );
//                }
//                try {
//                    barrier.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (BrokenBarrierException e) {
//                    e.printStackTrace();
//                }
//            }
//        } ).start();
//        try {
//            barrier.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (BrokenBarrierException e) {
//            e.printStackTrace();
//        }
//        System.out.println( "test end" );
//    }
}
