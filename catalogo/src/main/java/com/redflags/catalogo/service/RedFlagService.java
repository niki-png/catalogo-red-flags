package com.redflags.catalogo.service;

import com.redflags.catalogo.entity.RedFlag;
import com.redflags.catalogo.repository.RedFlagRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RedFlagService {
    
    private final RedFlagRepository repository;
    
    public RedFlagService(RedFlagRepository repository) {
        this.repository = repository;
    }
    
    public List<RedFlag> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }
    
    public List<RedFlag> findAll(String sortBy) {
        if (sortBy.equals("severity")) {
            return repository.findAllBySeverityAsc();
        }
        if (sortBy.equals("-severity")) {
            return repository.findAllBySeverityDesc();
        }
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortBy.startsWith("-")) {
            direction = Sort.Direction.DESC;
            sortBy = sortBy.substring(1);
        }
        return repository.findAll(Sort.by(direction, sortBy));
    }
    
    public List<RedFlag> search(String query) {
        if (query == null || query.trim().isEmpty()) {
            return findAll();
        }
        return repository.findByTitleContainingIgnoreCase(query.trim());
    }
    
    public Optional<RedFlag> findById(UUID code) {
        return repository.findById(code);
    }
    
    @Transactional
    public RedFlag save(RedFlag redFlag) {
        return repository.save(redFlag);
    }
    
    @Transactional
    public void delete(UUID code) {
        repository.deleteById(code);
    }
    
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }
    
    public boolean exists(UUID code) {
        return repository.existsById(code);
    }
    
    public List<String> getAllPersonTypes() {
        return repository.findAllPersonTypes();
    }
    
    public List<String> getAllContexts() {
        return repository.findAllContexts();
    }
    
    public List<String> getAllSeverities() {
        return repository.findAllSeverities();
    }
}