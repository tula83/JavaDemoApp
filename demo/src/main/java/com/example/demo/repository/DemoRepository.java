package com.example.demo.repository;

import com.example.demo.models.DemoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DemoRepository extends CrudRepository<DemoModel,Integer> {
    public Optional<DemoModel> findByName(String name);
}

