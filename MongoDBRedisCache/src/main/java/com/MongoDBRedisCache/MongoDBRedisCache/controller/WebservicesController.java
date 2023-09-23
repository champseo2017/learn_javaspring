// ระบุ package ที่ code นี้อยู่
package com.MongoDBRedisCache.MongoDBRedisCache.controller;

// นำเข้า libraries และ modules ที่จำเป็น
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// นำเข้า class Book และ BookRepository จาก package ที่ระบุ
import com.MongoDBRedisCache.MongoDBRedisCache.model.Book;
import com.MongoDBRedisCache.MongoDBRedisCache.model.CachedBook;

import com.MongoDBRedisCache.MongoDBRedisCache.repository.BookRepository;
import com.MongoDBRedisCache.MongoDBRedisCache.repository.CachedBookRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// บอกว่า class นี้เป็น RESTful web service controller
@RestController
public class WebservicesController {

    // ใช้ dependency injection สำหรับ BookRepository
    @Autowired
    BookRepository repository;

    @Autowired
    CachedBookRepository cachedBookRepository;

    // ใช้ dependency injection สำหรับ MongoTemplate
    @Autowired
    MongoTemplate mongoTemplate;

    // ตั้งค่า Logger
    private static final Logger logger = LoggerFactory.getLogger(WebservicesController.class);

    // ตั้งค่า endpoint URL เป็น /book และ HTTP method เป็น POST
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public Book saveBook(Book book) {
        // เรียก method save จาก repository สำหรับบันทึกข้อมูล
        return repository.save(book);
    }

    // ตั้งค่า endpoint URL เป็น /book/{title} และ HTTP method เป็น GET
    @Cacheable(value = "book", key = "#title")
    @RequestMapping(value = "/book/{title}", method = RequestMethod.GET)
    public ResponseEntity<?> findBookByTitle(@PathVariable String title) {
        // Search for the book from repository by title
        Book insertedBook = repository.findByTitle(title);
        // Log insertedBook
        logger.info("Inserted Book: {}", insertedBook);
        // If the book is not found
        if (insertedBook == null) {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }

        // Return the insertedBook object
        return new ResponseEntity<>(insertedBook, HttpStatus.OK);
    }

    // ตัวอย่าง method สำหรับลบข้อมูล
    @CacheEvict(value = "book", key = "#title")
    @RequestMapping(value = "/book/{title}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable String title) {
        repository.deleteByTitle(title);
    }

    // ตัวอย่าง method สำหรับอัปเดตข้อมูล
    @CachePut(value = "book", key = "#title")
    @RequestMapping(value = "/book/{title}", method = RequestMethod.PUT)
    public Book updateBook(@PathVariable String title, Book updatedBook) {
        Book existingBook = repository.findByTitle(title);
        existingBook.setAuthor(updatedBook.getAuthor()); // ตัวอย่างการอัปเดต field
        return repository.save(existingBook);
    }

    @RequestMapping(value = "/cachedbook/{title}", method = RequestMethod.GET)
    public ResponseEntity<?> findCachedBookByTitle(@PathVariable String title) {
        CachedBook cachedBook = cachedBookRepository.findByTitle(title);
        if (cachedBook == null) {
            return new ResponseEntity<>("Cached Book not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cachedBook, HttpStatus.OK);
    }
}
