package com.example.demo.service;

import com.example.demo.model.Correction;
import com.example.demo.repository.CorrectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;


@Service
public class CorrectionServiceImpl implements CorrectionService{
    @Autowired
    private CorrectionRepository correctionRepository;
    @Override
    public Correction saveCorrection(Correction correction){
        return this.correctionRepository.save(correction);
    }

    @Override
    public Correction updateCorrection(Correction correction) {

        return this.correctionRepository.save( correction);
    }

    @Override
    public Correction getCorrectionById(Integer id) {

        Optional< Correction > optional = correctionRepository.findById(id);
        Correction correction = null;
        if (optional.isPresent()) {
            correction = optional.get();
        } else {
            throw new RuntimeException(" Correction not found for id :: " + id);
        }
        return correction;
    }


    @Override
    public List<Correction> getAllCorrection() {
        return (List<Correction>) correctionRepository.findAll();
    }

    @Override
    public void deleteCorrectionById(Integer id) {
        this.correctionRepository.deleteById(id);
    }

}
