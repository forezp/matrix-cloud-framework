package io.github.forezp.cache;


import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;


public class LimitRuleCache<LimitRule> extends AbstractCaffineCache {

    @Override
    LoadingCache createLoadingCache() {

        LoadingCache loadingCache = Caffeine.newBuilder()
                .expireAfterWrite(100000, TimeUnit.DAYS)
                .initialCapacity(10)
                .maximumSize(99999999)
                .recordStats()
                .build(new CacheLoader<String, LimitRule>() {
                    @Override
                    public LimitRule load(String key) throws Exception {
                        return null;
                    }
                });
        return loadingCache;
    }
}