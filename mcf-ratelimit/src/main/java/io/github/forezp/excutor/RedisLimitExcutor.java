package io.github.forezp.excutor;


import io.github.forezp.entity.LimitEntity;
import io.github.forezp.entity.LimitResult;
import io.github.forezp.util.KeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Email miles02@163.com
 *
 * @author fangzhipeng
 * create 2018-06-26
 **/
public class RedisLimitExcutor implements LimitExcutor {

    @Value("${spring.application.name}")
    String applicationName;

    private StringRedisTemplate stringRedisTemplate;
    Logger log = LoggerFactory.getLogger(RedisLimitExcutor.class);


    public RedisLimitExcutor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public LimitResult tryAccess(LimitEntity limitEntity) {
        String identifier = applicationName;

        String key = limitEntity.getName();
        if (StringUtils.isEmpty(key)) {
            return null;
        }

        int seconds = limitEntity.getSecond();
        int limitCount = limitEntity.getNum();
        String compositeKey = KeyUtil.compositeKey(identifier, key);
        List<String> keys = new ArrayList<>();
        keys.add(compositeKey);
        String luaScript = buildLuaScript();
        RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);
        Long count = stringRedisTemplate.execute(redisScript, keys, "" + limitCount, "" + seconds);
        log.info("Access try count is {} for key={}", count, key);
//        return count != 0;
        LimitResult result = new LimitResult();
        result.setName(key);

        if (count != 0) {
            result.setResultType(LimitResult.ResultType.SUCCESS);
        } else {
            result.setResultType(LimitResult.ResultType.FAIL);
        }
        return result;
    }

    private String buildLuaScript() {
        StringBuilder lua = new StringBuilder();
        lua.append(" local key = KEYS[1]");
        lua.append("\nlocal limit = tonumber(ARGV[1])");
        lua.append("\nlocal curentLimit = tonumber(redis.call('get', key) or \"0\")");
        lua.append("\nif curentLimit + 1 > limit then");
        lua.append("\nreturn 0");
        lua.append("\nelse");
        lua.append("\n redis.call(\"INCRBY\", key, 1)");
        lua.append("\nredis.call(\"EXPIRE\", key, ARGV[2])");
        lua.append("\nreturn curentLimit + 1");
        lua.append("\nend");
        return lua.toString();
    }
}
