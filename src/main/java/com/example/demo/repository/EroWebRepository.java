package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EroWeb;

@Repository
public interface EroWebRepository extends CrudRepository<EroWeb, Long>{
    EroWeb findById(int id);
}

