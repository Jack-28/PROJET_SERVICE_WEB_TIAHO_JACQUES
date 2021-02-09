package com.example.demo.controller.rest;

import com.example.demo.dto.CorrectionDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CorrectionMapper;
import com.example.demo.model.Correction;
import com.example.demo.model.User;
import com.example.demo.repository.CorrectionRepository;
import com.example.demo.service.CorrectionService;
import com.example.demo.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RestController
@RequestMapping("/api/correction")
public class CorrectionRestController {
    @Autowired
    private CorrectionRepository correctionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CorrectionService correctionService;
    @Autowired
    CorrectionMapper correctionMapper;
    @PostMapping("/newCorrection")
    public ResponseEntity<Correction> saveCorrection(@RequestBody @Valid CorrectionDTO correctionDTO) throws ResourceNotFoundException{
        Correction correction=new Correction();
        User user = userService.getUserById(correctionDTO.getAuthorId());
        if (user==null) {
            throw new ResourceNotFoundException("User not found for this id :: " + correctionDTO);
        }
        correction=correctionMapper.map(correctionDTO);
        correction.setAuthor(user);

        Correction correction1 = correctionService.saveCorrection(correction);


        return new ResponseEntity<Correction>(correction1, HttpStatus.CREATED);
    }
    @PutMapping("/updateCorrection/{id}")
    public ResponseEntity < Correction > updateCorrection(@Valid @PathVariable(value = "id") Integer correctionId,
                                                          @Valid @RequestBody Correction correctionDetails) throws ResourceNotFoundException {
        Correction correction = correctionRepository.findById(correctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Correction not found for this id :: " + correctionId));
        correction.setCourbure(correctionDetails.getCourbure());
        correction.setCreateDate(correctionDetails.getCreateDate());
        correction.setAuthor(correctionDetails.getAuthor());
        final Correction updatedCorrection = correctionRepository.save(correction);
        return ResponseEntity.ok(updatedCorrection);
    }

    @DeleteMapping("/deleteCorrection/{id}")
    public Map< String, Boolean > deleteCorrectionById(@PathVariable(value = "id") Integer correctionId)
            throws ResourceNotFoundException {
        Correction correction = correctionRepository.findById(correctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Correction not found for this id :: " + correctionId));

        correctionRepository.delete(correction);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @GetMapping("/infoCorrection/{id}")
    public ResponseEntity< Correction > getCorrectionById(@PathVariable(value = "id") Integer correctionId)
            throws ResourceNotFoundException {
        Correction correction = correctionRepository.findById(correctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Correction not found for this id :: " + correctionId));
        return ResponseEntity.ok().body(correction);
    }
    @GetMapping("/listCorrection")
    public List<Correction> getAllCorrection() {
        return (List<Correction>) correctionRepository.findAll();
    }


}
