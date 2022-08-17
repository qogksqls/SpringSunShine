package com.ssafy.web.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	@Value("${spring.redis.host}")
	private String redisHost;
	
	@Value("${spring.redis.port}")
	private int redisPort;
	
	@Value("${spring.redis.password}")
	private String redisPassword;

	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
//		return new LettuceConnectionFactory(redisHost, redisPort);
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(redisHost);
		redisStandaloneConfiguration.setPort(redisPort);
        redisStandaloneConfiguration.setPassword(redisPassword); //redis에 비밀번호가 설정 되어 있는 경우 설정해주면 됩니다.
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        return lettuceConnectionFactory;
	}
	//별도의 설정없이 RedisTemplate 메서드로 Redis 서버에 명령어 수행 가능 
	@Bean
	public RedisTemplate<String, String> redisTemplate(){
		// redisTemplate 에서 set, get,delete 사용 
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		//redis-cli를 통해 데이터 조회 시, 알아볼 수 없는 형태로 출력되는 것을 방지 
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.setConnectionFactory(redisConnectionFactory());
			
		return redisTemplate;
	}
	
	@SuppressWarnings("deprecation")
	@Bean
    public CacheManager cacheManager() {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory());
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())) // Value Serializer 변경
                .entryTtl(Duration.ofMinutes(30)); // 캐시 수명 30분
        builder.cacheDefaults(configuration);
        return builder.build();
    }
	
	
	
}

