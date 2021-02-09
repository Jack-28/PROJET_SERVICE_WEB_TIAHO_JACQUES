package com.example.demo.service;

import com.example.demo.model.Correction;

import java.util.List;

public interface CorrectionService {
    /**
     * Create a correction
     * @param correction the correction to create without id and createdDate
     * @return created correction
     */
    Correction saveCorrection(Correction correction);
    Correction updateCorrection(Correction correction);
    void deleteCorrectionById(Integer id);
    Correction getCorrectionById(Integer id);
    List<Correction> getAllCorrection();


}
