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

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "MOBILE", nullable = false)
    private String mobile;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "CITY")
    private String city;
}
