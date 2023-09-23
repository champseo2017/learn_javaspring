package com.MongoDBRedisCache.MongoDBRedisCache.repository;

import com.MongoDBRedisCache.MongoDBRedisCache.model.CachedBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CachedBookRepository extends CrudRepository<CachedBook, String> {
    CachedBook findByTitle(String title);
    void deleteByTitle(String title);
}
