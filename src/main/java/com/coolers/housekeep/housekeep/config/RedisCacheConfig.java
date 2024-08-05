package com.coolers.housekeep.housekeep.config;

import com.coolers.housekeep.housekeep.util.RedisCacheUtil;
import com.coolers.housekeep.housekeep.util.RedisUtil;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.BatchStrategies;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableCaching
@Configuration
@Import({RedisUtil.class})
@EnableConfigurationProperties(CacheProperties.class)
public class RedisCacheConfig extends CachingConfigurerSupport {
    /**
     * redis 连接工厂
     */
    private final RedisConnectionFactory connectionFactory;

    /**
     * 缓存配置数据
     */
    private final CacheProperties cacheProperties;

    public RedisCacheConfig(RedisConnectionFactory connectionFactory, CacheProperties cacheProperties) {
        this.connectionFactory = connectionFactory;
        this.cacheProperties = cacheProperties;
    }


    @Bean
    @Override
    public CacheManager cacheManager() {
        CacheProperties.Redis redisCacheProperties = cacheProperties.getRedis();
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(connectionFactory).cacheDefaults(redisCacheConfiguration(redisCacheProperties)).transactionAware().cacheWriter(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory, BatchStrategies.scan(1000)));
        if (redisCacheProperties.isEnableStatistics()) {
            builder.enableStatistics();
        }
        return builder.build();
    }

    /**
     * 自定义KeyGenerator
     * key结构 key-prefix+cacheName::类名.方法名(参数)
     * 最终生成的key会受到key-prefix和@Cacheable注解中配置的cacheNames影响 示例：
     * <b>@Cacheable(cacheNames = {"query.hello"})</b>
     * query.hello::HelloController.hello(spring)
     * 如果配置文件中配置了key-prefix: 'cache:'
     * cache:query.hello::HelloController.hello(world)
     *
     * @return KeyGenerator
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            String paramStr = Stream.of(params).map(Object::toString).collect(Collectors.joining(",", "(", ")"));
            return target.getClass().getSimpleName() + "." + method.getName() + paramStr;
        };
    }

    /**
     * redis cache configuration 配置
     * 如果需要缓存LocalDateTime类型，自定义value序列化器
     *
     * @param redisCacheProperties redis缓存配置
     * @return RedisCacheConfiguration
     */
    RedisCacheConfiguration redisCacheConfiguration(CacheProperties.Redis redisCacheProperties) {
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new RedisCacheUtil()));
        if (redisCacheProperties.getTimeToLive() != null) {
            cacheConfig = cacheConfig.entryTtl(redisCacheProperties.getTimeToLive());
        }
        if (redisCacheProperties.getKeyPrefix() != null) {
            cacheConfig = cacheConfig.prefixCacheNameWith(redisCacheProperties.getKeyPrefix());
        }
        if (!redisCacheProperties.isCacheNullValues()) {
            cacheConfig = cacheConfig.disableCachingNullValues();
        }
        if (!redisCacheProperties.isUseKeyPrefix()) {
            cacheConfig = cacheConfig.disableKeyPrefix();
        }
        // 这里会影响最终生成的缓存key
        if (StringUtils.hasText(redisCacheProperties.getKeyPrefix())) {
            cacheConfig = cacheConfig.prefixCacheNameWith(redisCacheProperties.getKeyPrefix());
        }
        return cacheConfig;
    }
}
