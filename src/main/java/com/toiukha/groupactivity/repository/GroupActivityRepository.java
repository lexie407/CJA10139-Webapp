package com.toiukha.groupactivity.repository;

/**
 * 【轉換註解】
 * 配合 Spring Boot 導入而新增的 Repository，使用 Spring Data JPA。
 * 此類在新程式中取代手動 DAO 操作，與舊有 DAO 類別並存。
 */

import org.springframework.data.jpa.repository.JpaRepository;
import com.toiukha.groupactivity.entity.GroupActivityVO;

public interface GroupActivityRepository extends JpaRepository<GroupActivityVO, Integer> {
}
