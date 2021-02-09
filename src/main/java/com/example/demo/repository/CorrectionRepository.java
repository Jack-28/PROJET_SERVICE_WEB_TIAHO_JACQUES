package com.example.demo.repository;

import com.example.demo.model.Correction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CorrectionRepository extends CrudRepository<Correction, Integer> {
    Correction getCorrectionById(Integer correctionId);
}