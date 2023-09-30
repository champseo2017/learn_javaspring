package com.SpringBootMysql.SpringBootMysql.model;

import lombok.Data;
import javax.persistence.*;

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
