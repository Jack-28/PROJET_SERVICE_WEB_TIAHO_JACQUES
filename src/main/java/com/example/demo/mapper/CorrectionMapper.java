package com.example.demo.mapper;

import com.example.demo.dto.CorrectionDTO;
import com.example.demo.model.Correction;
import org.mapstruct.Mapper;



@Mapper(componentModel="spring")
public interface CorrectionMapper{
    CorrectionDTO map(Correction correction);
    Correction map(CorrectionDTO correctionDTO);
}
