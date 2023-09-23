package com.MongoDBRedisCache.MongoDBRedisCache.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import lombok.Data;

@Data
@RedisHash("CachedBook")
public class CachedBook {
    @Id
    private String id;
    private String title;
    private String author;
    private String description;
}
