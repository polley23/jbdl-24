package com.example.jbdl24urlshortener.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class RedisConfiguration {

    @Bean
    JedisConnectionFactory jedisFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration
                = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(1);
        return new JedisConnectionFactory(redisStandaloneConfiguration,
                JedisClientConfiguration.builder().build());
    }

    @Bean
    RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisFactory());
        return redisTemplate;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}
