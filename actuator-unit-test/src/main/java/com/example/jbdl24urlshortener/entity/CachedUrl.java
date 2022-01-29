package com.example.jbdl24urlshortener.entity;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@RedisHash(value = "cached_url" , timeToLive = 100l)
public class CachedUrl {
    @Id
    private String id;
    private String url;
}
