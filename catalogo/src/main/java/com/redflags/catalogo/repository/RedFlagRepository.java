package com.redflags.catalogo.repository;

import com.redflags.catalogo.entity.RedFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RedFlagRepository extends JpaRepository<RedFlag, UUID> {
    
    List<RedFlag> findByTitleContainingIgnoreCase(String title);
    
    @Query("SELECT DISTINCT r.personInvolved FROM RedFlag r ORDER BY r.personInvolved")
    List<String> findAllPersonTypes();
    
    @Query("SELECT DISTINCT r.context FROM RedFlag r ORDER BY r.context")
    List<String> findAllContexts();
    
    @Query("SELECT DISTINCT r.severity FROM RedFlag r ORDER BY r.severity")
    List<String> findAllSeverities();
}