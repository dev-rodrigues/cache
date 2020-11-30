package com.gasparbarancelli.cache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.host}") private String host;

    @Value("${spring.redis.port}") private Integer port;

    @Value("${spring.redis.password}") private String password;

    @Bean
    public JedisConnectionFactory jedisConnFactory() {
        RedisStandaloneConfiguration envConfig = new RedisStandaloneConfiguration();
        if (password != null && password.trim().length() > 0) {
            envConfig.setPassword(RedisPassword.of(password));
        }
        envConfig.setHostName(host);
        envConfig.setPort(port);

        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build();
        return new JedisConnectionFactory(envConfig, jedisClientConfiguration);
    }

    @Bean(name = "cacheManager")
    public CacheManager cacheManager(JedisConnectionFactory jedisConnFactory) {
        RedisCacheConfiguration cacheConfiguration =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofSeconds(3))
                        .disableCachingNullValues()
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(jedisConnFactory).cacheDefaults(cacheConfiguration).build();
    }

}
