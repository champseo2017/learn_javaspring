package com.SpringBootMysql.SpringBootMysql.model;

import lombok.Data;
import javax.persistence.*;

/* 
 ใช้ javax.persistence สำหรับ annotations เช่น @Entity, @Table, @Id, @GeneratedValue, @Column และอื่น ๆ ที่เกี่ยวข้องกับ JPA. และใช้ org.springframework.data สำหรับเรื่องที่เกี่ยวข้องกับ Spring Data เช่น repositories.
 */

@Data
@Entity
@Table(name = "buyer_requirement")
public class BuyerRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "city")
    private String city;
}
