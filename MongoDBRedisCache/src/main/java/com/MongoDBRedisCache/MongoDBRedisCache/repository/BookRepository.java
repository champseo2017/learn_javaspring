package com.MongoDBRedisCache.MongoDBRedisCache.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.MongoDBRedisCache.MongoDBRedisCache.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
    Book findByTitle(String title); // ค้นหาหนังสือตามชื่อ

    void deleteByTitle(String title); // ลบหนังสือตามชื่อ
}
