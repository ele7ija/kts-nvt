package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.CulturalOfferingSubtypeApi;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingSubTypeDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingSubtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController(value = "/cultural-offerings-subtype")
public class CulturalOfferingSubtypeController implements CulturalOfferingSubtypeApi {

    @Autowired
    private CulturalOfferingSubtypeService culturalOfferingSubtypeService;


    @Override
    public ResponseEntity<List<CulturalOfferingSubType>> findAll() {
        return new ResponseEntity<>(
                culturalOfferingSubtypeService.findAll(),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Page<CulturalOfferingSubType>> findAll(Pageable pageable) {
        return new ResponseEntity<>(
                culturalOfferingSubtypeService.findAll(pageable),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<CulturalOfferingSubType> get(Long id) {
        CulturalOfferingSubType culturalOfferingSubType = culturalOfferingSubtypeService.findOne(id);
        return new ResponseEntity<>(
                culturalOfferingSubType,
                culturalOfferingSubType == null ? HttpStatus.NOT_FOUND : HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<CulturalOfferingSubType> create(CulturalOfferingSubTypeDTO body) {
        return new ResponseEntity<>(
                culturalOfferingSubtypeService.create(body);
        )
    }

    @Override
    public ResponseEntity<CulturalOfferingSubType> update(CulturalOfferingSubTypeDTO body, String id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        return null;
    }
}
