package com.example.catalog.repository;

import com.example.catalog.entity.RedFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedFlagRepository extends JpaRepository<RedFlag, String> {
    
    List<RedFlag> findByTitleContainingIgnoreCase(String title);
    
    @Query("SELECT DISTINCT r.personInvolved FROM RedFlag r ORDER BY r.personInvolved")
    List<String> findAllPersonTypes();
    
    @Query("SELECT DISTINCT r.context FROM RedFlag r ORDER BY r.context")
    List<String> findAllContexts();
    
    @Query("SELECT DISTINCT r.severity FROM RedFlag r ORDER BY r.severity")
    List<String> findAllSeverities();
}