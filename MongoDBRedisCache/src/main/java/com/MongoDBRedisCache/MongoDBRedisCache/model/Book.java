package com.MongoDBRedisCache.MongoDBRedisCache.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import lombok.Data;

@Data
public class Book {
    @Id
    private String id; // รหัสหนังสือ (เช่น รหัสอ้างอิงในฐานข้อมูล)

    @Indexed
    private String title; // ชื่อหนังสือ (เคยใช้คำสั่ง Index เพื่อการค้นหาเร็วขึ้น)

    private String author; // ชื่อผู้เขียนหนังสือ

    private String description; // คำอธิบายเกี่ยวกับหนังสือ

    // Annotation @Data จาก Lombok จะสร้าง Getter, Setter, Equals, hashCode, และ
    // toString ให้เราอัตโนมัติ
    // Annotation @Id ระบุว่าตัวแปร id
    // เป็นรหัสหนังสือที่ใช้ในการระบุตัวตนของหนังสือในฐานข้อมูล
    // Annotation @Indexed ระบุว่าตัวแปร title มีการสร้างดัชนี (Index)
    // เพื่อการค้นหาเร็วขึ้น
}