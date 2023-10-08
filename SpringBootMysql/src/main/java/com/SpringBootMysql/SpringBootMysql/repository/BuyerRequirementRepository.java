package com.SpringBootMysql.SpringBootMysql.repository;

import com.SpringBootMysql.SpringBootMysql.model.BuyerRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository  // บอก Spring Framework ว่า interface นี้เป็น repository ซึ่งจะใช้สำหรับการเข้าถึงข้อมูลใน database
@RepositoryRestResource  // บอก Spring Framework ให้สร้าง HTTP endpoint สำหรับ repository นี้โดยอัตโนมัติ
public interface BuyerRequirementRepository extends JpaRepository<BuyerRequirement, UUID> {  // ประกาศ interface ที่ extends JpaRepository ทำให้เราสามารถใช้งาน method พื้นฐานสำหรับการจัดการข้อมูลได้

    Page<BuyerRequirement> findAllByOrderByCreatedDesc(Pageable pageable);  // ประกาศ method สำหรับค้นหาทั้งหมดและเรียงลำดับตามวันที่สร้างในลำดับที่ลดลง

    Page<BuyerRequirement> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);  // ประกาศ method สำหรับค้นหาตามชื่อโดยไม่สนใจตัวพิมพ์เล็ก-ใหญ่
}


/*
 * `Page<BuyerRequirement>` เป็นคลาสจาก Spring Data library ที่ให้คุณจัดการและนำเสนอข้อมูลในรูปแบบของหน้า (pagination). ในการทำงานกับข้อมูลจำนวนมาก, การแบ่งข้อมูลเป็นหน้าๆ จะทำให้ง่ายต่อการจัดการและประสิทธิภาพมากขึ้น โดยที่ผู้ใช้งานหรือระบบสามารถดึงข้อมูลในปริมาณที่กำหนดได้ทีละหน้า.

ใน `Page<BuyerRequirement>`:

- `Page` คือคลาสที่ให้คุณทำ pagination.
- `<BuyerRequirement>` คือ generic type parameter ที่บอกว่าแต่ละหน้าจะมีอ็อบเจ็กต์ของประเภท `BuyerRequirement`.

`Page` มีเมทอดและคุณสมบัติที่เป็นประโยชน์มากมาย เช่น:

- `getContent()`: ให้ข้อมูลทั้งหมดในหน้าปัจจุบัน.
- `getNumber()`: ให้หมายเลขหน้าปัจจุบัน.
- `getTotalPages()`: ให้จำนวนหน้าทั้งหมด.
- `getTotalElements()`: ให้จำนวนข้อมูลทั้งหมด.
- `isFirst()`, `isLast()`, `hasNext()`, `hasPrevious()`: เมทอดเหล่านี้ให้สถานะที่บ่งบอกว่าหน้าปัจจุบันเป็นหน้าแรก, หน้าสุดท้าย, มีหน้าถัดไปหรือไม่, หรือมีหน้าก่อนหน้าหรือไม่.

การใช้ `Page` จะช่วยให้คุณสามารถจัดการข้อมูลและนำเสนอข้อมูลในแอปพลิเคชันของคุณได้อย่างมีประสิทธิภาพ.

 */

